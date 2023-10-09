package com.tecnocampus.grouppablo.application.exception;

import java.util.UUID;

public class CourseNotFound extends RuntimeException{
    public CourseNotFound(UUID id)  {
        super("Course with id:" + id + " does not exist");
    }
}
