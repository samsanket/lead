package com.mahindra.lead.repository;

import com.mahindra.lead.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadsRepository extends JpaRepository<Lead, Integer> {
    // You can add custom query methods here if needed
}
