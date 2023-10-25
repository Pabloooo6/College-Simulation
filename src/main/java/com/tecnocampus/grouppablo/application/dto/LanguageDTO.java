package com.tecnocampus.grouppablo.application.dto;

import com.tecnocampus.grouppablo.domain.Language;
import java.io.Serializable;

public class LanguageDTO implements Serializable {

    private String language;

    public LanguageDTO(){
    }

    public LanguageDTO(String language){
        this.language = language;
    }

    public LanguageDTO(Language language){
        this.language = language.getLanguage();
    }

    public String getLanguage() {return language;}
    public void setLanguage(String language) {this.language = language;}
}
