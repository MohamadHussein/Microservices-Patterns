package com.example.usermanagement.commandHandler;

import com.example.usermanagement.commands.CreateCourseCommand;
import com.example.usermanagement.exceptions.CannotCreateCourseException;
import reactive.src.main.java.com.example.usermanagement.model.Course;
import reactive.src.main.java.com.example.usermanagement.services.CourseManagementService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class CreateCourseCommandHandler {

    private final CourseManagementService courseManagementService;

    public CreateCourseCommandHandler(CourseManagementService courseManagementService) {
        this.courseManagementService = courseManagementService;
    }

    @CommandHandler
    public Course handleCreateCourse(CreateCourseCommand command){
        AtomicLong aLong = new AtomicLong(System.currentTimeMillis());
        Course course = Course.builder()
                .id(aLong.get())
                .name(command.getName())
                .lecturerName(command.getLecturerName())
                .studentList(new ArrayList<>()).build();
        if(!validLecturer(command.getLecturerName())){
            throw new CannotCreateCourseException();
        }
        return courseManagementService.addCourse(course);
    }

    private boolean validLecturer(String lecturerName) { //must enter full name
        int length = lecturerName.split("\\s").length;
        return length == 2;
    }
}
