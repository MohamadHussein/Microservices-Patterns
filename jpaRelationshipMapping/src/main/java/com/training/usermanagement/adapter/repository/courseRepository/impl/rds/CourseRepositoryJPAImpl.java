package jpaRelationshipMapping.src.main.java.com.training.usermanagement.adapter.repository.courseRepository.impl.rds;

import com.example.usermanagement.adapter.repository.Entities.CourseEntity;
import reactive.src.main.java.com.example.usermanagement.adapter.repository.courseRepository.CourseRepo;
import reactive.src.main.java.com.example.usermanagement.model.Course;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.usermanagement.adapter.repository.utils.Utils.toCourseEntity;
import static com.example.usermanagement.adapter.repository.utils.Utils.toCourseModel;




@Component
public class CourseRepositoryJPAImpl implements CourseRepo {

    private final CourseRepositorySpringJPAImpl courseRepositorySpringJPA;

    public CourseRepositoryJPAImpl(CourseRepositorySpringJPAImpl courseRepositorySpringJPA) {
        this.courseRepositorySpringJPA = courseRepositorySpringJPA;
    }

    @Override
    public Optional<Course> byId(Long id) {
        return courseRepositorySpringJPA.findById(id).map(toCourseModel());
    }


    @Override
    public Course save(Course course) {
        System.out.println("course = " + course);
        CourseEntity entity = courseRepositorySpringJPA.save(toCourseEntity().apply(course));
        return toCourseModel().apply(entity);
    }


    @Override
    public List<Course> findByName(String name) {
        return courseRepositorySpringJPA
                .findByNameContaining(name)
                .stream()
                .map(toCourseModel())
                .collect(Collectors.toList());
    }
}
