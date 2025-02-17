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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false)
    private String passengerId;

    private String driverId;

    @Enumerated(EnumType.STRING)
    private TripStatus status; // REQUESTED, ACCEPTED, ON_THE_WAY, COMPLETED, CANCELLED

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "start_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "start_longitude")),
            @AttributeOverride(name = "address", column = @Column(name = "start_address"))
    })
    private Location startLocation;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "end_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "end_longitude")),
            @AttributeOverride(name = "address", column = @Column(name = "end_address"))
    })
    private Location endLocation;

    private BigDecimal fareAmount;
}
