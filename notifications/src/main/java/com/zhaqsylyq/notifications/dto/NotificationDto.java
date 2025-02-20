package com.zhaqsylyq.notifications.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhaqsylyq.notifications.constants.NotificationType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(
    name = "Notification",
    description = "Schema to hold notification information"
)
public class NotificationDto {

    @Schema(description = "Recipient ID (Passenger or Driver)", example = "P1234")
    @NotBlank(message = "Recipient ID should not be empty")
    private String recipientId;

    @Schema(description = "Notification type", example = "TRIP_ACCEPTED")
    @NotNull(message = "Notification type is required")
    private NotificationType type;

    @Schema(description = "Notification message", example = "Driver Bob has accepted your ride request")
    @NotBlank(message = "Notification message should not be empty")
    private String message;

    @Schema(description = "Notification timestamp", example = "2023-01-01T00:00:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private Instant timestamp;
}
