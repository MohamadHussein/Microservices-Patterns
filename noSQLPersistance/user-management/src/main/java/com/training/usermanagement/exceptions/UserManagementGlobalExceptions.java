package com.example.usermanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserManagementGlobalExceptions {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Object> handleStudentNotFound(StudentNotFoundException exception){
        return new ResponseEntity<>("student not found",HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<Object> handleCourseNotFound(CourseNotFoundException exception){
        return new ResponseEntity<>("course not found",HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<Object> handleDuplicateUser(DuplicateUserException exception){
        return new ResponseEntity("user already registered",HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CannotCreateCourseException.class)
    public ResponseEntity<Object> handleCantCreateCourse(CannotCreateCourseException exception){
        return new ResponseEntity<>("can't create course",HttpStatus.BAD_REQUEST);
    }

}
