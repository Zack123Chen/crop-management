package com.example.cropmanagement.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDateTime;

@Data // 这个注解是 Lombok 提供的，它会自动帮你生成 Getter/Setter 方法，让代码非常清爽
public class Crop {
    private Long id;
    @NotBlank(message = "农作物名称不能为空")
    @Size(max = 50, message = "名称长度不能超过 50 个字符")
    private String name;

    @NotBlank(message = "农作物类别不能为空")
    private String category;

    @NotNull(message = "生长周期不能为空")
    @Min(value = 1, message = "生长周期至少为 1 天")
    private Integer growth_cycle;

    private LocalDateTime create_time;
    private LocalDateTime update_time;
}