package com.example.hateoas.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;


    @Test
    public void findAll_pagable() {
        Pageable pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Order.desc("regDate")));

        itemRepository
                .findAll(pageRequest)
                .getContent()
                .stream()
                .forEach(x -> log.info("- item : {}", x));


    }

}