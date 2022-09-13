package jpaRelationshipMapping.src.main.java.com.training.usermanagement.adapter.repository.lecturerRepository;


import reactive.src.main.java.com.example.usermanagement.model.Lecturer;

import java.util.List;
import java.util.Optional;

public interface LecturerRepo {


    Optional<Lecturer> byId(Long id);

    Lecturer save(Lecturer lecturer);

    List<Lecturer> findByName(String name);
}
