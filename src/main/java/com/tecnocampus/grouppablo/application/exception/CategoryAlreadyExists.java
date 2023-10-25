package com.tecnocampus.grouppablo.application.exception;

public class CategoryAlreadyExists extends RuntimeException{
    public CategoryAlreadyExists(String category)  {
        super("Category:" + category + " already exists");
    }
}
