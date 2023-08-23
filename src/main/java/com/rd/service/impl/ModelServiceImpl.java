package com.rd.service.impl;

import com.rd.DTO.request.ModelRequestDTO;
import com.rd.DTO.response.ModelResponseDTO;
import com.rd.entity.Make;
import com.rd.entity.Model;
import com.rd.exception.DataNotFoundException;
import com.rd.mappers.ModelMapper;
import com.rd.repository.MakeRepository;
import com.rd.repository.ModelRepository;
import com.rd.service.ModelService;
import com.rd.utils.ListValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final MakeRepository makeRepository;

    @Override
    public ModelResponseDTO findById(Integer id) {
        Model model = modelRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Model not found"));
        return ModelMapper.buildDTO(model);
    }

    @Override
    public ModelResponseDTO save(ModelRequestDTO modelRequestDTO, Integer makeId) {
        Make make = makeRepository.findById(makeId).orElseThrow(() -> new IllegalStateException("make not found"));
        Model model = ModelMapper.buildObject(modelRequestDTO, make);
        return ModelMapper.buildDTO(modelRepository.save(model));
    }

    @Override
    public String delete(Integer id) {
        Model model = modelRepository.findById(id).orElseThrow(() -> new IllegalStateException("model not found"));
        modelRepository.delete(model);
        return "Deleted successfully";
    }

    @Override
    public ModelResponseDTO updateModel(ModelRequestDTO modelRequestDTO, Integer id) {
        Model model = modelRepository.findById(id).orElseThrow(() -> new IllegalStateException("Model not found"));
        model.setName(modelRequestDTO.getName());
        modelRepository.save(model);
        return ModelMapper.buildDTO(model);
    }

    @Override
    public List<ModelResponseDTO> findAll() {
        return ModelMapper.buildListDTO(modelRepository.findAll());
    }

    @Override
    public List<ModelResponseDTO> findByMakeId(Integer makeId) {
        List<Model> models = modelRepository.findByMake_Id(makeId);
        ListValidation.checkNonEmptyList(models, () -> "Models not found");
        return ModelMapper.buildListDTO(models);
    }
}
