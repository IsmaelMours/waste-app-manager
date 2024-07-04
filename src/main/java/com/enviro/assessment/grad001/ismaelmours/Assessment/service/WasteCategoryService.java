package com.enviro.assessment.grad001.ismaelmours.Assessment.service;

import com.enviro.assessment.grad001.ismaelmours.Assessment.dto.WasteCategoryDTO;
import com.enviro.assessment.grad001.ismaelmours.Assessment.dto.WasteCategoryWithGuidelinesDTO;
import com.enviro.assessment.grad001.ismaelmours.Assessment.dto.WasteCategoryWithRecycleDTO;
import com.enviro.assessment.grad001.ismaelmours.Assessment.entity.WasteCategory;
import org.springframework.stereotype.Service;

import java.util.List;


public interface WasteCategoryService {
    List<WasteCategoryDTO> findAll();
    String save(WasteCategoryDTO categoryDTO);
    void delete(Long id);
    WasteCategoryDTO findById(Long id);

    List<WasteCategoryWithGuidelinesDTO> findByWasteCategoryId(Long wasteCategoryId);
    List<WasteCategoryWithRecycleDTO> findByWasteCategoryByIdForTips(Long wasteCategoryId);
    WasteCategoryDTO updateCategory(Long id, WasteCategoryDTO wasteCategoryDTO) ;
}
