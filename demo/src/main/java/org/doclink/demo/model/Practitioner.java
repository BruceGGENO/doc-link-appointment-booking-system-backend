package org.doclink.demo.model;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.*;

@Entity
@Table(name = "practitioner")
public class Practitioner {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "license_number")
    private String licenseNumber;

    @ManyToOne
    @JoinColumn(name = "consultation_room_id")
    private ConsultationRoom consultationRoom;

    @ManyToMany
    @JoinTable(name = "practitioner_medical_field",
            joinColumns = @JoinColumn(name = "practitioner_id"),
            inverseJoinColumns = @JoinColumn(name = "medical_field_id"))
    private List<MedicalField> medicalFields = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public ConsultationRoom getConsultationRoom() {
        return consultationRoom;
    }

    public void setConsultationRoom(ConsultationRoom consultationRoom) {
        this.consultationRoom = consultationRoom;
    }

    public List<MedicalField> getMedicalFields() {
        return medicalFields;
    }

    public void setMedicalFields(List<MedicalField> medicalFields) {
        this.medicalFields = medicalFields;
    }
}
