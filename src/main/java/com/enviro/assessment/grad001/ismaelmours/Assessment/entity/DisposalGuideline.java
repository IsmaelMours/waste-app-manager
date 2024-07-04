package com.enviro.assessment.grad001.ismaelmours.Assessment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisposalGuideline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String guideline;

    @ManyToOne
    @JoinColumn(name = "waste_category_id", nullable = false)
    private WasteCategory wasteCategory;
}
