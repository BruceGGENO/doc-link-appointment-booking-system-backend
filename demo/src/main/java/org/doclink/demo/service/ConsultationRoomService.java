package org.doclink.demo.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.doclink.demo.model.ConsultationRoom;
import org.doclink.demo.repository.ConsultationRoomRepository;

@Service
public class ConsultationRoomService {
    
    @Autowired
    private ConsultationRoomRepository repository;

    public String saveConsultationRoom(ConsultationRoom consultationRoom) {
        consultationRoom.setId(null);
        repository.save(consultationRoom);
        return "Consultation room saved successfully";
    }

    public List<ConsultationRoom> getAllConsultationRooms() {
        return repository.findAll();
    }

    public Optional<ConsultationRoom> getConsultationRoom(UUID id) {
        return repository.findById(id);
    }

    public String updateConsultationRoom(UUID id, ConsultationRoom consultationRoom) {
        Optional<ConsultationRoom> existing = repository.findById(id);
        if (existing.isEmpty()) {
            return "The consultation room with that id does not exist";
        }
        ConsultationRoom saved = existing.get();
        saved.setRoomNumber(consultationRoom.getRoomNumber());
        saved.setName(consultationRoom.getName());
        repository.save(saved);
        return "Consultation room updated successfully";
    }

    public String deleteConsultationRoom(UUID id) {
        if (!repository.existsById(id)) {
            return "The consultation room with that id does not exist";
        }
        repository.deleteById(id);
        return "Consultation room deleted successfully";
    }
}
