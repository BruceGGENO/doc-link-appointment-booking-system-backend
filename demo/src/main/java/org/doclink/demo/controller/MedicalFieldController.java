package org.doclink.demo.controller;

import java.util.Optional;
import java.util.UUID;

import org.doclink.demo.model.MedicalField;
import org.doclink.demo.service.MedicalFieldService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medical-fields")
public class MedicalFieldController {

    @Autowired
    private MedicalFieldService service;

    @PostMapping("/save")
    public ResponseEntity<?> saveMedicalField(@RequestBody MedicalField medicalField) {
        String message = service.saveMedicalField(medicalField);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllMedicalFields() {
        return new ResponseEntity<>(service.getAllMedicalFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMedicalField(@PathVariable UUID id) {
        Optional<MedicalField> medicalField = service.getMedicalField(id);
        if (medicalField.isPresent()) {
            return new ResponseEntity<>(medicalField.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The medical field with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMedicalField(@PathVariable UUID id, @RequestBody MedicalField medicalField) {
        String message = service.updateMedicalField(id, medicalField);
        if (message.equals("Medical field updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMedicalField(@PathVariable UUID id) {
        String message = service.deleteMedicalField(id);
        if (message.equals("Medical field deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/unused")
    public ResponseEntity<?> findUnusedFields() {
        return new ResponseEntity<>(service.findUnusedFields(), HttpStatus.OK);
    }
}
