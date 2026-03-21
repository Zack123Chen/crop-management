package com.example.cropmanagement.controller;

import com.example.cropmanagement.entity.Crop;
import com.example.cropmanagement.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/crop") // 统一前缀
public class CropController {
    @Autowired
    private CropService cropService;

    @PostMapping // 添加
    public String add(@RequestBody Crop crop) {
        cropService.addCrop(crop);
        return "添加成功！ID是：" + crop.getId();
    }

    @GetMapping("/{id}") // 查询单个
    public Crop get(@PathVariable Long id) {
        return cropService.getById(id);
    }

    @GetMapping("/list") // 查询列表
    public List<Crop> list() {
        return cropService.getAll();
    }

    @PutMapping("/{id}") // 更新
    public String update(@PathVariable Long id, @RequestBody Crop crop) {
        crop.setId(id);
        cropService.update(crop);
        return "更新成功！";
    }

    @DeleteMapping("/{id}") // 删除
    public String delete(@PathVariable Long id) {
        cropService.delete(id);
        return "删除成功！";
    }
}