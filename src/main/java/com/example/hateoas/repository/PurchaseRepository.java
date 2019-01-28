package com.example.hateoas.repository;

import com.example.hateoas.entity.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, String> {
    Page<Purchase> findAllByUserId(String userId, Pageable pageable);

}
