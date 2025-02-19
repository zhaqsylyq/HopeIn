package com.zhaqsylyq.payments.dto;

import com.zhaqsylyq.payments.constants.PaymentMethod;
import com.zhaqsylyq.payments.constants.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(name = "Payment", description = "Schema to hold payment information")
public class PaymentDto {

    @Schema(
            description = "Trip ID associated with the payment",
            example = "123"
    )
    @NotNull(message = "Trip ID cannot be null")
    private Long tripId;

    @Schema(
            description = "Amount of the payment",
            example = "10.00"
    )
    @NotNull(message = "Amount cannot be null")
    private BigDecimal amount;

    @Schema(
            description = "Payment method",
            example = "CREDIT_CARD"
    )
    @NotNull(message = "Payment method cannot be null")
    private PaymentMethod paymentMethod;

    @Schema(
            description = "Payment status",
            example = "PENDING",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private PaymentStatus status;

    @Schema(
            description = "Payment creation timestamp",
            example = "2023-01-01T00:00:00",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime createdAt;

}
