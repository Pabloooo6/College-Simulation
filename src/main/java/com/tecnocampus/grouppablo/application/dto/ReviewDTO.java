package com.tecnocampus.grouppablo.application.dto;

import com.tecnocampus.grouppablo.domain.Review;
import java.io.Serializable;
import java.time.LocalDate;

public class ReviewDTO implements Serializable {

    private long id;
    private String title;
    private String contents;
    private int satisfaction;
    private LocalDate date;

    public ReviewDTO(){}

    public ReviewDTO(Review review){
        this.id = review.getId();
        this.title = review.getTitle();
        this.contents = review.getContents();
        this.satisfaction = review.getSatisfaction();
        this.date = review.getDate();
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
