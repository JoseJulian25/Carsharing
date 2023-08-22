package com.rd.mappers;

import com.rd.DTO.request.ModelRequestDTO;
import com.rd.DTO.response.ModelResponseDTO;
import com.rd.entity.Make;
import com.rd.entity.Model;
import java.util.List;

public class ModelMapper {

    public static ModelResponseDTO buildDTO(Model model){
        return ModelResponseDTO.builder()
                .id(model.getId())
                .name(model.getName())
                .make(MakeMapper.buildDTO(model.getMake()))
                .build();
    }

    public static List<ModelResponseDTO> buildListDTO(List<Model> models){
        return models.stream().map(ModelMapper::buildDTO).toList();
    }

    public static Model buildObject(ModelRequestDTO modelRequestDTO, Make make){
        return Model.builder()
                .name(modelRequestDTO.getName())
                .make(make)
                .build();
    }
}
