package org.doclink.demo.controller;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.doclink.demo.model.ConsultationNote;
import org.doclink.demo.service.ConsultationNoteService;

@RestController
@RequestMapping("/api/consultation-notes")
public class ConsultationNoteController {

    @Autowired
    private ConsultationNoteService service;

    @PostMapping("/save")
    public ResponseEntity<?> saveConsultationNote(@RequestParam UUID bookingId, @RequestBody ConsultationNote note) {
        String message = service.saveConsultationNote(bookingId, note);
        if (message.equals("Consultation note saved successfully")) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else if (message.equals("The booking with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/member/{memberNumber}")
    public ResponseEntity<?> findMemberHistory(@PathVariable String memberNumber) {
        return new ResponseEntity<>(service.findMemberHistory(memberNumber), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteConsultationNote(@PathVariable UUID id) {
        String message = service.deleteConsultationNote(id);
        if (message.equals("Consultation note deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
