package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
} 