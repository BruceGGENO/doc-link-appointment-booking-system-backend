package org.doclink.demo.controller;

import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.doclink.demo.model.ConsultationRoom;
import org.doclink.demo.service.ConsultationRoomService;

@RestController
@RequestMapping("/api/consultation-rooms")
public class ConsultationRoomController {

    @Autowired
    private ConsultationRoomService service;

    @PostMapping("/save")
    public ResponseEntity<?> saveConsultationRoom(@RequestBody ConsultationRoom consultationRoom) {
        String message = service.saveConsultationRoom(consultationRoom);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllConsultationRooms() {
        return new ResponseEntity<>(service.getAllConsultationRooms(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getConsultationRoom(@PathVariable UUID id) {
        Optional<ConsultationRoom> consultationRoom = service.getConsultationRoom(id);
        if (consultationRoom.isPresent()) {
            return new ResponseEntity<>(consultationRoom.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The consultation room with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateConsultationRoom(@PathVariable UUID id, @RequestBody ConsultationRoom consultationRoom) {
        String message = service.updateConsultationRoom(id, consultationRoom);
        if (message.equals("Consultation room updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteConsultationRoom(@PathVariable UUID id) {
        String message = service.deleteConsultationRoom(id);
        if (message.equals("Consultation room deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
