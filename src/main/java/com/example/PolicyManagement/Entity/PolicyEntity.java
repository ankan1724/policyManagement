package com.example.PolicyManagement.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "POLICY_DETAILS")
@AllArgsConstructor
@NoArgsConstructor
public class PolicyEntity {

    @Id
    private long policynumber;
    private long contact;
    private String type;
    private double amount;
    private int tenure;
    private double premium;
    @ManyToOne
    private Holders holders;

}
