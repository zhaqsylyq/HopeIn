package com.zhaqsylyq.payments.service.impl;

import com.zhaqsylyq.payments.constants.PaymentStatus;
import com.zhaqsylyq.payments.dto.PaymentDto;
import com.zhaqsylyq.payments.entity.Payment;
import com.zhaqsylyq.payments.exception.PaymentNotFoundException;
import com.zhaqsylyq.payments.mapper.PaymentsMapper;
import com.zhaqsylyq.payments.repository.PaymentsRepository;
import com.zhaqsylyq.payments.service.PaymentsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PaymentsServiceImpl implements PaymentsService {

    private final PaymentsRepository paymentsRepository;


    @Override
    public void createPayment(PaymentDto paymentDto) {
            log.info("Creating payment for trip ID: {}", paymentDto.getTripId());
            Payment payment = PaymentsMapper.mapToPayment(paymentDto, new Payment());
            payment.setStatus(PaymentStatus.PENDING); // Default status
            paymentsRepository.save(payment);
            log.info("Payment created successfully with ID: {}", payment.getId());
    }

    @Override
    public List<PaymentDto> getPaymentsByTrip(Long tripId) {
        log.info("Fetching payments for trip ID: {}", tripId);
        return paymentsRepository.findByTripId(tripId)
                .stream()
                .map(payment -> PaymentsMapper.mapToPaymentDto(payment, new PaymentDto()))
                .collect(Collectors.toList());
    }

    @Override
    public void updatePaymentStatus(Long paymentId, String status) {
        log.info("Updating payment status for payment ID: {} to {}", paymentId, status);

        Payment payment = paymentsRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with ID: " + paymentId));

        // Convert status to uppercase for case-insensitive handling
        PaymentStatus newStatus;
        try {
            newStatus = PaymentStatus.valueOf(status.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid payment status: " + status);
        }

        payment.setStatus(newStatus);
        paymentsRepository.save(payment);
        log.info("Payment status updated successfully to {}", newStatus);
    }

}
