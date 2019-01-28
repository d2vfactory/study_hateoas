package com.example.hateoas.controller;

import com.example.hateoas.entity.Purchase;
import com.example.hateoas.hateoas.PurchaseResource;
import com.example.hateoas.repository.PurchaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequestMapping("/rest/purchases")
@RestController
public class PurchaseController {

    private final PurchaseRepository repository;

    private final PagedResourcesAssembler assembler;

    public PurchaseController(PurchaseRepository repository, PagedResourcesAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    @GetMapping(produces = {MediaTypes.HAL_JSON_UTF8_VALUE})
    public ResponseEntity<?> getAllPurchases(@RequestParam("userId") String userId, Pageable pageable) {

        if(pageable.getSort().isUnsorted()) {
            Sort sort = Sort.by(Sort.Order.desc("chargeNo"));
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        }

        Page<Purchase> allByUserId = repository.findAllByUserId(userId, pageable);

        return ResponseEntity.ok(assembler.toResource(allByUserId.map(PurchaseResource::new)));

    }


    @GetMapping(value = "/{chargeNo}", produces = {MediaTypes.HAL_JSON_UTF8_VALUE})
    public ResponseEntity<PurchaseResource> getPurchase(@PathVariable String chargeNo) {
        // purchase to purchaseResource
        return repository.findById(chargeNo)
                .map(x -> ResponseEntity.ok(new PurchaseResource(x)))
                .orElseThrow(NullPointerException::new);

    }


}
