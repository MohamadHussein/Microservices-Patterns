package com.example.usermanagement.adapter.repository.courseRepository.impl.mongo;

import reactive.src.main.java.com.example.usermanagement.adapter.repository.courseRepository.CourseRepo;
import reactive.src.main.java.com.example.usermanagement.adapter.repository.courseRepository.impl.mongo.documents.CourseDoc;
import reactive.src.main.java.com.example.usermanagement.adapter.repository.utils.noSql.Utils;
import reactive.src.main.java.com.example.usermanagement.model.Course;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static reactive.src.main.java.com.example.usermanagement.adapter.repository.utils.noSql.Utils.toCourseDocument;
import static reactive.src.main.java.com.example.usermanagement.adapter.repository.utils.noSql.Utils.toCourseModel;

@Component
@ConditionalOnProperty(value = "db.source.impl",havingValue = "mongo")
public class CourseRepoNoSqlImpl implements CourseRepo {

    private final CourseRepoMongoImpl courseRepoMongo;

    public CourseRepoNoSqlImpl(CourseRepoMongoImpl courseRepoMongo) {
        this.courseRepoMongo = courseRepoMongo;
    }

    @Override
    public Optional<Course> byId(Long id) {
        return courseRepoMongo.findById(String.valueOf(id)).map(Utils::toCourseModel);
    }

    @Override
    public Course save(Course course) {
        CourseDoc courseDoc = toCourseDocument(course);
        return toCourseModel(courseRepoMongo.save(courseDoc));
    }

    @Override
    public Page<Course> findByName(String name, Pageable pageable) {
        return courseRepoMongo.findByName(name, pageable).map(Utils::toCourseModel);
    }
}
