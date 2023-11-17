package com.tecnocampus.grouppablo.application.exception;

public class LessonsNotFinished extends RuntimeException{
    public LessonsNotFinished(String title){super("The lesson before: " + title + " is not finished.");}
}
