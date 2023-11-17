package com.tecnocampus.grouppablo.domain;

import com.tecnocampus.grouppablo.application.dto.EnrolIdDTO;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EnrolId implements Serializable {

    private String username;
    private String courseId;

    public EnrolId(){}

    public EnrolId(String username, String courseId){
        this.username = username;
        this.courseId = courseId;
    }

    public EnrolId (EnrolIdDTO enrolIdDTO){
        this.username = enrolIdDTO.getUsername();
        this.courseId = enrolIdDTO.getCourseId();
    }

    public String getUsername(){return this.username;}
    public void setUsername(String username){this.username = username;}

    public String getCourseId(){return this.courseId;}
    public void setCourseId(String courseId) {this.courseId = courseId;}

    public int hashCode() {
        return Objects.hash(username, courseId);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EnrolId that = (EnrolId) o;
        return Objects.equals(username, that.username) && Objects.equals(courseId, that.courseId);
    }
}
