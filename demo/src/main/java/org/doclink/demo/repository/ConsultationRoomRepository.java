package org.doclink.demo.repository;

import java.util.UUID;

import org.doclink.demo.model.ConsultationRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationRoomRepository extends JpaRepository<ConsultationRoom, UUID> {
    

}
