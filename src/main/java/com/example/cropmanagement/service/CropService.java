package com.example.cropmanagement.service;

import com.example.cropmanagement.entity.Crop;
import com.example.cropmanagement.mapper.CropMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // 标记为 Service 层
public class CropService {

    @Autowired // 自动配发一个采购员给厨师
    private CropMapper cropMapper;

    public List<Crop> getAllCrops() {
        return cropMapper.findAll();
    }
}