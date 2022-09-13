package com.example.usermanagement.adapter.repository.lecturerRepository.impl.rds;

import reactive.src.main.java.com.example.usermanagement.adapter.repository.lecturerRepository.LecturerRepo;
import com.example.usermanagement.adapter.repository.utils.rds.Utils;
import reactive.src.main.java.com.example.usermanagement.model.Lecturer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.usermanagement.adapter.repository.utils.rds.Utils.toLecturerEntity;
import static com.example.usermanagement.adapter.repository.utils.rds.Utils.toLecturerModel;

@Component
@ConditionalOnProperty(value = "db.source.impl",havingValue = "h2",matchIfMissing = true)

public class LecturerRepoJPAImpl implements LecturerRepo {
    private final LecturerRepoSpringJPAImpl lecturerRepoSpringJPA;

    public LecturerRepoJPAImpl(LecturerRepoSpringJPAImpl lecturerRepoSpringJPA) {
        this.lecturerRepoSpringJPA = lecturerRepoSpringJPA;
    }

    @Override
    public Optional<Lecturer> byId(Long id) {
        return lecturerRepoSpringJPA.findById(id).map(Utils::toLecturerModel);

    }

    @Override
    public Lecturer save(Lecturer lecturer) {
        LecturerEntity entity = lecturerRepoSpringJPA.save(toLecturerEntity(lecturer));

        return toLecturerModel(entity);
    }

    @Override
    public List<Lecturer> findByName(String name) {
        return lecturerRepoSpringJPA.findByFirstNameOrLastNameContaining(name,name).stream()
                .map(Utils::toLecturerModel)
                .collect(Collectors.toList());
    }
}
