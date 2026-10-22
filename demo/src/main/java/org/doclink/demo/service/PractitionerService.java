package org.doclink.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.doclink.demo.model.Practitioner;
import org.doclink.demo.repository.ConsultationRoomRepository;
import org.doclink.demo.repository.MedicalFieldRepository;
import org.doclink.demo.repository.PractitionerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PractitionerService {

    @Autowired
    private ConsultationRoomRepository consultationRoomRepository;

    @Autowired
    private MedicalFieldRepository medicalFieldRepository;

    @Autowired
    private PractitionerRepository repository;

    public String savePractitioner(Practitioner practitioner) {
        practitioner.setId(null);
        repository.save(practitioner);
        return "Practitioner saved successfully";
    }

    public List<Practitioner> getAllPractitioners() {
        return repository.findAll();
    }

    public Optional<Practitioner> getPractitioner(UUID id) {
        return repository.findById(id);
    }

    public String updatePractitioner(UUID id, Practitioner practitioner) {
        Optional<Practitioner> existing = repository.findById(id);
        if (existing.isEmpty()) {
            return "The practitioner with that id does not exist";
        }
        Practitioner saved = existing.get();
        saved.setFirstName(practitioner.getFirstName());
        saved.setLastName(practitioner.getLastName());
        saved.setLicenseNumber(practitioner.getLicenseNumber());
        saved.setConsultationRoom(practitioner.getConsultationRoom());
        saved.setMedicalFields(practitioner.getMedicalFields());
        repository.save(saved);
        return "Practitioner updated successfully";
    }

    public String deletePractitioner(UUID id) {
        if (!repository.existsById(id)) {
            return "The practitioner with that id does not exist";
        }
        repository.deleteById(id);
        return "Practitioner deleted successfully";
    }

    public List<Practitioner> findByMedicalField(String name) {
        return repository.findByMedicalField(name);
    }

    
}
