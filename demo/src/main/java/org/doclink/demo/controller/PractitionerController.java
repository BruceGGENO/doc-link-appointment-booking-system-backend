package org.doclink.demo.controller;

import java.util.Optional;
import java.util.UUID;

import org.doclink.demo.model.Practitioner;
import org.doclink.demo.service.PractitionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/practitioners")
public class PractitionerController {

    @Autowired
    private PractitionerService service;

    @PostMapping("/save")
    public ResponseEntity<?> savePractitioner(@RequestBody Practitioner practitioner) {
        String message = service.savePractitioner(practitioner);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPractitioners() {
        return new ResponseEntity<>(service.getAllPractitioners(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPractitioner(@PathVariable UUID id) {
        Optional<Practitioner> practitioner = service.getPractitioner(id);
        if (practitioner.isPresent()) {
            return new ResponseEntity<>(practitioner.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The practitioner with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePractitioner(@PathVariable UUID id, @RequestBody Practitioner practitioner) {
        String message = service.updatePractitioner(id, practitioner);
        if (message.equals("Practitioner updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePractitioner(@PathVariable UUID id) {
        String message = service.deletePractitioner(id);
        if (message.equals("Practitioner deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-medical-field")
    public ResponseEntity<?> findByMedicalField(@RequestParam String name) {
        return new ResponseEntity<>(service.findByMedicalField(name), HttpStatus.OK);
    }

    @PutMapping("/{practitionerId}/medical-fields/{medicalFieldId}")
    public ResponseEntity<?> addMedicalField(@PathVariable UUID practitionerId, @PathVariable UUID medicalFieldId) {
        String message = service.addMedicalField(practitionerId, medicalFieldId);
        if (message.equals("Medical field added to the practitioner")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else if (message.equals("The practitioner already has that medical field")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{practitionerId}/consultation-room")
    public ResponseEntity<?> changeConsultationRoom(@PathVariable UUID practitionerId, @RequestParam int roomNumber) {
        String message = service.changeConsultationRoom(practitionerId, roomNumber);
        if (message.equals("Consultation room changed successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else if (message.equals("That consultation room is taken by another practitioner")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
