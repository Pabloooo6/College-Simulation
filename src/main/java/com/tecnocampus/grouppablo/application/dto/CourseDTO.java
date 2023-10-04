package com.tecnocampus.grouppablo.application.dto;

import com.tecnocampus.grouppablo.domain.Course;
import java.io.Serializable;

public class CourseDTO implements Serializable {

    private String title;
    private String description;
    private String imageUrl;
    private double currentPrice;
    private boolean availability;

    public CourseDTO(){
    }

    public CourseDTO (Course course){
        this.title = course.getTitle();
        this.description = course.getDescription();
        this.imageUrl = course.getImageUrl();
        this.currentPrice = course.getCurrentPrice();
        this.availability = course.getAvailability();
    }

    public String getTitle(){return this.title;}
    public void setTitle(String title){this.title = title;}

    public String getDescription(){return this.description;}
    public void setDescription(String description){this.description = description;}

    public String getImageUrl(){return this.imageUrl;}
    public void setImageUrl(String imageUrl){this.imageUrl = imageUrl;}
    
    public double getCurrentPrice(){return this.currentPrice;}
    public void setCurrentPrice(double currentPrice) {this.currentPrice = currentPrice;}
    
    public boolean getAvailability(){return this.availability;}
    public void setAvailability(boolean availability){this.availability = availability;}

}