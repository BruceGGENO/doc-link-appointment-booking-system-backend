package org.doclink.demo.repository;

import java.time.LocalDate;
import java.util.UUID;

import org.doclink.demo.model.BookingStatus;
import org.doclink.demo.model.Practitioner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<org.doclink.demo.model.Booking, UUID> {

    boolean existsByPractitionerAndBookingDateAndStatusNot(Practitioner practitioner, LocalDate bookingDate, BookingStatus status);

}
