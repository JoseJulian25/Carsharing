package com.rd.service.impl;

import com.rd.DTO.request.ModelDTO;
import com.rd.entity.Make;
import com.rd.entity.Model;
import com.rd.exception.DataNotFoundException;
import com.rd.mappers.ModelMapper;
import com.rd.repository.MakeRepository;
import com.rd.repository.ModelRepository;
import com.rd.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final MakeRepository makeRepository;

    @Override
    public ModelDTO findById(Integer id) {
        Model model = modelRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Model not found"));
        return ModelMapper.buildDTO(model);
    }

    @Override
    public ModelDTO save(ModelDTO modelDTO, Integer makeId) {
        Make make = makeRepository.findById(makeId).orElseThrow(() -> new IllegalStateException("make not found"));
        Model model = ModelMapper.buildObject(modelDTO, make);
        return ModelMapper.buildDTO(modelRepository.save(model));
    }

    @Override
    public String delete(Integer id) {
        Model model = modelRepository.findById(id).orElseThrow(() -> new IllegalStateException("model not found"));
        modelRepository.delete(model);
        return "Deleted successfully";
    }

    @Override
    public ModelDTO updateModel(ModelDTO modelDTO, Integer id) {
        Model model = modelRepository.findById(id).orElseThrow(() -> new IllegalStateException("Model not found"));
        model.setName(modelDTO.getName());
        modelRepository.save(model);
        return ModelMapper.buildDTO(model);
    }

    @Override
    public List<ModelDTO> findAll() {
        return ModelMapper.buildListDTO(modelRepository.findAll());
    }
}
