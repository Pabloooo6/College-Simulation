package com.tecnocampus.grouppablo.application.dto;

import com.tecnocampus.grouppablo.domain.Course;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;
import java.time.LocalDate;

public class CourseDTO implements Serializable {

    private String id;
    @Pattern(regexp = "^[A-Z].*", message = "The first letter must be capital letter.")
    private String title;
    private String description;
    private LocalDate publication;
    private LocalDate lastUpdate;
    private String imageUrl;
    private double currentPrice;
    private boolean availability;

    public CourseDTO(){
    }

    public CourseDTO(String title, String description, LocalDate publication, LocalDate lastUpdate,
                     String imageUrl, double currentPrice, boolean availability){
        this.title = title;
        this.description = description;
        this.publication = publication;
        this.lastUpdate = lastUpdate;
        this.imageUrl = imageUrl;
        this.currentPrice = currentPrice;
        this.availability = availability;
    }

    public CourseDTO (Course course){
        this.id = course.getId();
        this.title = course.getTitle();
        this.description = course.getDescription();
        this.publication = course.getPublication();
        this.lastUpdate = course.getLastUpdate();
        this.imageUrl = course.getImageUrl();
        this.currentPrice = course.getCurrentPrice();
        this.availability = course.getAvailability();
    }

    public String getId(){return this.id;}
    public void setId(String id){this.id = id;}

    public String getTitle(){return this.title;}
    public void setTitle(String title){this.title = title;}

    public String getDescription(){return this.description;}
    public void setDescription(String description){this.description = description;}

    public void setPublication(LocalDate publication){this.publication = publication;}
    public LocalDate getPublication(){return this.publication;}

    public void setLastUpdate(LocalDate lastUpdate){this.lastUpdate = lastUpdate;}
    public LocalDate getLastUpdate(){return this.lastUpdate;}

    public String getImageUrl(){return this.imageUrl;}
    public void setImageUrl(String imageUrl){this.imageUrl = imageUrl;}
    
    public double getCurrentPrice(){return this.currentPrice;}
    public void setCurrentPrice(double currentPrice) {this.currentPrice = currentPrice;}
    
    public boolean getAvailability(){return this.availability;}
    public void setAvailability(boolean availability){this.availability = availability;}

}