package com.tecnocampus.grouppablo.application.dto;

import com.tecnocampus.grouppablo.domain.Message;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;

public class MessageDTO implements Serializable{

    private String id;
    @Pattern(regexp = "^[A-Z].*", message = "The first letter must be capital letter.")
    private String title;
    @Pattern(regexp = ".+", message = "The message has to contain at least one letter.")
    private String body;
    private LocalDate date;
    private boolean read;
    private UserDTO sender;
    private UserDTO receiver;

    public MessageDTO() {
        this.date = LocalDate.now();
        this.read = false;
    }

    public MessageDTO(String id, String title, String body, LocalDate date, boolean read, UserDTO sender, UserDTO receiver){
        this.id = id;
        this.title = title;
        this.body = body;
        this.date = date;
        this.read = read;
        this.sender = sender;
        this.receiver = receiver;
    }
    
    public MessageDTO(Message message){
        this.id = message.getId();
        this.title = message.getTitle();
        this.body = message.getBody();
        this.date = message.getDate();
        this.read = message.getRead();
        this.sender = new UserDTO(message.getSender());
        this.receiver = new UserDTO(message.getReceiver());
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getBody() {return body;}
    public void setBody(String body) {this.body = body;}

    public LocalDate getDate() {return date;}
    public void setDate(LocalDate date) {this.date = date;}

    public boolean getRead(){return read;}
    public void setRead(boolean read){this.read = read;}

    public UserDTO getSender() {return sender;}
    public void setSender(UserDTO sender) {this.sender = sender;}

    public UserDTO getReceiver() {return receiver;}
    public void setReceiver(UserDTO receiver) {this.receiver = receiver;}
}
