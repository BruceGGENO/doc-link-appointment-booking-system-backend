package org.doclink.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.doclink.demo.model.Booking;
import org.doclink.demo.model.BookingStatus;
import org.doclink.demo.model.Practitioner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {

    List<Booking> findByStatusOrderByBookingDateAsc(BookingStatus status);

    boolean existsByPractitionerAndBookingDateAndStatusNot(Practitioner practitioner, LocalDate bookingDate, BookingStatus status);

    List<Booking> findByBookingDateBetweenOrderByBookingDateAsc(LocalDate start, LocalDate end);

    @Query("SELECT b.status, COUNT(b) FROM Booking b GROUP BY b.status")
    List<Object[]> countBookingsByStatus();

    @Query("UPDATE Booking b SET b.status = :declined WHERE b.practitioner.id = :practitionerId AND b.bookingDate = :date AND b.status <> :attended")
    int cancelDay(@Param("practitionerId") UUID practitionerId, @Param("date") LocalDate date,
            @Param("declined") BookingStatus declined, @Param("attended") BookingStatus attended);

}
