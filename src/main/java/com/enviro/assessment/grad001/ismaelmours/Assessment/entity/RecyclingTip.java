package com.enviro.assessment.grad001.ismaelmours.Assessment.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecyclingTip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String tip;

    @ManyToOne
    @JoinColumn(name = "waste_category_id", nullable = false)
    private WasteCategory wasteCategory;

}
