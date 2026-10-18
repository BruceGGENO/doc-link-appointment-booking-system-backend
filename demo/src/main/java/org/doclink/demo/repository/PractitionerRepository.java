package org.doclink.demo.repository;

import org.doclink.demo.model.ConsultationRoom;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.doclink.demo.model.Practitioner;

@Repository
public interface PractitionerRepository extends JpaRepository<Practitioner, UUID> {
   
}