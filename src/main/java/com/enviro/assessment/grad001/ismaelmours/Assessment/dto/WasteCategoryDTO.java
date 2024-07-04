package com.enviro.assessment.grad001.ismaelmours.Assessment.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record WasteCategoryDTO(
        Long id,
        @NotBlank(message = "Name is mandatory")
        String name
)
{
}
