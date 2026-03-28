package com.example.cropmanagement.controller;

import com.example.cropmanagement.entity.Crop;
import com.example.cropmanagement.entity.Result; // 确保你已经创建了这个 Result 类
import com.example.cropmanagement.service.CropService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
    public Result<String> add(@Valid @RequestBody Crop crop) {
        cropService.addCrop(crop);
        return Result.success("添加成功！生成的 ID 是：" + crop.getId());
    }

    @Operation(summary = "按 ID 查询作物", description = "根据唯一的主键 ID 获取该农作物的详细信息")
    @GetMapping("/{id}") // 根据 ID 查询接口
    public Result<Crop> get(@PathVariable Long id) {
        Crop crop = cropService.getById(id);
        // 如果查不到数据，虽然 Service 可能返回 null，但套上 Result 依然很优雅
        return Result.success(crop);
    }

    @Operation(summary = "查询所有作物", description = "获取当前数据库中登记的所有农作物列表")
    @GetMapping("/list") // 查询所有接口
    public Result<List<Crop>> list() {
        List<Crop> allCrops = cropService.getAll();
        return Result.success(allCrops); // 即使列表为空，也会返回 { "code": 200, "data": [] }
    }

    @Operation(summary = "更新作物信息", description = "根据 ID 修改已有的作物信息")
    @PutMapping("/{id}") // 更新信息接口
    public Result<String> update(@PathVariable Long id, @RequestBody Crop crop) {
        crop.setId(id);
        cropService.update(crop);
        return Result.success("ID 为 " + id + " 的作物更新成功！");
    }

    @Operation(summary = "删除指定作物", description = "根据 ID 从系统中永久移除该农作物记录")
    @DeleteMapping("/{id}") // 删除接口
    public Result<String> delete(@PathVariable Long id) {
        cropService.delete(id);
        return Result.success("ID 为 " + id + " 的作物已被成功移除！");
    }
}