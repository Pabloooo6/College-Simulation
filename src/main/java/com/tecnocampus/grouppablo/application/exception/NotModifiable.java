package com.tecnocampus.grouppablo.application.exception;

public class NotModifiable extends RuntimeException{
    public NotModifiable(String id)  {super("Course with id: " + id + " is available. Can not modify.");}
}
