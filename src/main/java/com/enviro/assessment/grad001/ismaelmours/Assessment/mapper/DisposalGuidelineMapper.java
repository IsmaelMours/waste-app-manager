package com.enviro.assessment.grad001.ismaelmours.Assessment.mapper;

import com.enviro.assessment.grad001.ismaelmours.Assessment.dto.DisposalGuidelineDTO;
import com.enviro.assessment.grad001.ismaelmours.Assessment.entity.DisposalGuideline;
import com.enviro.assessment.grad001.ismaelmours.Assessment.entity.WasteCategory;
import org.springframework.stereotype.Component;

@Component
public class DisposalGuidelineMapper {

    public DisposalGuidelineDTO toDTO(DisposalGuideline entity) {
        return new DisposalGuidelineDTO(
                entity.getId(),
                entity.getGuideline(),
                entity.getWasteCategory().getId()
        );
    }

    public DisposalGuideline toEntity(DisposalGuidelineDTO dto) {
        DisposalGuideline disposalGuideline = new DisposalGuideline();
        disposalGuideline.setGuideline(dto.guideline());
        return disposalGuideline;
    }
}
