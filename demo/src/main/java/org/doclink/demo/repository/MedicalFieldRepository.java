package org.doclink.demo.repository;

import java.util.List;
import java.util.UUID;

import org.doclink.demo.model.MedicalField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalFieldRepository extends JpaRepository<MedicalField, UUID> {

    @Query("SELECT f FROM MedicalField f WHERE f.practitioners IS EMPTY")
    List<MedicalField> findUnusedFields();
}
