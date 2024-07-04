package com.enviro.assessment.grad001.ismaelmours.Assessment.controller;

import com.enviro.assessment.grad001.ismaelmours.Assessment.dto.RecyclingTipDTO;
import com.enviro.assessment.grad001.ismaelmours.Assessment.dto.WasteCategoryDTO;
import com.enviro.assessment.grad001.ismaelmours.Assessment.dto.WasteCategoryWithGuidelinesDTO;
import com.enviro.assessment.grad001.ismaelmours.Assessment.dto.WasteCategoryWithRecycleDTO;
import com.enviro.assessment.grad001.ismaelmours.Assessment.entity.WasteCategory;
import com.enviro.assessment.grad001.ismaelmours.Assessment.exception.ResourceNotFoundException;
import com.enviro.assessment.grad001.ismaelmours.Assessment.service.WasteCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/waste-categories")
public class WasteCategoryController {

    @Autowired
    private WasteCategoryService service;

    @GetMapping("/all")
    public List<WasteCategoryDTO> getAll() {
        return service.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<String> create(@Valid @RequestBody WasteCategoryDTO categoryDTO) {
        return ResponseEntity.ok(service.save(categoryDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<WasteCategoryDTO> update(@PathVariable Long id, @Valid @RequestBody WasteCategoryDTO categoryDTO) {
        WasteCategoryDTO updatedCategory = service.updateCategory(id, categoryDTO);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WasteCategoryDTO> getById(@PathVariable Long id) {
        WasteCategoryDTO categoryDTO = service.findById(id);
        return ResponseEntity.ok(categoryDTO);
    }
    @GetMapping("/guidelines/{wasteCategoryId}")
    public ResponseEntity<WasteCategoryWithGuidelinesDTO> getGuidelinesByWasteCategoryId(@PathVariable Long wasteCategoryId) {
        List<WasteCategoryWithGuidelinesDTO> wasteCategoryWithGuidelines = service.findByWasteCategoryId(wasteCategoryId);
        if (wasteCategoryWithGuidelines.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(wasteCategoryWithGuidelines.get(0));
    }

    @GetMapping("/recycling/{wasteCategoryId}")
    public ResponseEntity<WasteCategoryWithRecycleDTO> getRecycclingByWasteCategory(@PathVariable Long wasteCategoryId, RecyclingTipDTO recyclingTipDTO){

        List<WasteCategoryWithRecycleDTO> wasteCategoryWithRecycleDTOS = service.findByWasteCategoryByIdForTips(wasteCategoryId);
        if (wasteCategoryWithRecycleDTOS.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(wasteCategoryWithRecycleDTOS.get(0));

    }
}