package com.example.hateoas.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Table(name = "TBI_PURCHASEMST")
public class Purchase {

    @Id
    @Column(name = "chargeno")
    private String chargeNo;
    @Column(name = "userno")
    private String userNo;
    @Column(name = "userid")
    private String userId;
    @Column(name = "username")
    private String userName;
    @Column(name = "prodname")
    private String prodName;
    @Column(name = "prodid")
    private int prodId;
    @Column(name = "prodtype")
    private int prodType;
    @Column(name = "price")
    private int price;
    @Column(name = "corpid")
    private int corpId;
    @Column(name = "ymd")
    private String ymd;
    @Column(name = "regdate")
    private LocalDate regDate;
    @Column(name = "usestate")
    private int useState;


}
