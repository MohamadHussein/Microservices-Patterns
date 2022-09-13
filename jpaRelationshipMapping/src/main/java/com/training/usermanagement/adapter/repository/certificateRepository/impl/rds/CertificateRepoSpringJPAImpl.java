package jpaRelationshipMapping.src.main.java.com.training.usermanagement.adapter.repository.certificateRepository.impl.rds;

import com.example.usermanagement.adapter.repository.Entities.CertificateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepoSpringJPAImpl extends JpaRepository<CertificateEntity,Long> {

}
