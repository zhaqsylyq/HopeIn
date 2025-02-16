package com.zhaqsylyq.passengers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ratings {
    private double rating;
    private int numberOfRides;
}
