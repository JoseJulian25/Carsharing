package com.rd.controller;

import com.rd.DTO.request.MakeDTO;
import com.rd.service.MakeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Tag(name= "Make Controller", description = "Endpoints for managing Makes of vehicles")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/make")
public class MakeController {
    private final MakeService makeService;

    @Operation(summary = "Find make by id", description = "Requires roles: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<MakeDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok(makeService.findById(id));
    }

    @Operation(summary = "Find all makes", description = "Requires roles: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @GetMapping()
    public ResponseEntity<List<MakeDTO>> findAll(){
        return ResponseEntity.ok(makeService.findAll());
    }

    @Operation(summary = "Save make", description = "Requires roles: ADMIN")
    @Secured("ADMIN")
    @PostMapping
    public ResponseEntity<MakeDTO> saveMake(@RequestBody @Valid MakeDTO makeDTO){
        return new ResponseEntity<>(makeService.save(makeDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete make", description = "Requires rol: ADMIN")
    @Secured("ADMIN")
    @DeleteMapping
    public ResponseEntity<String> deleteMake(@RequestParam Integer id){
        return ResponseEntity.ok(makeService.delete(id));
    }

    @Operation(summary = "update make", description = "Requires rol: ADMIN")
    @Secured("ADMIN")
    @PutMapping
    public ResponseEntity<MakeDTO> updateMake(@RequestBody @Valid MakeDTO makeDTO, @RequestParam Integer id){
        return ResponseEntity.ok(makeService.updateMake(makeDTO, id));
    }
}
