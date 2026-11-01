package org.doclink.demo.repository;

import java.util.List;
import java.util.UUID;

import org.doclink.demo.model.Booking;
import org.doclink.demo.model.ConsultationNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationNoteRepository extends JpaRepository<ConsultationNote, UUID> {

    boolean existsByBooking(Booking booking);

    @Query("SELECT d FROM ConsultationNote d WHERE d.booking.member.memberNumber = :memberNumber ORDER BY d.writtenDate DESC")
    List<ConsultationNote> findMemberHistory(@Param("memberNumber") String memberNumber);

}
