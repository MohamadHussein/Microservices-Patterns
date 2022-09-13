package jpaRelationshipMapping.src.main.java.com.training.usermanagement.adapter.repository.lecturerRepository.impl.rds;

import com.example.usermanagement.adapter.repository.Entities.LecturerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LecturerRepoSpringJPAImpl extends JpaRepository<LecturerEntity,Long> {
    List<LecturerEntity> findByName(String name);
}
