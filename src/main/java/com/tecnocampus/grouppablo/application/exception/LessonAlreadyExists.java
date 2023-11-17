package com.tecnocampus.grouppablo.application.exception;

public class LessonAlreadyExists extends RuntimeException{
    public LessonAlreadyExists (String title){super("Lesson with title: " + title + " already exists.");}
}
