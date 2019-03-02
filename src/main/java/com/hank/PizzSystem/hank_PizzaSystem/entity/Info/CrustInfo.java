package com.hank.PizzSystem.hank_PizzaSystem.entity.Info;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CrustInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
