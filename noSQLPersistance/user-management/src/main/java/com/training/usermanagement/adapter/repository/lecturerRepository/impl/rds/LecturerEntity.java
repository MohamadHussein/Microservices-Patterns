package com.example.usermanagement.adapter.repository.lecturerRepository.impl.rds;

import com.example.usermanagement.adapter.repository.certificateRepository.impl.rds.CertificateEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lecturer")
@Builder
public class LecturerEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;

    @OneToMany(mappedBy = "graduate")
    private List<CertificateEntity> certificate;
}
