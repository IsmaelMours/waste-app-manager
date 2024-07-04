package com.enviro.assessment.grad001.ismaelmours.Assessment.service;

import com.enviro.assessment.grad001.ismaelmours.Assessment.dto.DisposalGuidelineDTO;
import com.enviro.assessment.grad001.ismaelmours.Assessment.entity.DisposalGuideline;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DisposalGuidelineService {
    List<DisposalGuidelineDTO> findAll();
    DisposalGuidelineDTO save(DisposalGuidelineDTO guidelineDTO);
    void delete(Long id);
    DisposalGuidelineDTO findById(Long id);
    List<DisposalGuidelineDTO> findByWasteCategoryId(Long wasteCategoryId);
    DisposalGuidelineDTO addGuidelineByWasteCategoryId(Long wasteCategoryId, DisposalGuidelineDTO guidelineDTO);
    DisposalGuidelineDTO updateDisposalGuideline(Long id, DisposalGuidelineDTO disposalGuidelineDTO);
}
