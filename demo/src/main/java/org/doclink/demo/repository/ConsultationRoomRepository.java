package org.doclink.demo.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.doclink.demo.model.ConsultationRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationRoomRepository extends JpaRepository<ConsultationRoom, UUID> {

    @Query("SELECT r FROM ConsultationRoom r WHERE NOT EXISTS (SELECT p FROM Practitioner p WHERE p.consultationRoom = r) ORDER BY r.roomNumber")
    List<ConsultationRoom> findFreeRooms();

    Optional<ConsultationRoom> findByRoomNumber(int roomNumber);

}
