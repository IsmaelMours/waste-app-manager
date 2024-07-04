package com.enviro.assessment.grad001.ismaelmours.Assessment.controller;

import com.enviro.assessment.grad001.ismaelmours.Assessment.dto.DisposalGuidelineDTO;
import com.enviro.assessment.grad001.ismaelmours.Assessment.exception.ResourceNotFoundException;
import com.enviro.assessment.grad001.ismaelmours.Assessment.service.DisposalGuidelineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/disposal-guidelines")
public class DisposalGuidelineController {

    @Autowired
    private DisposalGuidelineService service;

    @GetMapping("/all")
    public List<DisposalGuidelineDTO> getAll() {
        return service.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<DisposalGuidelineDTO> create(@Valid @RequestBody DisposalGuidelineDTO guidelineDTO) {
        return ResponseEntity
                .ok(service.save(guidelineDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DisposalGuidelineDTO> update(@PathVariable Long id, @Valid @RequestBody DisposalGuidelineDTO guidelineDTO) {

        return ResponseEntity
                .ok(service.updateDisposalGuideline(id, guidelineDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisposalGuidelineDTO> getById(@PathVariable Long id) {
        DisposalGuidelineDTO guidelineDTO = service.findById(id);
        if (guidelineDTO == null) {
            throw new ResourceNotFoundException("Disposal Guideline not found with id " + id);
        }
        return ResponseEntity.ok(guidelineDTO);
    }

    @GetMapping("/waste-category/{wasteCategoryId}")
    public ResponseEntity<List<DisposalGuidelineDTO>> getByWasteCategoryId(@PathVariable Long wasteCategoryId) {
        List<DisposalGuidelineDTO> guidelines = service.findByWasteCategoryId(wasteCategoryId);
        return ResponseEntity.ok(guidelines);
    }

    @PostMapping("/disposal-guideline/{wasteCategoryId}")
    public ResponseEntity<DisposalGuidelineDTO> addGuidelineByWasteCategoryId(@PathVariable Long wasteCategoryId, @Valid @RequestBody DisposalGuidelineDTO guidelineDTO) {
        DisposalGuidelineDTO createdGuideline = service.addGuidelineByWasteCategoryId(wasteCategoryId, guidelineDTO);
        return ResponseEntity.ok(createdGuideline);
    }
}
