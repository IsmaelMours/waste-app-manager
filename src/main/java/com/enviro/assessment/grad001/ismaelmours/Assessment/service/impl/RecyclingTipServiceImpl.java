package com.enviro.assessment.grad001.ismaelmours.Assessment.service.impl;

import com.enviro.assessment.grad001.ismaelmours.Assessment.dto.RecyclingTipDTO;
import com.enviro.assessment.grad001.ismaelmours.Assessment.entity.RecyclingTip;
import com.enviro.assessment.grad001.ismaelmours.Assessment.entity.WasteCategory;
import com.enviro.assessment.grad001.ismaelmours.Assessment.exception.ResourceNotFoundException;
import com.enviro.assessment.grad001.ismaelmours.Assessment.mapper.RecyclingTipMapper;
import com.enviro.assessment.grad001.ismaelmours.Assessment.repository.RecyclingTipRepository;
import com.enviro.assessment.grad001.ismaelmours.Assessment.repository.WasteCategoryRepository;
import com.enviro.assessment.grad001.ismaelmours.Assessment.service.RecyclingTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecyclingTipServiceImpl implements RecyclingTipService {

    @Autowired
    private RecyclingTipRepository repository;

    @Autowired
    private RecyclingTipMapper mapper;

    @Autowired
    private WasteCategoryRepository wasteCategoryRepository;

    @Override
    public List<RecyclingTipDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public RecyclingTipDTO save(RecyclingTipDTO tipDTO) {
        RecyclingTip tip = mapper.toEntity(tipDTO);
        return mapper.toDTO(repository.save(tip));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recycling Tip not found with id " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public RecyclingTipDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Recycling Tip not found with id " + id));
    }

    @Override
    public List<RecyclingTipDTO> findByWasteCategoryId(Long wasteCategoryId) {
        return repository.findByWasteCategoryId(wasteCategoryId).stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public RecyclingTipDTO updateRecyclingTips(Long id, RecyclingTipDTO recyclingTipDTO) {
        RecyclingTip existingTips = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Recycling tips not found with id " + id));
        existingTips.setTip(recyclingTipDTO.tip());
        RecyclingTip updateTips = repository.save(existingTips);
        return mapper.toDTO(updateTips);
    }

    @Override
    public RecyclingTipDTO addTipByWasteCategoryId(Long wasteCategoryId, RecyclingTipDTO tipDTO) {
        WasteCategory wasteCategory = wasteCategoryRepository.findById(wasteCategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Waste Category not found with id " + wasteCategoryId));

        RecyclingTip recyclingTip = new RecyclingTip();
        recyclingTip.setWasteCategory(wasteCategory);
        recyclingTip.setTip(tipDTO.tip());
        RecyclingTip saveTips = repository.save(recyclingTip);
        return mapper.toDTO(saveTips);
    }
}