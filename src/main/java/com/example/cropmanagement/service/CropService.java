package com.example.cropmanagement.service;

import com.example.cropmanagement.entity.Crop;
import com.example.cropmanagement.mapper.CropMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CropService {
    @Autowired
    private CropMapper cropMapper;

    @Autowired
    private StringRedisTemplate redisTemplate; // Redis 机械臂


    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

    /**
     * 带缓存的查询：先查 Redis，没有再查 MySQL
     */
    public Crop getById(Long id) {
        String key = "crop:" + id;
        try {
            // 1. 尝试从 Redis 获取（看速取柜）
            String json = redisTemplate.opsForValue().get(key);
            if (json != null) {
                System.out.println("--- Redis 命中！直接返回缓存数据 ---");
                return objectMapper.readValue(json, Crop.class);
            }

            // 2. 缓存没有，查 MySQL（跑仓库）
            Crop crop = cropMapper.findById(id);
            if (crop != null) {
                // 3. 查到后存入 Redis，有效期设为 10 分钟（防止数据过期）
                String cropJson = objectMapper.writeValueAsString(crop);
                redisTemplate.opsForValue().set(key, cropJson, 10, TimeUnit.MINUTES);
                System.out.println("--- MySQL 命中！已同步至 Redis ---");
            }
            return crop;
        } catch (Exception e) {
            // 这一行非常关键：它会在控制台打印出 Redis 连不上的真实原因！
            System.err.println("❌ Redis 报错了！具体原因：" + e.getMessage());
            e.printStackTrace();
            return (Crop) cropMapper.findAll();
        }
    }

    public void addCrop(Crop crop) {
        cropMapper.insert(crop);
    }


    public List<Crop> getAll() {
        String key = "crop:list:all";
        try {
            String json = redisTemplate.opsForValue().get(key);
            if (json != null) {
                System.out.println("🚀 [Redis 命中] 正在从缓存秒回整张作物表！");
                // 复杂的 JSON 列表转对象列表逻辑
                return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, Crop.class));
            }

            List<Crop> allCrops = cropMapper.findAll();
            if (allCrops != null && !allCrops.isEmpty()) {
                String listJson = objectMapper.writeValueAsString(allCrops);
                redisTemplate.opsForValue().set(key, listJson, 5, TimeUnit.MINUTES);
                System.out.println("🏠 [MySQL 命中] 正在从仓库搬运整张表，并存入 Redis...");
            }
            return allCrops;
        } catch (Exception e) {
            // 这一行非常关键：它会在控制台打印出 Redis 连不上的真实原因！
            System.err.println("❌ Redis 报错了！具体原因：" + e.getMessage());
            e.printStackTrace();
            return cropMapper.findAll();
        }
    }

    public void update(Crop crop) {
        cropMapper.update(crop);
        // 重点：数据更新了，必须把 Redis 里的旧缓存删掉（防止卖陈年旧货）
        redisTemplate.delete("crop:" + crop.getId());
    }

    public void delete(Long id) {
        cropMapper.deleteById(id);
        redisTemplate.delete("crop:" + id); // 删除数据也要清缓存
    }
}