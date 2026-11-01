package org.doclink.demo.repository;

import java.util.List;
import java.util.UUID;

import org.doclink.demo.model.ConsultationRoom;
import org.doclink.demo.model.Practitioner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PractitionerRepository extends JpaRepository<Practitioner, UUID> {

    @Query("SELECT p FROM Practitioner p JOIN p.medicalFields f WHERE LOWER(f.name) = LOWER(:name)")
    List<Practitioner> findByMedicalField(@Param("name") String name);

    boolean existsByConsultationRoom(ConsultationRoom consultationRoom);

}
