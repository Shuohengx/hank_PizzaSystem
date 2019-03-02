package com.hank.PizzSystem.hank_PizzaSystem.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Timestamp LastUpdatTime;
/*
    private enum status{


    };
    */
    private float Voucher;

    private int unitNumber;

    private int streetNumber;

    private String streetName;

    private String suburbName;

    private int postCode;

    private int mobileNumber;





}
