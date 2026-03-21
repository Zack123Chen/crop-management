package com.example.cropmanagement.controller;

import com.example.cropmanagement.entity.Crop;
import com.example.cropmanagement.service.CropService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 农作物管理控制器
 * 负责接收外部 HTTP 请求并调用 Service 层处理业务
 */
@Tag(name = "农作物管理接口", description = "负责农作物的增删改查全套逻辑")
@RestController
@RequestMapping("/crop") // 统一接口前缀
public class CropController {

    @Autowired
    private CropService cropService;

    @Operation(summary = "添加新作物", description = "传入作物名称、类别和周期，ID 由数据库自动生成")
    @PostMapping // 添加农作物接口
    public String add(@RequestBody Crop crop) {
        cropService.addCrop(crop);
        return "添加成功！生成的 ID 是：" + crop.getId();
    }

    @Operation(summary = "按 ID 查询作物", description = "根据唯一的主键 ID 获取该农作物的详细信息")
    @GetMapping("/{id}") // 根据 ID 查询接口
    public Crop get(@PathVariable Long id) {
        return cropService.getById(id);
    }

    @Operation(summary = "查询所有作物", description = "获取当前数据库中登记的所有农作物列表")
    @GetMapping("/list") // 查询所有接口
    public List<Crop> list() {
        return cropService.getAll();
    }

    @Operation(summary = "更新作物信息", description = "根据 ID 修改已有的作物信息，包括名称、类别或生长周期")
    @PutMapping("/{id}") // 更新信息接口
    public String update(@PathVariable Long id, @RequestBody Crop crop) {
        crop.setId(id);
        cropService.update(crop);
        return "ID 为 " + id + " 的作物更新成功！";
    }

    @Operation(summary = "删除指定作物", description = "根据 ID 从系统中永久移除该农作物记录")
    @DeleteMapping("/{id}") // 删除接口
    public String delete(@PathVariable Long id) {
        cropService.delete(id);
        return "ID 为 " + id + " 的作物已被成功移除！";
    }
}