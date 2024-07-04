package com.enviro.assessment.grad001.ismaelmours.Assessment.service;

import com.enviro.assessment.grad001.ismaelmours.Assessment.dto.RecyclingTipDTO;
import com.enviro.assessment.grad001.ismaelmours.Assessment.entity.RecyclingTip;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RecyclingTipService {
    List<RecyclingTipDTO> findAll();
    RecyclingTipDTO save(RecyclingTipDTO tipDTO);
    void delete(Long id);
    RecyclingTipDTO findById(Long id);
    List<RecyclingTipDTO> findByWasteCategoryId(Long wasteCategoryId);
    RecyclingTipDTO updateRecyclingTips(Long id, RecyclingTipDTO recyclingTipDTO);
    RecyclingTipDTO addTipByWasteCategoryId(Long wasteCategoryId, RecyclingTipDTO tipDTO);  // New method
}
