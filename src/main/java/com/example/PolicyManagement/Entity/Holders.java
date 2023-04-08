package com.example.PolicyManagement.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "HOLDER_DETAILS")
@NoArgsConstructor
@AllArgsConstructor
public class Holders {

    @Id
    private long holderId;
    private long contact;
    private String name;
    private String email;
    private String address;
    private String gender;

}
