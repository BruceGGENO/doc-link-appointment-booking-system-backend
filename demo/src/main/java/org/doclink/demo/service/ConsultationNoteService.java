package org.doclink.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.doclink.demo.model.Booking;
import org.doclink.demo.model.BookingStatus;
import org.doclink.demo.model.ConsultationNote;
import org.doclink.demo.repository.BookingRepository;
import org.doclink.demo.repository.ConsultationNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultationNoteService {

    @Autowired
    private ConsultationNoteRepository repository;

    @Autowired
    private BookingRepository bookingRepository;

    public String saveConsultationNote(UUID bookingId, ConsultationNote note) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if (booking.isEmpty()) {
            return "The booking with that id does not exist";
        }
        if (booking.get().getStatus() != BookingStatus.ATTENDED) {
            return "Consultation notes are only for ATTENDED bookings";
        }
        note.setId(null);
        note.setWrittenDate(LocalDate.now());
        note.setBooking(booking.get());
        repository.save(note);
        return "Consultation note saved successfully";
    }

    public List<ConsultationNote> findMemberHistory(String memberNumber) {
        return repository.findMemberHistory(memberNumber);
    }

    public String deleteConsultationNote(UUID id) {
        if (!repository.existsById(id)) {
            return "The consultation note with that id does not exist";
        }
        repository.deleteById(id);
        return "Consultation note deleted successfully";
    }
}
