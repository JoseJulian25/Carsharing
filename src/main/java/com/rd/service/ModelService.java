package com.rd.service;

import com.rd.DTO.request.ModelRequestDTO;
import com.rd.DTO.response.ModelResponseDTO;

import java.util.List;

public interface ModelService {
    ModelResponseDTO findById(Integer id);
    ModelResponseDTO save(ModelRequestDTO modelRequestDTO, Integer makeId);
    String delete(Integer id);
    ModelResponseDTO updateModel(ModelRequestDTO modelRequestDTO, Integer id);
    List<ModelResponseDTO> findAll();
    List<ModelResponseDTO> findByMakeId(Integer makeId);
}
