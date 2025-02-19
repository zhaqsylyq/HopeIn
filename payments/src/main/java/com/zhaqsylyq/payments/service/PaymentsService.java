package com.zhaqsylyq.payments.service;

import com.zhaqsylyq.payments.dto.PaymentDto;

import java.util.List;

public interface PaymentsService {
    void createPayment(PaymentDto paymentDto);
    List<PaymentDto> getPaymentsByTrip(Long tripId);
    void updatePaymentStatus(Long paymentId, String status);
}
