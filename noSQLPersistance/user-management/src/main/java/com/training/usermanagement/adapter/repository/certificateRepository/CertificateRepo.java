package com.example.usermanagement.adapter.repository.certificateRepository;

import reactive.src.main.java.com.example.usermanagement.model.Certificate;

import java.util.List;
import java.util.Optional;

public interface CertificateRepo {

    Optional<Certificate> byId(Long id);

    Certificate save(Certificate certificate);

    List<Certificate> findByName(String name);
}
