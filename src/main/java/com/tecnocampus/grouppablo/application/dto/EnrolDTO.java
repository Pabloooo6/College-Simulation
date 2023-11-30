package com.tecnocampus.grouppablo.application.dto;

import com.tecnocampus.grouppablo.domain.Enrol;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EnrolDTO implements Serializable {

    private EnrolIdDTO enrolIdDTO;
    private UserDTO userDTO;
    private CourseDTO courseDTO;
    private List<LessonDTO> finishedLessons = new ArrayList<>();
    private LocalDate date;
    private double price;
    private ReviewDTO reviewDTO;

    public EnrolDTO(){}

    public EnrolDTO (Enrol enrol){
        this.enrolIdDTO = new EnrolIdDTO(enrol.getEnrolId());
        this.userDTO = new UserDTO(enrol.getUser());
        this.courseDTO = new CourseDTO(enrol.getCourse());
        this.finishedLessons = enrol.getFinishedLessons().stream().map(LessonDTO::new).collect(Collectors.toList());
        this.date = enrol.getDate();
        this.price = enrol.getPrice();
    }

    public static EnrolDTO enrolDTODetails(Enrol enrol){
        EnrolDTO enrolDTO = new EnrolDTO();
        enrolDTO.setCourseDTO(new CourseDTO(enrol.getCourse()));
        enrolDTO.setFinishedLessons(enrol.getFinishedLessons().stream().map(LessonDTO::new).collect(Collectors.toList()));
        return enrolDTO;
    }

    public EnrolIdDTO getEnrolIdDTO() {return this.enrolIdDTO;}
    public void setEnrolIdDTO(EnrolIdDTO enrolIdDTO) {this.enrolIdDTO = enrolIdDTO;}

    public UserDTO getUserDTO() {return this.userDTO;}
    public void setUserDTO(UserDTO userDTO) {this.userDTO = userDTO;}

    public CourseDTO getCourseDTO() {return this.courseDTO;}
    public void setCourseDTO(CourseDTO courseDTO) {this.courseDTO = courseDTO;}

    public List<LessonDTO> getFinishedLessons() {return this.finishedLessons;}
    public void setFinishedLessons(List<LessonDTO> finishedLessons) {this.finishedLessons = finishedLessons;}

    public LocalDate getDate() {return this.date;}
    public void setDate(LocalDate date) {this.date = date;}

    public double getPrice() {return this.price;}
    public void setPrice(double price) {this.price = price;}

    public ReviewDTO getReviewDTO(){return this.reviewDTO;}
    public void setReviewDTO(ReviewDTO reviewDTO){this.reviewDTO = reviewDTO;}
}
