package com.tecnocampus.grouppablo.api.frontendException;

import com.tecnocampus.grouppablo.application.exception.CategoryAlreadyExists;
import com.tecnocampus.grouppablo.application.exception.CourseAlreadyExists;
import com.tecnocampus.grouppablo.application.exception.CourseNotFound;
import com.tecnocampus.grouppablo.application.exception.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlingAdvice {

    @ExceptionHandler({CourseNotFound.class, UserNotFound.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Object not found")
    @ResponseBody
    String objectNotFoundHandler(Exception ex) {
        return ex.getMessage();
    }

    @ExceptionHandler({CourseAlreadyExists.class, CategoryAlreadyExists.class})
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "Duplicated title. Please choose another one.")
    String objectAlreadyExists(Exception exception) {
        return "Duplicated title. Please choose another one.";
    }

}