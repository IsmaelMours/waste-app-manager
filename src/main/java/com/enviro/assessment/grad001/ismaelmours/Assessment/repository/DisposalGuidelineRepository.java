package com.enviro.assessment.grad001.ismaelmours.Assessment.repository;

import com.enviro.assessment.grad001.ismaelmours.Assessment.dto.DisposalGuidelineDTO;
import com.enviro.assessment.grad001.ismaelmours.Assessment.entity.DisposalGuideline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisposalGuidelineRepository extends JpaRepository<DisposalGuideline, Long> {
    List<DisposalGuideline> findByWasteCategoryId(Long wasteCategoryId);
}
