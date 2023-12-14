package com.tecnocampus.grouppablo.application.exception;

public class MessageNotFound extends RuntimeException{
    public MessageNotFound(String messageId){super("The message with the id: " + messageId + " does not exist.");}
}
