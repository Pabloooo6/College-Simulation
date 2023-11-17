package com.tecnocampus.grouppablo.application.exception;

public class EnrolAlreadyExists extends RuntimeException{
    public EnrolAlreadyExists(String userId, String courseId)  {
        super("Connexion between user: " + userId + " and courseId: " + courseId + " already exists");}
}
