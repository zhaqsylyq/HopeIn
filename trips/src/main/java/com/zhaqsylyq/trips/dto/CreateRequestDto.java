package com.zhaqsylyq.trips.dto;

import com.zhaqsylyq.trips.entity.Location;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(
        name = "CreateRequest",
        description = "Schema to hold Create Request by the Passenger"
)
public class CreateRequestDto {
    @Schema(
            description = "ID of the passenger",
            example = "P1234",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Passenger ID should not be empty")
    private String passengerId;

    @Schema(description = "Starting location of the trip", requiredMode = Schema.RequiredMode.REQUIRED)
    @Valid // ✅ Ensures validation of nested object
    @NotNull(message = "Start location is required")
    private Location startLocation;

    @Schema(description = "Ending location of the trip", requiredMode = Schema.RequiredMode.REQUIRED)
    @Valid // ✅ Ensures validation of nested object
    @NotNull(message = "End location is required")
    private Location endLocation;
}
