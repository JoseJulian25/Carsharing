package com.rd.controller;

import com.rd.DTO.request.MakeDTO;
import com.rd.service.MakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/make")
public class MakeController {
    private final MakeService makeService;

    @GetMapping("/{id}")
    public ResponseEntity<MakeDTO> findById(@PathVariable Integer id){
        return new ResponseEntity<>(makeService.findById(id), HttpStatus.FOUND);
    }

    @GetMapping()
    public ResponseEntity<List<MakeDTO>> findAll(){
        return new ResponseEntity<>(makeService.findAll(), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<MakeDTO> saveMake(@RequestBody MakeDTO makeDTO){
        return new ResponseEntity<>(makeService.save(makeDTO), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteMake(@RequestParam Integer id){
        return ResponseEntity.ok(makeService.delete(id));
    }

    @PutMapping
    public ResponseEntity<MakeDTO> updateMake(@RequestBody MakeDTO makeDTO, @RequestParam Integer id){
        return ResponseEntity.ok(makeService.updateMake(makeDTO, id));
    }
}
