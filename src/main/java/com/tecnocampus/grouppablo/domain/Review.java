package com.tecnocampus.grouppablo.domain;

import com.tecnocampus.grouppablo.application.dto.ReviewDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.time.LocalDate;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String contents;
    @Min(value = 0, message = "The satisfaction can not be negative.")
    @Max(value = 5, message = "The satisfaction can not be more than 5.")
    private int satisfaction;
    private LocalDate date;

    public Review(){}

    public Review(String title, String contents, int satisfaction){
        this.title = title;
        this.contents = contents;
        this.satisfaction = satisfaction;
        this.date = LocalDate.now();
    }

    public Review(ReviewDTO reviewDTO){
        this.id = reviewDTO.getId();
        this.title = reviewDTO.getTitle();
        this.contents = reviewDTO.getContents();
        this.satisfaction = reviewDTO.getSatisfaction();
        this.date = reviewDTO.getDate();
    }

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getContents() {return contents;}
    public void setContents(String contents) {this.contents = contents;}

    public int getSatisfaction() {return satisfaction;}
    public void setSatisfaction(int satisfaction) {this.satisfaction = satisfaction;}

    public LocalDate getDate() {return date;}
    public void setDate(LocalDate date) {this.date = date;}
}
