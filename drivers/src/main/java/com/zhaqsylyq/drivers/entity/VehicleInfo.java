package com.zhaqsylyq.drivers.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleInfo {

    @NotEmpty(message = "Vehicle make should not be empty")
    @Size(min = 2, max = 50, message = "Vehicle make should be between 2 and 50 characters")
    @Pattern(regexp = "[a-zA-Z\\s]+", message = "Vehicle make should only contain letters and spaces")
    private String make;

    @NotEmpty(message = "Vehicle model should not be empty")
    @Size(min = 2, max = 50, message = "Vehicle model should be between 2 and 50 characters")
    @Pattern(regexp = "[a-zA-Z0-9\\s]+", message = "Vehicle model should only contain letters, numbers, and spaces")
    private String model;

    @NotEmpty(message = "Vehicle plate number should not be empty")
    @Pattern(regexp = "[A-Z0-9]{6}", message = "Vehicle plate number should be exactly 6 characters (uppercase letters & numbers)")
    private String plateNumber;
}
