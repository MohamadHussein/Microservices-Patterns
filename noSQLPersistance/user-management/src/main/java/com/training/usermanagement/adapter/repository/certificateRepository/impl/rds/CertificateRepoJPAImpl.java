package com.example.usermanagement.adapter.repository.certificateRepository.impl.rds;

import reactive.src.main.java.com.example.usermanagement.adapter.repository.certificateRepository.CertificateRepo;
import reactive.src.main.java.com.example.usermanagement.model.Certificate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.example.usermanagement.adapter.repository.utils.rds.Utils.toCertificateEntity;
import static com.example.usermanagement.adapter.repository.utils.rds.Utils.toCertificateModel;

@Component
@ConditionalOnProperty(value = "db.source.impl",havingValue = "h2",matchIfMissing = true)

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
