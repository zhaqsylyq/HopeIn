package com.zhaqsylyq.passengers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreferredLocation {
    private String label;
    private Double latitude;
    private Double longitude;
}
