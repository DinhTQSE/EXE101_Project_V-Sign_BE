package com.vsign.backend.monetization.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrderEntity, String> {
    List<PaymentOrderEntity> findAllByOrderByCreatedAtDesc();
}
