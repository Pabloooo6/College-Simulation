package com.tecnocampus.grouppablo.application.exception;

public class EnrolNotFound extends RuntimeException{
    public EnrolNotFound(String userId, String courseId){
        super("Connexion between username: " + userId + " and courseId: " + courseId + " does not exist.");}
}
