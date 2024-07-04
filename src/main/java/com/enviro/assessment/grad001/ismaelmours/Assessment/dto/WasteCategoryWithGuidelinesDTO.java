package com.enviro.assessment.grad001.ismaelmours.Assessment.dto;

import java.util.List;

public record WasteCategoryWithGuidelinesDTO(String name, List<DisposalGuidelineDTO> guidelines) {
}
