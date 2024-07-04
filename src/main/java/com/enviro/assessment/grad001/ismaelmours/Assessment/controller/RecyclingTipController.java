package com.enviro.assessment.grad001.ismaelmours.Assessment.controller;

import com.enviro.assessment.grad001.ismaelmours.Assessment.dto.RecyclingTipDTO;
import com.enviro.assessment.grad001.ismaelmours.Assessment.exception.ResourceNotFoundException;
import com.enviro.assessment.grad001.ismaelmours.Assessment.service.RecyclingTipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/recycling-tips")
public class RecyclingTipController {

    @Autowired
    private RecyclingTipService service;

    @GetMapping("/all")
    public List<RecyclingTipDTO> getAll() {
        return service.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<RecyclingTipDTO> create(@Valid @RequestBody RecyclingTipDTO tipDTO) {
        return ResponseEntity.ok(service.save(tipDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RecyclingTipDTO> update(@PathVariable Long id, @Valid @RequestBody RecyclingTipDTO tipDTO) {

        return ResponseEntity.ok(service.updateRecyclingTips(id, tipDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecyclingTipDTO> getById(@PathVariable Long id) {
        RecyclingTipDTO tipDTO = service.findById(id);
        return ResponseEntity.ok(tipDTO);
    }

    @GetMapping("/waste-category/{wasteCategoryId}")
    public ResponseEntity<List<RecyclingTipDTO>> getByWasteCategoryId(@PathVariable Long wasteCategoryId) {
        List<RecyclingTipDTO> tips = service.findByWasteCategoryId(wasteCategoryId);
        return ResponseEntity.ok(tips);
    }

    @PostMapping("/waste-category/{wasteCategoryId}")
    public ResponseEntity<RecyclingTipDTO> addTipByWasteCategoryId(@PathVariable Long wasteCategoryId, @Valid @RequestBody RecyclingTipDTO tipDTO) {
        RecyclingTipDTO createdTip = service.addTipByWasteCategoryId(wasteCategoryId, tipDTO);
        return ResponseEntity.ok(createdTip);
    }
}
