package com.tecnocampus.grouppablo.application.dto;

import com.tecnocampus.grouppablo.domain.Lesson;

public class LessonDTO {

    private String title;
    private String description;
    private int duration;
    private String videoURL;
    private boolean done;

    public LessonDTO(){
    }

    public LessonDTO(String title, String description, int duration, String videoURL, boolean done){
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.videoURL = videoURL;
        this.done = done;
    }

    public LessonDTO(Lesson lesson){
        this.title = lesson.getTitle();
        this.description = lesson.getDescription();
        this.duration = lesson.getDuration();
        this.videoURL = lesson.getVideoURL();
        this.done = lesson.getDone();
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
