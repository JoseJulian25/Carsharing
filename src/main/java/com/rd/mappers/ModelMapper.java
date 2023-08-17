package com.rd.mappers;

import com.rd.DTO.request.ModelDTO;
import com.rd.entity.Make;
import com.rd.entity.Model;

import java.util.ArrayList;
import java.util.List;

public class ModelMapper {

    public static ModelDTO buildDTO(Model model){
        return ModelDTO.builder()
                .id(model.getId())
                .name(model.getName())
                .build();
    }

    public static List<ModelDTO> buildListDTO(List<Model> models){
        List<ModelDTO> modelDTOS = new ArrayList<>();
        models.forEach(payment -> modelDTOS.add(buildDTO(payment)));
        return modelDTOS;
    }

    public static Model buildObject(ModelDTO modelDTO, Make make){
        return Model.builder()
                .name(modelDTO.getName())
                .make(make)
                .build();
    }
}
