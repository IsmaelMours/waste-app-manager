package com.enviro.assessment.grad001.ismaelmours.Assessment.service.impl;

import com.enviro.assessment.grad001.ismaelmours.Assessment.dto.DisposalGuidelineDTO;
import com.enviro.assessment.grad001.ismaelmours.Assessment.entity.DisposalGuideline;
import com.enviro.assessment.grad001.ismaelmours.Assessment.entity.WasteCategory;
import com.enviro.assessment.grad001.ismaelmours.Assessment.exception.ResourceNotFoundException;
import com.enviro.assessment.grad001.ismaelmours.Assessment.mapper.DisposalGuidelineMapper;
import com.enviro.assessment.grad001.ismaelmours.Assessment.repository.DisposalGuidelineRepository;
import com.enviro.assessment.grad001.ismaelmours.Assessment.repository.WasteCategoryRepository;
import com.enviro.assessment.grad001.ismaelmours.Assessment.service.DisposalGuidelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisposalGuidelineServiceImpl implements DisposalGuidelineService {

    @Autowired
    private DisposalGuidelineRepository repository;

    @Autowired
    private WasteCategoryRepository wasteCategoryRepository;

    @Autowired
    private DisposalGuidelineMapper mapper;

    @Override
    public DisposalGuidelineDTO save(DisposalGuidelineDTO guidelineDTO) {
        DisposalGuideline guideline = mapper.toEntity(guidelineDTO);
        return mapper.toDTO(repository.save(guideline));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Disposal Guideline not found with id " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public List<DisposalGuidelineDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DisposalGuidelineDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Disposal Guideline not found with id " + id));
    }

    @Override
    public List<DisposalGuidelineDTO> findByWasteCategoryId(Long wasteCategoryId) {

        WasteCategory wasteCategory = wasteCategoryRepository.findById(wasteCategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Waste Category not found with id " + wasteCategoryId));

        return repository.findByWasteCategoryId(wasteCategoryId)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public DisposalGuidelineDTO addGuidelineByWasteCategoryId(Long wasteCategoryId, DisposalGuidelineDTO guidelineDTO) {

        WasteCategory wasteCategory = wasteCategoryRepository.findById(wasteCategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Waste Category not found with id " + wasteCategoryId));

        DisposalGuideline guideline = new DisposalGuideline();
        guideline.setWasteCategory(wasteCategory);
        guideline.setGuideline(guidelineDTO.guideline());
        DisposalGuideline savedGuideline = repository.save(guideline);
        return mapper.toDTO(savedGuideline);
    }

    @Override
    public DisposalGuidelineDTO updateDisposalGuideline(Long id, DisposalGuidelineDTO disposalGuidelineDTO) {
        DisposalGuideline existingGuideline = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disposal guideline not found with id " + id));

        existingGuideline.setGuideline(disposalGuidelineDTO.guideline());
        // The waste category is already set on the existing guideline, so we don't need to set it again.

        DisposalGuideline updatedGuideline = repository.save(existingGuideline);
        return mapper.toDTO(updatedGuideline);
    }
}