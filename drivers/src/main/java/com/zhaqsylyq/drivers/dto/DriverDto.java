package com.zhaqsylyq.drivers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(
    name = "Driver",
    description = "Schema to hold driver information"
)
public class DriverDto {
    @Schema(
        description = "Name of the Driver",
        example = "John Doe"
    )
    @NotEmpty(message = "Name should not be empty")
    @NotNull
    @Size(min = 3, max = 50, message = "Name should be between 3 and 50 characters")
    private String name;

    @Schema(
        description = "Email of the Driver",
        example = "0qoZ6@example.com"
    )
    @NotEmpty(message = "Email should not be empty")
    @NotNull
    @Email(message = "Email should be in format: 0qoZ6@example.com")
    private String email;

    @Schema(
        description = "Phone number of the Driver",
        example = "1234567890"
    )
    @NotEmpty(message = "Phone number should not be empty")
    @NotNull
    @Pattern(regexp = "[0-9]{10}", message = "Phone number should be 10 digits")
    private String phoneNumber;

    @Schema(
        description = "Vehicle type of the Driver",
        example = "Sedan"
    )
    @NotEmpty(message = "Vehicle type should not be empty")
    @NotNull
    @Size(min = 3, max = 50, message = "Vehicle type should be between 3 and 50 characters")
    @Pattern(regexp = "[a-zA-Z\\s]+", message = "Vehicle type should contain only letters and spaces")
    private String vehicleType;

    @Schema(
        description = "Vehicle plate number of the Driver",
        example = "ABC123"
    )
    @NotEmpty(message = "Vehicle plate number should not be empty")
    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9]{6}", message = "Vehicle plate number should be 6 characters long")
    private String vehiclePlateNumber;

//    @Schema(
//        description = "Rating of the Driver",
//        example = "4.5"
//    )
    private Double rating = 0.0;
}
