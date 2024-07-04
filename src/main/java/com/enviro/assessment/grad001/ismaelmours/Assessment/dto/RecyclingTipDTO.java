package com.enviro.assessment.grad001.ismaelmours.Assessment.dto;

import jakarta.validation.constraints.NotBlank;

public record RecyclingTipDTO(
        Long id,
        @NotBlank(message = "Tip is mandatory")
        String tip,
        Long wasteCategoryId
) {
}