package com.zhaqsylyq.payments.mapper;

import com.zhaqsylyq.payments.dto.PaymentDto;
import com.zhaqsylyq.payments.entity.Payment;

public class PaymentsMapper {
    public static PaymentDto mapToPaymentDto(Payment payment, PaymentDto paymentDto) {
        paymentDto.setTripId(payment.getTripId());
        paymentDto.setAmount(payment.getAmount());
        paymentDto.setPaymentMethod(payment.getPaymentMethod());
        paymentDto.setStatus(payment.getStatus());
        paymentDto.setCreatedAt(payment.getCreatedAt());
        return paymentDto;
    }

    public static Payment mapToPayment(PaymentDto paymentDto, Payment payment) {
        payment.setTripId(paymentDto.getTripId());
        payment.setAmount(paymentDto.getAmount());
        payment.setPaymentMethod(paymentDto.getPaymentMethod());
        return payment;
    }
}
