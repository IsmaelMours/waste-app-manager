package com.enviro.assessment.grad001.ismaelmours.Assessment.dto;

import com.enviro.assessment.grad001.ismaelmours.Assessment.entity.WasteCategory;
import jakarta.validation.constraints.NotBlank;

public record DisposalGuidelineDTO(
        Long id,
        @NotBlank(message = "Guideline is mandatory")
        String guideline,
        Long wasteCategoryId
       ) {
}
