package com.zhaqsylyq.passengers.dto;

import com.zhaqsylyq.passengers.entity.PreferredLocation;
import com.zhaqsylyq.passengers.entity.Ratings;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
@Schema(
        name = "Passenger",
        description = "Schema to hold Passenger information"
)
public class PassengerDto {

    @Schema(
            description = "Unique Passenger ID assigned by the system",
            example = "P1234",
            accessMode = Schema.AccessMode.READ_ONLY // Prevents users from sending this field in requests
    )
    private String passengerId;

    @Schema(
            description = "FirstName of the Passenger",
            example = "John"
    )
    @NotEmpty(message = "First Name should not be empty")
    @NotNull
    @Size(min = 3, max = 50, message = "First Name should be between 3 and 50 characters")
    private String firstName;

    @Schema(
            description = "LastName of the Passenger",
            example = "Doe"
    )
    @NotEmpty(message = "Last Name should not be empty")
    @NotNull
    @Size(min = 3, max = 50, message = "Last Name should be between 3 and 50 characters")
    private String lastName;

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
            description = "Preferred locations of the Passenger"
    )
    private List<PreferredLocation> preferredLocations;

    @Schema(
            description = "Ratings given by drivers",
            example = "{ \"driverRatings\": 4.8, \"numberOfRides\": 10 }"
    )
    private Ratings ratings;
}
