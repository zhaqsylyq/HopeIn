package com.zhaqsylyq.drivers.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
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
    private String name;
    private String email;
    private String phoneNumber;
    private String vehicleType;
    private String vehiclePlateNumber;
    private Double rating;
}
