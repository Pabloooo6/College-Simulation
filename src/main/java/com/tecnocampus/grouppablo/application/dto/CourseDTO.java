package com.tecnocampus.grouppablo.application.dto;

import com.tecnocampus.grouppablo.domain.Course;
import java.io.Serializable;

public class CourseDTO implements Serializable {

    private String title;
    private String description;
    private String imageUrl;

    public CourseDTO(){
    }

    public CourseDTO (Course course){
        this.title = course.getTitle();
        this.description = course.getDescription();
        this.imageUrl = course.getImageUrl();
    }

    public String getTitle(){return this.title;}
    public void setTitle(String title){this.title = title;}

    public String getDescription(){return this.description;}
    public void setDescription(String description){this.description = description;}

    public String getImageUrl(){return this.imageUrl;}
    public void setImageUrl(String imageUrl){this.imageUrl = imageUrl;}

}