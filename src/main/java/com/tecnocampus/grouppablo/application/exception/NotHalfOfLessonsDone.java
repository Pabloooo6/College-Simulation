package com.tecnocampus.grouppablo.application.exception;

public class NotHalfOfLessonsDone extends RuntimeException{
    public NotHalfOfLessonsDone(String id, String courseId)  {
        super("The user with id: " + id + " has not finished half of the lessons of the course with id: " + courseId);
    }
}
