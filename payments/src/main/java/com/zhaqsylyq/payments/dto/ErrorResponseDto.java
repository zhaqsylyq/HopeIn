package com.zhaqsylyq.payments.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(name = "ErrorResponse", description = "Schema to hold error response information")
@Data
@AllArgsConstructor
public class ErrorResponseDto {

    @Schema(description = "API path invoked by the client")
    private String apiPath;

    @Schema(description = "Error code representing the issue")
    private HttpStatus errorCode;

    @Schema(description = "Detailed error message")
    private String errorMessage;

    @Schema(description = "Time when the error occurred")
    private LocalDateTime errorTime;
}
