package com.zhaqsylyq.passengers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(
        name = "Passenger",
        description = "Schema to hold Passenger information"
)
public class PassengerDto {
    @Schema(
            description = "Name of the Passenger",
            example = "John Doe"
    )
    @NotEmpty(message = "Name should not be empty")
    @NotNull
    @Size(min = 3, max = 50, message = "Name should be between 3 and 50 characters")
    private String name;

    @Schema(
            description = "Email of the Passenger",
            example = "0qoZ6@example.com"
    )
    @NotEmpty(message = "Email should not be empty")
    @NotNull
    @Email(message = "Email should be in format: 0qoZ6@example.com")
    private String email;

    @Schema(
            description = "Phone number of the Passenger",
            example = "1234567890"
    )
    @NotEmpty(message = "Phone number should not be empty")
    @NotNull
    @Pattern(regexp = "[0-9]{10}", message = "Phone number should be 10 digits")
    private String phoneNumber;

    @Schema(
            description = "Rating of the Passenger",
            example = "4.5"
    )
    private Double rating;
}
