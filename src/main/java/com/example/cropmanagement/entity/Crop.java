package com.example.cropmanagement.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data // 这个注解是 Lombok 提供的，它会自动帮你生成 Getter/Setter 方法，让代码非常清爽
public class Crop {
    private Long id;
    private String name;
    private String category;
    private Integer growth_cycle;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
}