package com.rd.mappers;

import com.rd.DTO.request.MakeDTO;
import com.rd.entity.Make;

import java.util.ArrayList;
import java.util.List;

public class MakeMapper {

    public static MakeDTO buildDTO(Make make){
       return MakeDTO.builder()
               .id(make.getId())
               .name(make.getName())
               .build();
    }

    public static List<MakeDTO> buildListDTO(List<Make> makes){
        List<MakeDTO> makeDTOS = new ArrayList<>();
        makes.forEach(payment -> makeDTOS.add(buildDTO(payment)));
        return makeDTOS;
    }

    public static Make buildObject(MakeDTO makeDTO){
        return Make.builder()
                .name(makeDTO.getName())
                .build();
    }
}
