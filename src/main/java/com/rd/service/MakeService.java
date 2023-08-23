package com.rd.service;

import com.rd.DTO.request.MakeDTO;
import com.rd.entity.Make;

import java.util.List;

public interface MakeService {
    MakeDTO findById(Integer id);
    MakeDTO save(MakeDTO make);
    String delete(Integer id);
    MakeDTO updateMake(MakeDTO make, Integer id);
    List<MakeDTO> findAll();
}
