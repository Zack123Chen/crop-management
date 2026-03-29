package com.example.cropmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CropManagementApplication {

    // 💡 记住：这里必须有 public，它是 JVM 进入你程序的唯一通道
    public static void main(String[] args) {
        SpringApplication.run(CropManagementApplication.class, args);
    }

}