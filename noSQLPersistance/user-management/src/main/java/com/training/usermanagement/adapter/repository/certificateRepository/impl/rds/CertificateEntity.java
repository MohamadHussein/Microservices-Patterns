package com.example.usermanagement.adapter.repository.certificateRepository.impl.rds;

import com.example.usermanagement.adapter.repository.lecturerRepository.impl.rds.LecturerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "certificate")
public class CertificateEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String issueId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lecturer_id")
    private LecturerEntity graduate;
    private String department;
    private String institute;
    private LocalDate issueDate;
}
