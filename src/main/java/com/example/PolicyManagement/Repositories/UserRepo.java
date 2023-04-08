package com.example.PolicyManagement.Repositories;

import com.example.PolicyManagement.Entity.Holders;
import com.example.PolicyManagement.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {

    List<Users> findByUsername(String username);
}
