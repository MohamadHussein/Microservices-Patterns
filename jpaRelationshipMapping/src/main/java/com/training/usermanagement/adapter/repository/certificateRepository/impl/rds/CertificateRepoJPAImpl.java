package jpaRelationshipMapping.src.main.java.com.training.usermanagement.adapter.repository.certificateRepository.impl.rds;

import com.example.usermanagement.adapter.repository.Entities.CertificateEntity;
import reactive.src.main.java.com.example.usermanagement.adapter.repository.certificateRepository.CertificateRepo;
import reactive.src.main.java.com.example.usermanagement.model.Certificate;

import java.util.List;
import java.util.Optional;

import static com.example.usermanagement.adapter.repository.utils.Utils.toCertificateEntity;
import static com.example.usermanagement.adapter.repository.utils.Utils.toCertificateModel;

public class CertificateRepoJPAImpl implements CertificateRepo {
    private final CertificateRepoSpringJPAImpl certificateRepoSpringJPA;

    public CertificateRepoJPAImpl(CertificateRepoSpringJPAImpl certificateRepoSpringJPA) {
        this.certificateRepoSpringJPA = certificateRepoSpringJPA;
    }

    @Override
    public Optional<Certificate> byId(Long id) {
        return certificateRepoSpringJPA.findById(id).map(toCertificateModel());

    }

    @Override
    public Certificate save(Certificate certificate) {

        CertificateEntity entity = certificateRepoSpringJPA.save(toCertificateEntity().apply(certificate));
        return toCertificateModel().apply(entity);

    }

    @Override
    public List<Certificate> findByName(String name) {
        return null;
    }
}
