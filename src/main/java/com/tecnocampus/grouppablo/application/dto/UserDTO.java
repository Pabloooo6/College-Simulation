package com.tecnocampus.grouppablo.application.dto;

import com.tecnocampus.grouppablo.domain.User;
import java.io.Serializable;

public class UserDTO implements Serializable {

    private String name;
    private String secondName;
    private String thirdName;
    private String email;
    private String gender;
    private String username;
    private double rating;

    public UserDTO(){
    }

    public UserDTO(String name, String secondName, String thirdName, String email, String gender, String username){
        this.name = name;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.email = email;
        this.gender = gender;
        this.username = username;
    }

    public UserDTO(User user){
        this.name = user.getName();
        this.secondName = user.getSecondName();
        this.thirdName = user.getThirdName();
        this.email = user.getEmail();
        this.gender = user.getGender();
        this.username = user.getUsername();
    }

    public UserDTO(String name){
        this.name = name;
    }

    public UserDTO(String username, String name, double rating){
        this.username = username;
        this.name = name;
        this.rating = rating;
    }

    public void setName(String name){this.name = name;}
    public String getName(){return this.name;}

    public void setSecondName(String secondName){this.secondName = secondName;}
    public String getSecondName(){return this.secondName;}

    public void setThirdName(String thirdName){this.thirdName = thirdName;}
    public String getThirdName(){return this.thirdName;}

    public void setEmail(String email){this.email = email;}
    public String getEmail(){return this.email;}

    public void setGender(String gender) {this.gender = gender;}
    public String getGender(){return this.gender;}

    public void setUsername(String username){this.username = username;}
    public String getUsername(){return this.username;}

    public void setRating(double rating){this.rating = rating;}
    public double getRating() {return this.rating;}
}
