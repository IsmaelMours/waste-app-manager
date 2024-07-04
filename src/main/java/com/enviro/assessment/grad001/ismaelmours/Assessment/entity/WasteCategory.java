package com.enviro.assessment.grad001.ismaelmours.Assessment.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WasteCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "wasteCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DisposalGuideline> disposalGuidelines;

    @OneToMany(mappedBy = "wasteCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecyclingTip> recyclingTips;


}
