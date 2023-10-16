package com.tecnocampus.grouppablo.domain;

import com.tecnocampus.grouppablo.application.dto.CourseDTO;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "course")
public class Course {

    @Id
    private String id;
    @Column(name = "title", unique = true)
    private String title;
    private String description;
    private LocalDate publication;
    private LocalDate lastUpdate;
    private String imageUrl;
    private double currentPrice;
    private boolean availability;

    public Course() {
        this.publication = LocalDate.now();
        this.lastUpdate = LocalDate.now();
        this.availability = false;
    }

    public Course(CourseDTO courseDTO){
        new Course();
        this.id = courseDTO.getId();
        this.title = courseDTO.getTitle();
        this.description = courseDTO.getDescription();
        this.imageUrl = courseDTO.getImageUrl();
        this.currentPrice = courseDTO.getCurrentPrice();
        this.availability = courseDTO.getAvailability();
    }

    public void setId(String id){this.id = id;}
    public String getId() {return id;}

    public void setTitle(String title){this.title = title;}
    public String getTitle(){return this.title;}

    public void setDescription(String description){this.description = description;}
    public String getDescription(){return this.description;}

    public void setPublication(LocalDate publication){this.publication = publication;}
    public LocalDate getPublication(){return this.publication;}

    public void setLastUpdate(LocalDate lastUpdate){this.lastUpdate = lastUpdate;}
    public LocalDate getLastUpdate(){return this.lastUpdate;}

    public void setImageUrl(String imageUrl){this.imageUrl = imageUrl;}
    public String getImageUrl(){return this.imageUrl;}

    public void setCurrentPrice(double currentPrice){this.currentPrice = currentPrice;}
    public double getCurrentPrice(){return this.currentPrice;}

    public void setAvailability(boolean availability){this.availability = availability;}
    public boolean getAvailability(){return this.availability;}

}