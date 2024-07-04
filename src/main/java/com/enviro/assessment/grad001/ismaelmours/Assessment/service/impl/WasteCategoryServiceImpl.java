package com.enviro.assessment.grad001.ismaelmours.Assessment.service.impl;


import com.enviro.assessment.grad001.ismaelmours.Assessment.dto.*;

import com.enviro.assessment.grad001.ismaelmours.Assessment.entity.WasteCategory;
import com.enviro.assessment.grad001.ismaelmours.Assessment.exception.ResourceNotFoundException;

import com.enviro.assessment.grad001.ismaelmours.Assessment.mapper.DisposalGuidelineMapper;
import com.enviro.assessment.grad001.ismaelmours.Assessment.mapper.RecyclingTipMapper;
import com.enviro.assessment.grad001.ismaelmours.Assessment.mapper.WasteCategoryMapper;
import com.enviro.assessment.grad001.ismaelmours.Assessment.repository.DisposalGuidelineRepository;
import com.enviro.assessment.grad001.ismaelmours.Assessment.repository.RecyclingTipRepository;
import com.enviro.assessment.grad001.ismaelmours.Assessment.repository.WasteCategoryRepository;
import com.enviro.assessment.grad001.ismaelmours.Assessment.service.WasteCategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WasteCategoryServiceImpl implements WasteCategoryService {

    @Autowired
    private WasteCategoryRepository repository;

    @Autowired
    private WasteCategoryMapper mapper;

    @Autowired
    private DisposalGuidelineRepository disposalGuidelineRepository;

    @Autowired
    private DisposalGuidelineMapper disposalGuidelineMapper;

    @Autowired
    private RecyclingTipRepository recyclingTipRepository;

    @Autowired
    private RecyclingTipMapper recyclingTipMapper;

    @Override
    public List<WasteCategoryDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public String save(WasteCategoryDTO categoryDTO) {

       WasteCategory wasteCategory = mapper.toEntity(categoryDTO);
       repository.save(wasteCategory);
        return "Waste Category saved successfully!";
    }


    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Waste Category not found with id " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public WasteCategoryDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Waste Category not found with id " + id));
    }

    @Override
    public List<WasteCategoryWithGuidelinesDTO> findByWasteCategoryId(Long wasteCategoryId) {
        WasteCategory wasteCategory = repository.findById(wasteCategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Waste Category not found with id " + wasteCategoryId));

        List<DisposalGuidelineDTO> guidelines = disposalGuidelineRepository.findByWasteCategoryId(wasteCategoryId)
                .stream()
                .map(disposalGuidelineMapper::toDTO)
                .collect(Collectors.toList());

        return List.of(new WasteCategoryWithGuidelinesDTO(wasteCategory.getName(), guidelines));
    }

    @Override
    public List<WasteCategoryWithRecycleDTO> findByWasteCategoryByIdForTips(Long wasteCategoryId) {
        WasteCategory wasteCategory = repository.findById(wasteCategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Waste Category not found with id " + wasteCategoryId));
        List<RecyclingTipDTO> tipDTOS = recyclingTipRepository.findByWasteCategoryId(wasteCategoryId)
                .stream()
                .map(recyclingTipMapper::toDTO)
                .collect(Collectors.toList());
        return List.of(new WasteCategoryWithRecycleDTO(wasteCategory.getName(), tipDTOS));
    }

    @Override
    public WasteCategoryDTO updateCategory(Long id, WasteCategoryDTO categoryDTO) {
        Optional<WasteCategory> optionalWasteCategory = repository.findById(id);
        if (optionalWasteCategory.isEmpty()) {
            throw new IllegalArgumentException("Waste Category not found with id: " + id);
        }

        WasteCategory existingWasteCategory = optionalWasteCategory.get();
        existingWasteCategory.setName(categoryDTO.name()); // Use name() method from record

        // Optionally, you can use mapper to update other fields if needed
        // WasteCategory updatedWasteCategory = mapper.toEntity(categoryDTO);
        // existingWasteCategory.setName(updatedWasteCategory.getName());

        WasteCategory updatedCategory = repository.save(existingWasteCategory);
        return mapper.toDTO(updatedCategory); // Convert updated entity back to DTO
    }
}