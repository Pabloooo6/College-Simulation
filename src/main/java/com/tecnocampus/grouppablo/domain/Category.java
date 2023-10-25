package com.tecnocampus.grouppablo.domain;

import com.tecnocampus.grouppablo.application.dto.CategoryDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "category", unique = true)
    private String category;

    public Category(){
    }

    public Category(String category){
        this.category = category;
    }

    public Category(CategoryDTO categoryDTO){
        this.category = categoryDTO.getCategory();
    }

    public String getCategory(){return this.category;}
    public void setCategory(String category){this.category = category;}
}
