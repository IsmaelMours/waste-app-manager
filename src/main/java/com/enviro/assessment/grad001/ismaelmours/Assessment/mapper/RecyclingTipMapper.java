package com.enviro.assessment.grad001.ismaelmours.Assessment.mapper;

import com.enviro.assessment.grad001.ismaelmours.Assessment.dto.RecyclingTipDTO;
import com.enviro.assessment.grad001.ismaelmours.Assessment.entity.RecyclingTip;
import org.springframework.stereotype.Component;

@Component
public class RecyclingTipMapper {

    public RecyclingTipDTO toDTO(RecyclingTip entity) {
        return new RecyclingTipDTO(
                entity.getId(),
                entity.getTip(),
                entity.getWasteCategory().getId()
        );
    }

    public RecyclingTip toEntity(RecyclingTipDTO dto) {
        RecyclingTip recyclingTip = new RecyclingTip();
        recyclingTip.setId(dto.id());
        recyclingTip.setTip(dto.tip());
        // wasteCategory needs to be set separately since it requires fetching from the database
        return recyclingTip;
    }
}
