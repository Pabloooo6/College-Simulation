package com.tecnocampus.grouppablo.api.frontendException;

import com.tecnocampus.grouppablo.application.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlingAdvice {

    @ExceptionHandler({CourseNotFound.class, UserNotFound.class, EnrolNotFound.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Object not found")
    @ResponseBody
    String objectNotFoundHandler(Exception ex) {
        return ex.getMessage();
    }

    @ExceptionHandler({CourseAlreadyExists.class, CategoryAlreadyExists.class, EnrolAlreadyExists.class})
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "Duplicated object. Please choose another one.")
    String objectAlreadyExists(Exception exception) {
        return "Duplicated object. Please choose another one.";
    }

    @ExceptionHandler({NoLessonsInCourse.class})
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "There is any lesson in the course. Can not make it available.")
    String courseWithoutLesson(Exception exception) {
        return "There is any lesson in the course. Can not make it available.";}

    @ExceptionHandler({LessonsNotFinished.class, NotHalfOfLessonsDone.class})
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "There is a lesson that must finish before.")
    String lessonsNotFinished(Exception exception) {return "There is a lesson that must finish before.";}

    @ExceptionHandler({NotModifiable.class})
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "The course is available. Can not modify.")
    String courseNotModifiable(Exception exception) {
        return "The course is available. Can not modify.";
    }
}