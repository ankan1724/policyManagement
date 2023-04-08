package com.example.PolicyManagement.Repositories;

import com.example.PolicyManagement.Entity.PolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PolicyRepo extends JpaRepository<PolicyEntity, Long> {

    Optional<PolicyEntity> findByContact(long contact);
}
