package com.tecnocampus.grouppablo.application.exception;

public class CourseNotFound extends RuntimeException{
    public CourseNotFound(Long id)  {
        super("Course with id:" + id + " does not exist");
    }
}
