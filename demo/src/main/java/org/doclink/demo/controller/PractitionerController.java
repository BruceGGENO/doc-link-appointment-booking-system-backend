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
}
