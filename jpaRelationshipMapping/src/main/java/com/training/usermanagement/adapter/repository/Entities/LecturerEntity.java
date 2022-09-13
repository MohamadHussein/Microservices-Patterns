package jpaRelationshipMapping.src.main.java.com.training.usermanagement.adapter.repository.Entities;

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
