package com.tecnocampus.grouppablo.application.dto;

import com.tecnocampus.grouppablo.domain.Category;
import java.io.Serializable;

public class CategoryDTO implements Serializable {

    private String category;

    public CategoryDTO(){
    }

    public CategoryDTO(String category){
        this.category = category;
    }

    public CategoryDTO(Category category){
        this.category = category.getCategory();
    }

    public String getCategory(){return this.category;}
    public void setCategory(String category){this.category = category;}
}
