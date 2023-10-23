package com.tecnocampus.grouppablo.application.exception;

public class UserNotFound extends RuntimeException{
    public UserNotFound(String username){super("User with username: "+username+" does not exist.");}
}
