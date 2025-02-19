package com.zhaqsylyq.payments.controller;

import com.zhaqsylyq.payments.constants.PaymentsConstants;
import com.zhaqsylyq.payments.dto.ErrorResponseDto;
import com.zhaqsylyq.payments.dto.PaymentDto;
import com.zhaqsylyq.payments.dto.ResponseDto;
import com.zhaqsylyq.payments.service.PaymentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Payment APIs", description = "CRUD APIs for Payments in HopIn")
@RestController
@RequestMapping("/api/v1/payments")
@AllArgsConstructor
@Validated
public class PaymentsController {
    private final PaymentsService paymentsService;

    @Operation(summary = "Create a Payment", description = "Registers a new payment for a trip")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Payment created successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createPayment(@Valid @RequestBody PaymentDto paymentDto) {
        paymentsService.createPayment(paymentDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(PaymentsConstants.STATUS_201, PaymentsConstants.MESSAGE_201));
    }

    @Operation(summary = "Fetch Payments by Trip ID", description = "Gets payments linked to a specific trip")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Payments retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No payments found", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/fetch")
    public ResponseEntity<List<PaymentDto>> getPaymentsByTrip(@RequestParam Long tripId) {
        return ResponseEntity.ok(paymentsService.getPaymentsByTrip(tripId));
    }

    @Operation(summary = "Update Payment Status", description = "Updates the status of a payment")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Payment status updated successfully"),
            @ApiResponse(responseCode = "404", description = "Payment not found", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PutMapping("/{paymentId}/update-status")
    public ResponseEntity<ResponseDto> updatePaymentStatus(@PathVariable Long paymentId, @RequestParam String status) {
        paymentsService.updatePaymentStatus(paymentId, status);
        return ResponseEntity.ok(new ResponseDto(PaymentsConstants.STATUS_200, "Payment status updated successfully"));
    }
}
