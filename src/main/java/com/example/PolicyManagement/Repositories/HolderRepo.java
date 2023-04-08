package com.example.PolicyManagement.Repositories;

import com.example.PolicyManagement.Entity.Holders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolderRepo extends JpaRepository<Holders,Long> {
    List<Holders> findByName(String name);
}
