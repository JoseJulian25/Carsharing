package com.rd.controller;

import com.rd.DTO.request.ModelDTO;
import com.rd.service.ModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Model Controller", description = "Endpoints for managing models of vehicles")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/model")
public class ModelController {
    private final ModelService modelService;

    @Operation(summary = "Find model by id", description = "Requires roles: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<ModelDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok(modelService.findById(id));
    }

    @Operation(summary = "Find all models", description = "Requires roles: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @GetMapping
    public ResponseEntity<List<ModelDTO>> findAll(){
        return ResponseEntity.ok(modelService.findAll());
    }

    @Operation(summary = "save model", description = "Requires rol: ADMIN")
    @Secured("ADMIN")
    @PostMapping("/make/{makeId}")
    public ResponseEntity<ModelDTO> saveModel(@RequestBody ModelDTO modelDTO, @PathVariable Integer makeId){
        return ResponseEntity.ok(modelService.save(modelDTO, makeId));
    }

    @Operation(summary = "update model", description = "Requires rol: ADMIN")
    @Secured("ADMIN")
    @PutMapping
    public ResponseEntity<ModelDTO> updateModel(@RequestBody ModelDTO modelDTO, @RequestParam Integer id){
        return ResponseEntity.ok(modelService.updateModel(modelDTO, id));
    }

    @Operation(summary = "delete model", description = "Requires rol: ADMIN")
    @Secured("ADMIN")
    @DeleteMapping
    public ResponseEntity<String> deleteModel(@RequestParam Integer id){
        return ResponseEntity.ok(modelService.delete(id));
    }
}
