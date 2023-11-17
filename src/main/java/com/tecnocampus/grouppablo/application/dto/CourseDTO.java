package com.tecnocampus.grouppablo.application.dto;

import com.tecnocampus.grouppablo.domain.Category;
import com.tecnocampus.grouppablo.domain.Course;
import com.tecnocampus.grouppablo.domain.Language;
import com.tecnocampus.grouppablo.domain.User;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private CategoryDTO categoryDTO;
    private LanguageDTO languageDTO;
    private UserDTO teacher;
    private List<LessonDTO> lessonsDTO = new ArrayList<>();

    public CourseDTO(){
    }

    public CourseDTO(String title, String description, String imageUrl){
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public CourseDTO(String title, String description, LocalDate publication, LocalDate lastUpdate, String imageUrl,
                     double currentPrice, boolean availability, Category category, Language language, User teacher){
        this.title = title;
        this.description = description;
        this.publication = publication;
        this.lastUpdate = lastUpdate;
        this.imageUrl = imageUrl;
        this.currentPrice = currentPrice;
        this.availability = availability;
        this.categoryDTO = new CategoryDTO(category);
        this.languageDTO = new LanguageDTO(language);
        this.teacher = new UserDTO(teacher);
    }

    public CourseDTO(String title, String description, LocalDate publication, LocalDate lastUpdate, String imageUrl,
                     double currentPrice, boolean availability){
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
        if(course.getCategory() == null) this.categoryDTO = new CategoryDTO("none");
        else this.categoryDTO = new CategoryDTO(course.getCategory());
        if(course.getLanguage() == null) this.languageDTO = new LanguageDTO("none");
        else this.languageDTO = new LanguageDTO(course.getLanguage());
        this.teacher = new UserDTO(course.getTeacher());
        this.lessonsDTO = course.getLessons().stream().map(LessonDTO::new).collect(Collectors.toList());
    }

    public CourseDTO(String title, String description) {
        this.title = title;
        this.description = description;
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

    public void setCategory(CategoryDTO category){this.categoryDTO = category;}
    public CategoryDTO getCategory(){return this.categoryDTO;}

    public void setLanguage(LanguageDTO language){this.languageDTO = language;}
    public LanguageDTO getLanguage(){return this.languageDTO;}

    public void setTeacher(UserDTO teacher){this.teacher = teacher;}
    public UserDTO getTeacher(){return this.teacher;}

    public List<LessonDTO> getLessonsDTO(){return this.lessonsDTO;}
    public void setLessonsDTO(List<LessonDTO> lessonsDTO) {this.lessonsDTO = lessonsDTO;}
}