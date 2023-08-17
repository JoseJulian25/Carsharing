package com.rd.service;

import com.rd.DTO.request.ModelDTO;

import java.util.List;

public interface ModelService {
    ModelDTO findById(Integer id);
    ModelDTO save(ModelDTO modelDTO, Integer makeId);
    String delete(Integer id);
    ModelDTO updateModel(ModelDTO modelDTO, Integer id);
    List<ModelDTO> findAll();
}
