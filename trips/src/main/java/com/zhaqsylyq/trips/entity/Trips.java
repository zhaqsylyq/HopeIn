package com.zhaqsylyq.trips.entity;

import com.zhaqsylyq.trips.constants.TripStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Trips extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

//    @Column(nullable = false)
    private String passengerId;

    private String driverId;

    @Enumerated(EnumType.STRING)
    private TripStatus status; // REQUESTED, ACCEPTED, ON_THE_WAY, COMPLETED, CANCELLED

    private LocalDateTime startTime;

    private LocalDateTime endTime;

//    @Column(columnDefinition = "JSON")
    private String startLocation;

//    @Column(columnDefinition = "JSON")
    private String endLocation;

    private BigDecimal fareAmount;
}
