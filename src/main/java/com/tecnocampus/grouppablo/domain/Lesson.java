package com.tecnocampus.grouppablo.domain;

import com.tecnocampus.grouppablo.application.dto.LessonDTO;

public class Lesson {

    private String title;
    private String description;
    private int duration;
    private String videoURL;
    private boolean done;

    public Lesson(){
    }

    public Lesson(String title, String description, int duration, String videoURL, boolean done){
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.videoURL = videoURL;
        this.done = done;
    }

    public Lesson(LessonDTO lessonDTO){
        this.title = lessonDTO.getTitle();
        this.description = lessonDTO.getDescription();
        this.duration = lessonDTO.getDuration();
        this.videoURL = lessonDTO.getVideoURL();
        this.done = lessonDTO.getDone();
    }

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public int getDuration() {return duration;}
    public void setDuration(int duration) {this.duration = duration;}

    public String getVideoURL() {return videoURL;}
    public void setVideoURL(String videoURL) {this.videoURL = videoURL;}

    public boolean getDone(){return this.done;}
    public void setDone(boolean done){this.done = done;}
}
