package com.zhaqsylyq.passengers.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "passengers")
public class Passenger extends BaseEntity{
    @Id
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private Double rating;
}
