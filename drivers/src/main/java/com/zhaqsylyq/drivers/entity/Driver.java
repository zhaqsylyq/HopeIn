package com.zhaqsylyq.drivers.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "drivers")
public class Driver extends BaseEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String driverId; // Exposed, human-readable driver identifier
    private String name;
    private String email;
    private String phoneNumber;
    private VehicleInfo vehicleInfo;
    private String status;
    private Ratings ratings;
}
