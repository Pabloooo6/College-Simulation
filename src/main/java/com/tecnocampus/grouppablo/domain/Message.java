package com.tecnocampus.grouppablo.domain;

import com.tecnocampus.grouppablo.application.dto.MessageDTO;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "message")
public class Message {

    @Id
    private String id;
    private String title;
    private String body;
    private LocalDate date;
    private boolean read;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    public Message(){
        this.date = LocalDate.now();
        this.read = false;
    }

    public Message(String title, String body){
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.body = body;
        this.date = LocalDate.now();
        this.read = false;
    }

    public Message(MessageDTO messageDTO){
        this.id = messageDTO.getId();;
        this.title = messageDTO.getTitle();
        this.body = messageDTO.getBody();
        this.date = messageDTO.getDate();
        this.read = messageDTO.getRead();
        this.sender = new User(messageDTO.getSender());
        this.receiver = new User(messageDTO.getReceiver());
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

    public User getSender() {return sender;}
    public void setSender(User sender) {this.sender = sender;}

    public User getReceiver() {return receiver;}
    public void setReceiver(User receiver) {this.receiver = receiver;}
}
