package com.example.hateoas.repository;

import com.example.hateoas.entity.Purchase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

@Slf4j
public class PurchaseRepositoryTest extends AbstractDataJpaTest {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Test
    public void findById() {
        String chargeNo = "201901142869211";
        Optional<Purchase> purchase = purchaseRepository.findById(chargeNo);

        Assert.assertEquals(chargeNo, purchase.get().getChargeNo());
        Assert.assertEquals("gilju81", purchase.get().getUserId());

    }

    @Test
    public void findByUserId_gilju81_pagable() {
        Pageable pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Order.desc("chargeNo")));

        Page<Purchase> purchasePage = purchaseRepository.findAllByUserId("gilju81", pageRequest);

        log.info("# hasNext : {}", purchasePage.hasNext());
        log.info("# total page : {}", purchasePage.getTotalPages());
        log.info("# total cnt : {}", purchasePage.getTotalElements());

        purchasePage
                .getContent()
                .stream()
                .forEach(x -> log.info("- purchase : {}", x));


    }
}