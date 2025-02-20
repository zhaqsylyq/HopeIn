package com.zhaqsylyq.notifications.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Map;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ErrorResponse", description = "Schema for API error responses")
public class ErrorResponseDto {

    @Schema(description = "API Path where the error occurred")
    private String apiPath;

    @Schema(description = "HTTP Status Code")
    private HttpStatus errorCode;

    @Schema(description = "Error message")
    private String errorMessage;

    @Schema(description = "Timestamp when error occurred")
    private Instant errorTime;

    @Schema(description = "Validation errors (if applicable)")
    private Map<String, String> validationErrors;
}
