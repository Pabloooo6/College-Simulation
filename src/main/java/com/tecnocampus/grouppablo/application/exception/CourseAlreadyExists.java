package com.tecnocampus.grouppablo.application.exception;

public class CourseAlreadyExists extends RuntimeException{
    public CourseAlreadyExists(String title)  {
        super("Course with title:" + title + " already exists");
    }
}
