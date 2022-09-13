package jpaRelationshipMapping.src.main.java.com.training.usermanagement.adapter.repository.lecturerRepository.impl.rds;

import com.example.usermanagement.adapter.repository.Entities.LecturerEntity;
import reactive.src.main.java.com.example.usermanagement.adapter.repository.lecturerRepository.LecturerRepo;
import reactive.src.main.java.com.example.usermanagement.model.Lecturer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.usermanagement.adapter.repository.utils.Utils.toLecturerEntity;
import static com.example.usermanagement.adapter.repository.utils.Utils.toLecturerModel;


public class LecturerRepoJPAImpl implements LecturerRepo {
    private final LecturerRepoSpringJPAImpl lecturerRepoSpringJPA;

    public LecturerRepoJPAImpl(LecturerRepoSpringJPAImpl lecturerRepoSpringJPA) {
        this.lecturerRepoSpringJPA = lecturerRepoSpringJPA;
    }

    @Override
    public Optional<Lecturer> byId(Long id) {
        return lecturerRepoSpringJPA.findById(id).map(toLecturerModel());

    }

    @Override
    public Lecturer save(Lecturer lecturer) {
        LecturerEntity entity = lecturerRepoSpringJPA.save(toLecturerEntity(lecturer));
        return toLecturerModel(entity);
    }

    @Override
    public List<Lecturer> findByName(String name) {
        return lecturerRepoSpringJPA.findByName(name).stream()
                .map(toLecturerModel())
                .collect(Collectors.toList());
    }
}
