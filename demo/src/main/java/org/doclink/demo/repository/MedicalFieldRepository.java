package org.doclink.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.doclink.demo.model.MedicalField;

@Repository
public interface MedicalFieldRepository extends JpaRepository<MedicalField, UUID> {
   
}

