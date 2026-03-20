package com.example.cropmanagement.controller;

import com.example.cropmanagement.entity.Crop;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/crop") // 统一给接口加个前缀，就像餐厅的“农作物专区”
public class CropController {

    @GetMapping("/list") // 对应考核要求的 GET /crop/list
    public List<Crop> getAllCrops() {
        // 先造两个假数据试试水
        List<Crop> list = new ArrayList<>();
        Crop c = new Crop();
        c.setId(1L);
        c.setName("超级大水稻");
        c.setCategory("粮食");
        list.add(c);
        return list;
    }
}