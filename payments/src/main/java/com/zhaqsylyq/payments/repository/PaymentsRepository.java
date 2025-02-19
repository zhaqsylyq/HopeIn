package com.zhaqsylyq.payments.repository;

import com.zhaqsylyq.payments.constants.PaymentStatus;
import com.zhaqsylyq.payments.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentsRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByTripId(Long tripId);
    List<Payment> findByStatus(PaymentStatus paymentStatus);
}
