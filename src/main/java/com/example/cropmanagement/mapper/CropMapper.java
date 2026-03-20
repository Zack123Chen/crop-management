package com.example.cropmanagement.mapper;

import com.example.cropmanagement.entity.Crop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper // 这是一个关键的“身份卡”，没有它 MyBatis 就不干活
public interface CropMapper {

    @Select("SELECT * FROM crop") // 告诉采购员：去 crop 表里把所有东西搬出来
    List<Crop> findAll();
}