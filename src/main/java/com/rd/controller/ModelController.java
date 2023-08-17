package com.rd.controller;

import com.rd.DTO.request.ModelDTO;
import com.rd.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/model")
public class ModelController {
    private final ModelService modelService;

    @GetMapping("/{id}")
    public ResponseEntity<ModelDTO> findById(@PathVariable Integer id){
        return new ResponseEntity<>(modelService.findById(id), HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<ModelDTO>> findAll(){
        return new ResponseEntity<>(modelService.findAll(), HttpStatus.FOUND);
    }

    @PostMapping("/make/{makeId}")
    public ResponseEntity<ModelDTO> saveModel(@RequestBody ModelDTO modelDTO, @PathVariable Integer makeId){
        return ResponseEntity.ok(modelService.save(modelDTO, makeId));
    }

    @PutMapping
    public ResponseEntity<ModelDTO> updateModel(@RequestBody ModelDTO modelDTO, @RequestParam Integer id){
        return ResponseEntity.ok(modelService.updateModel(modelDTO, id));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteModel(@RequestParam Integer id){
        return ResponseEntity.ok(modelService.delete(id));
    }
}
