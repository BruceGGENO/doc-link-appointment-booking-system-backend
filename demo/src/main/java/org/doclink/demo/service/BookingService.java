package org.doclink.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.doclink.demo.model.Booking;
import org.doclink.demo.model.BookingStatus;
import org.doclink.demo.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepository repository;

    public String saveBooking(Booking booking) {
        if (booking.getBookingDate().isBefore(LocalDate.now())) {
            return "Booking date cannot be in the past";
        }
        booking.setStatus(BookingStatus.REQUESTED);
        booking.setId(null);
        repository.save(booking);
        return "Booking saved successfully";
    }

    public List<Booking> getAllBookings() {
        return repository.findAll();
    }

    public Optional<Booking> getBooking(UUID id) {
        return repository.findById(id);
    }

    public String updateBooking(UUID id, Booking booking) {
        Optional<Booking> existing = repository.findById(id);
        if (existing.isEmpty()) {
            return "The booking with that id does not exist";
        }
        Booking saved = existing.get();
        saved.setBookingDate(booking.getBookingDate());
        saved.setReason(booking.getReason());
        saved.setMember(booking.getMember());
        saved.setPractitioner(booking.getPractitioner());
        repository.save(saved);
        return "Booking updated successfully";
    }

    public String deleteBooking(UUID id) {
        Optional<Booking> booking = repository.findById(id);
        if (booking.isEmpty()) {
            return "The booking with that id does not exist";
        }
        repository.deleteById(id);
        return "Booking deleted successfully";
    }
}
