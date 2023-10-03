package com.tecnocampus.grouppablo.domain;

import com.tecnocampus.grouppablo.application.dto.CourseDTO;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Date publication;
    private Date lastUpdate;
    private String imageUrl;
    private double currentPrice;
    private boolean availability;

    public Course() {
        this.publication = new Date();
        this.availability = false;
    }

    public Course(CourseDTO courseDTO){
        new Course();
        this.title = courseDTO.getTitle();
        this.description = courseDTO.getDescription();
        this.imageUrl = courseDTO.getImageUrl();
    }

    public void setId(Long id){this.id = id;}
    public Long getId() {return id;}

    public void setTitle(String title){this.title = title;}
    public String getTitle(){return this.title;}

    public void setDescription(String description){this.description = description;}
    public String getDescription(){return this.description;}

    public void setPublication(Date publication){this.publication = publication;}
    public Date getPublication(){return this.publication;}

    public void setLastUpdate(Date lastUpdate){this.lastUpdate = lastUpdate;}
    public Date getLastUpdate(){return this.lastUpdate;}

    public void setImageUrl(String imageUrl){this.imageUrl = imageUrl;}
    public String getImageUrl(){return this.imageUrl;}

    public void setCurrentPrice(double currentPrice){this.currentPrice = currentPrice;}
    public double getCurrentPrice(){return this.currentPrice;}

    public void setAvailability(boolean availability){this.availability = availability;}
    public boolean getAvailability(){return this.availability;}

}