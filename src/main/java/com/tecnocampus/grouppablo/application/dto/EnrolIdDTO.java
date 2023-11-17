package com.tecnocampus.grouppablo.application.dto;

import com.tecnocampus.grouppablo.domain.EnrolId;
import java.io.Serializable;

public class EnrolIdDTO implements Serializable {

    private String username;
    private String courseId;

    public EnrolIdDTO(){}

    public EnrolIdDTO(EnrolId enrolId){
        this.username = enrolId.getUsername();
        this.courseId = enrolId.getCourseId();
    }

    public String getUsername(){return this.username;}
    public void setUsername(String username){this.username = username;}

    public String getCourseId() {return this.courseId;}
    public void setCourseId(String courseId) {this.courseId = courseId;}
}
