package com.tecnocampus.grouppablo.domain;

import com.tecnocampus.grouppablo.application.dto.LanguageDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "language")
public class Language {

    @Id
    @Column(name = "language", unique = true)
    private String language;

    public Language(){
    }

    public Language(String language){
        this.language = language;
    }

    public Language(LanguageDTO languageDTO){
        this.language = languageDTO.getLanguage();
    }

    public String getLanguage() {return language;}
    public void setLanguage(String language) {this.language = language;}
}
