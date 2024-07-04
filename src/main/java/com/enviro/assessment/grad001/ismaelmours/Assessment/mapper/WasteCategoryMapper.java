package com.enviro.assessment.grad001.ismaelmours.Assessment.mapper;

import com.enviro.assessment.grad001.ismaelmours.Assessment.dto.WasteCategoryDTO;
import com.enviro.assessment.grad001.ismaelmours.Assessment.entity.WasteCategory;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class WasteCategoryMapper {



    public WasteCategoryDTO toDTO(WasteCategory entity) {
        return new WasteCategoryDTO(
                entity.getId(),
                entity.getName()
        );
    }

    public WasteCategory toEntity(WasteCategoryDTO dto) {
        WasteCategory wasteCategory = new WasteCategory();
        wasteCategory.setId(dto.id());
        wasteCategory.setName(dto.name());

        return wasteCategory;
    }
}