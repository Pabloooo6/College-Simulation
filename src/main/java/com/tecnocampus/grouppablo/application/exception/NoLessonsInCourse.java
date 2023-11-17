package com.tecnocampus.grouppablo.application.exception;

public class NoLessonsInCourse extends RuntimeException{
    public NoLessonsInCourse(String id)  {
        super("Course with id: " + id + " does not have any lesson. You can not make it available.");
    }
}
