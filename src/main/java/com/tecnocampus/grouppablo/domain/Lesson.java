package com.tecnocampus.grouppablo.domain;

import com.tecnocampus.grouppablo.application.dto.LessonDTO;
import jakarta.persistence.*;

@Entity
public class Lesson {

    @Id
    private String title;
    private String description;
    private int duration;
    private String videoURL;
    private int numOrder;

    public Lesson(){
    }

    public Lesson(String title, String description, int duration, String videoURL, int numOrder){
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.videoURL = videoURL;
        this.numOrder = numOrder;
    }

    public Lesson(LessonDTO lessonDTO){
        this.title = lessonDTO.getTitle();
        this.description = lessonDTO.getDescription();
        this.duration = lessonDTO.getDuration();
        this.videoURL = lessonDTO.getVideoURL();
        this.numOrder = lessonDTO.getNumOrder();
    }

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public int getDuration() {return duration;}
    public void setDuration(int duration) {this.duration = duration;}

    public String getVideoURL() {return videoURL;}
    public void setVideoURL(String videoURL) {this.videoURL = videoURL;}

    public int getNumOrder(){return this.numOrder;}
    public void setNumOrder(int numOrder){this.numOrder = numOrder;}
}
