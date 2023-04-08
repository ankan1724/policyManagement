package com.example.PolicyManagement.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "policy_user_details_tbl")
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    private int id;
    private String username;
    private String password;
    private String authority;
}
