package com.rd.controller;

import com.rd.DTO.request.ModelRequestDTO;
import com.rd.DTO.response.ModelResponseDTO;
import com.rd.service.ModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Validated
@Tag(name = "Model Controller", description = "Endpoints for managing models of vehicles")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/model")
public class ModelController {
    private final ModelService modelService;

    @Operation(summary = "Find model by id", description = "Requires roles: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<ModelResponseDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok(modelService.findById(id));
    }

    @Operation(summary = "Find model by makeID", description = "Requires roles: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @GetMapping("/make/{makeId}")
    public ResponseEntity<List<ModelResponseDTO>> findByMakeId(@PathVariable Integer makeId){
        return ResponseEntity.ok(modelService.findByMakeId(makeId));
    }

    @Operation(summary = "Find all models", description = "Requires roles: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @GetMapping
    public ResponseEntity<List<ModelResponseDTO>> findAll(){
        return ResponseEntity.ok(modelService.findAll());
    }

    @Operation(summary = "save model", description = "Requires rol: ADMIN")
    @Secured("ADMIN")
    @PostMapping("/make/{makeId}")
    public ResponseEntity<ModelResponseDTO> saveModel(@RequestBody @Valid ModelRequestDTO modelRequestDTO, @PathVariable Integer makeId){
        return ResponseEntity.ok(modelService.save(modelRequestDTO, makeId));
    }

    @Operation(summary = "update model", description = "Requires rol: ADMIN")
    @Secured("ADMIN")
    @PutMapping
    public ResponseEntity<ModelResponseDTO> updateModel(@RequestBody @Valid ModelRequestDTO modelRequestDTO, @RequestParam Integer id){
        return ResponseEntity.ok(modelService.updateModel(modelRequestDTO, id));
    }

    @Operation(summary = "delete model", description = "Requires rol: ADMIN")
    @Secured("ADMIN")
    @DeleteMapping
    public ResponseEntity<String> deleteModel(@RequestParam Integer id){
        return ResponseEntity.ok(modelService.delete(id));
    }
}
