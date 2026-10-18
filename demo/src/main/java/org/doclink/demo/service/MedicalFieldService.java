package org.doclink.demo.service;



import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.doclink.demo.model.MedicalField;
import org.doclink.demo.repository.MedicalFieldRepository;

@Service
public class MedicalFieldService {
    

    @Autowired
    private MedicalFieldRepository repository;

    public String saveMedicalField(MedicalField medicalField) {
        medicalField.setId(null);
        repository.save(medicalField);
        return "Medical field saved successfully";
    }

    public List<MedicalField> getAllMedicalFields() {
        return repository.findAll();
    }

    public Optional<MedicalField> getMedicalField(UUID id) {
        return repository.findById(id);
    }

    public String updateMedicalField(UUID id, MedicalField medicalField) {
        Optional<MedicalField> existing = repository.findById(id);
        if (existing.isEmpty()) {
            return "The medical field with that id does not exist";
        }
        MedicalField saved = existing.get();
        saved.setName(medicalField.getName());
        repository.save(saved);
        return "Medical field updated successfully";
    }

    public String deleteMedicalField(UUID id) {
        if (!repository.existsById(id)) {
            return "The medical field with that id does not exist";
        }
        repository.deleteById(id);
        return "Medical field deleted successfully";
    }
}
