package com.zhaqsylyq.trips.dto;

import com.zhaqsylyq.trips.constants.TripStatus;
import com.zhaqsylyq.trips.entity.Location;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(
        name = "Trip",
        description = "Schema to hold Trip information"
)
public class TripDto {
    @Schema(
            description = "ID of the trip",
            example = "123"
    )
    private Long id;

    @Schema(
            description = "ID of the passenger",
            example = "P1234",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Passenger ID should not be empty")
    private String passengerId;

    @Schema(
            description = "Unique ID of the Driver",
            example = "D5678"
//            requiredMode = Schema.RequiredMode.REQUIRED
    )
//    @NotBlank(message = "Driver ID should not be empty")
    private String driverId;

    @Schema(
            description = "Current status of the trip",
            example = "REQUESTED"
//            requiredMode = Schema.RequiredMode.REQUIRED
    )
//    @NotNull(message = "Trip status is required")
    private TripStatus status; // REQUESTED, ACCEPTED, ON_THE_WAY, COMPLETED, CANCELLED

    @Schema(description = "Starting location of the trip", requiredMode = Schema.RequiredMode.REQUIRED)
    @Valid // ✅ Ensures validation of nested object
    @NotNull(message = "Start location is required")
    private Location startLocation;

    @Schema(description = "Ending location of the trip", requiredMode = Schema.RequiredMode.REQUIRED)
    @Valid // ✅ Ensures validation of nested object
    @NotNull(message = "End location is required")
    private Location endLocation;

    @Schema(
            description = "Start time of the trip",
            example = "2024-02-18T10:30:00",
            accessMode = Schema.AccessMode.READ_ONLY // ✅ Prevents user from setting it
    )
    private LocalDateTime startTime;

    @Schema(
            description = "End time of the trip",
            example = "2024-02-18T11:00:00"
    )
    private LocalDateTime endTime;

    @Schema(
            description = "Total fare amount for the trip",
            example = "25.50"
    )
    private BigDecimal fareAmount;
}
