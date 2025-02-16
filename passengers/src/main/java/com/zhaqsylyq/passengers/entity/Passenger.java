package com.zhaqsylyq.passengers.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "passengers")
public class Passenger extends BaseEntity{
    @Id
    private String id;

    @Indexed(unique = true)
    private String passengerId; // Publicly exposed, human-readable unique ID

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    private List<PreferredLocation> preferredLocations; // List of preferred locations>

    private Ratings ratings;
}
