package com.tecnocampus.grouppablo.domain;

import com.tecnocampus.grouppablo.application.dto.EnrolDTO;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Enrol {

    @EmbeddedId
    private EnrolId enrolId;
    @MapsId("username")
    @ManyToOne
    private User user;
    @MapsId("courseId")
    @ManyToOne
    private Course course;
    @MapsId("lessonTitle")
    @ManyToMany
    private List<Lesson> finishedLessons = new ArrayList<>();
    private LocalDate date;
    private double price;

    public Enrol(){}

    public Enrol(User user, Course course){
        this.user = user;
        this.course = course;
        this.enrolId = new EnrolId(user.getUsername(), course.getId());
        this.date = LocalDate.now();
        this.price = course.getCurrentPrice();
    }

    public Enrol (EnrolDTO enrolDTO){
        this.enrolId = new EnrolId(enrolDTO.getEnrolIdDTO());
        this.user = new User(enrolDTO.getUserDTO());
        this.course = new Course(enrolDTO.getCourseDTO());
        this.finishedLessons = enrolDTO.getFinishedLessons().stream().map(Lesson::new).collect(Collectors.toList());
        this.date = enrolDTO.getDate();
        this.price = enrolDTO.getPrice();
    }

    public EnrolId getEnrolId(){return this.enrolId;}
    public void setEnrolId(EnrolId enrolId){this.enrolId = enrolId;}

    public User getUser(){return this.user;}
    public void setUser(User user){this.user = user;}

    public Course getCourse(){return this.course;}
    public void setCourse(Course course){this.course = course;}

    public List<Lesson> getFinishedLessons(){return this.finishedLessons;}
    public void setFinishedLessons(List<Lesson> finishedLessons){this.finishedLessons = finishedLessons;}

    public LocalDate getDate(){return this.date;}
    public void setDate(LocalDate date){this.date = date;}

    public double getPrice(){return this.price;}
    public void setPrice(double price){this.price = price;}
}
