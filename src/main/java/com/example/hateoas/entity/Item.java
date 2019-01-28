package com.example.hateoas.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@ToString
@Table(name = "TBI_ITEMMST")
public class Item {

    @Id
    @Column(name = "prodid")
    private int prodId;

    @Column(name = "prodname")
    private String prodName;

    private int price;

    @Column(name = "itemid")
    private int itemId;

    @Column(name = "itemtype")
    private int itemType;

    @Column(name = "cmsitemid")
    private String cmsItemId;

    @Column(name = "regdate")
    private LocalDate regDate;



}
