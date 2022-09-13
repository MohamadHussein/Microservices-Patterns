package jpaRelationshipMapping.src.main.java.com.training.usermanagement.adapter.repository.courseRepository.impl.rds;

import com.example.usermanagement.adapter.repository.Entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepositorySpringJPAImpl extends JpaRepository<CourseEntity,Long> {
    List<CourseEntity> findByNameContaining(String substr);
}
