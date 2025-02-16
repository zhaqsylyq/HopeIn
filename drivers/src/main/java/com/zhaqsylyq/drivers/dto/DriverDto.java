package com.zhaqsylyq.drivers.dto;

import com.zhaqsylyq.drivers.entity.Ratings;
import com.zhaqsylyq.drivers.entity.VehicleInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(
    name = "Driver",
    description = "Schema to hold driver information"
)
public class DriverDto {
    @Schema(
            description = "Unique Driver ID assigned by the system",
            example = "D5678",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private String driverId; // Generated automatically

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

    @Valid // Ensures validation applies to the nested object
    @NotNull(message = "Vehicle information is required")
    private VehicleInfo vehicleInfo;

    @Schema(
            description = "Driver status",
            example = "AVAILABLE"
    )
    @Pattern(regexp = "AVAILABLE|OFFLINE|ON_TRIP", message = "Status should be AVAILABLE, OFFLINE, or ON_TRIP")
    private String status; // AVAILABLE, OFFLINE, ON_TRIP

    private Ratings ratings;
}
