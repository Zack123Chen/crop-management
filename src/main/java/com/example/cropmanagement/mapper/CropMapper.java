package com.example.cropmanagement.mapper;

import com.example.cropmanagement.entity.Crop;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface CropMapper {
    @Insert("INSERT INTO crop(name, category, growth_cycle) VALUES(#{name}, #{category}, #{growth_cycle})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Crop crop);

    @Select("SELECT * FROM crop WHERE id = #{id}")
    Crop findById(Long id);

    @Select("SELECT * FROM crop")
    List<Crop> findAll();

    @Update("UPDATE crop SET name=#{name}, category=#{category}, growth_cycle=#{growth_cycle} WHERE id=#{id}")
    int update(Crop crop);

    @Delete("DELETE FROM crop WHERE id = #{id}")
    int deleteById(Long id);
}