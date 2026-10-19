package org.doclink.demo.controller;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.doclink.demo.model.Booking;
import org.doclink.demo.model.BookingStatus;
import org.doclink.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService service;

    @PostMapping("/save")
    public ResponseEntity<?> saveBooking(@RequestBody Booking booking) {
        String message = service.saveBooking(booking);
        if (message.equals("Booking saved successfully")) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else if (message.equals("Booking date cannot be in the past")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBookings() {
        return new ResponseEntity<>(service.getAllBookings(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBooking(@PathVariable UUID id) {
        Optional<Booking> booking = service.getBooking(id);
        if (booking.isPresent()) {
            return new ResponseEntity<>(booking.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The booking with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable UUID id, @RequestBody Booking booking) {
        String message = service.updateBooking(id, booking);
        if (message.equals("Booking updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable UUID id) {
        String message = service.deleteBooking(id);
        if (message.equals("Booking deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else if (message.equals("That booking has consultation notes, delete them first")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-status")
    public ResponseEntity<?> findByStatus(@RequestParam BookingStatus status) {
        return new ResponseEntity<>(service.findByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/between")
    public ResponseEntity<?> findBetween(@RequestParam String start, @RequestParam String end) {
        return new ResponseEntity<>(service.findBetween(LocalDate.parse(start), LocalDate.parse(end)), HttpStatus.OK);
    }
}
