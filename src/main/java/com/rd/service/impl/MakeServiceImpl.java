package com.rd.service.impl;

import com.rd.DTO.request.MakeDTO;
import com.rd.entity.Make;
import com.rd.exception.DataNotFoundException;
import com.rd.mappers.MakeMapper;
import com.rd.repository.MakeRepository;
import com.rd.service.MakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MakeServiceImpl implements MakeService {
    private final MakeRepository makeRepository;

    @Override
    public MakeDTO findById(Integer id) {
        Make make = makeRepository.findById(id).orElseThrow(()-> new DataNotFoundException("Make not found"));
        return MakeMapper.buildDTO(make);
    }

    @Override
    public MakeDTO save(MakeDTO makeDTO) {
        Make make = MakeMapper.buildObject(makeDTO);
        return MakeMapper.buildDTO(makeRepository.save(make));
    }

    @Override
    public String delete(Integer id) {
        Make make = makeRepository.findById(id).orElseThrow(() -> new IllegalStateException("make not found"));
        makeRepository.save(make);
        return "Deleted successfully";
    }

    @Override
    public MakeDTO updateMake(MakeDTO make, Integer id) {
        Make makeSaved = makeRepository.findById(id).orElseThrow(() -> new IllegalStateException("Make not found"));
        makeSaved.setName(make.getName());
        return MakeMapper.buildDTO(makeRepository.save(makeSaved));
    }

    @Override
    public List<MakeDTO> findAll() {
        return MakeMapper.buildListDTO(makeRepository.findAll());
    }
}
