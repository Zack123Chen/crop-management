package com.example.cropmanagement.service;

import com.example.cropmanagement.entity.Crop;
import com.example.cropmanagement.mapper.CropMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CropService {
    @Autowired
    private CropMapper cropMapper; // 呼叫仓库管理员

    public void addCrop(Crop crop) { cropMapper.insert(crop); }
    public Crop getById(Long id) { return cropMapper.findById(id); }
    public List<Crop> getAll() { return cropMapper.findAll(); }
    public void update(Crop crop) { cropMapper.update(crop); }
    public void delete(Long id) { cropMapper.deleteById(id); }
}