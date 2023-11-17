package com.tecnocampus.grouppablo.domain;

import com.tecnocampus.grouppablo.application.dto.UserDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "userLab")
public class User {

    private String name;
    private String secondName;
    private String thirdName;
    private String email;
    private String gender;
    @Id
    @Column(name = "username", unique = true)
    private String username;

    public User(){
    }

    public User(UserDTO userDTO) {
        this.name = userDTO.getName();
        this.secondName = userDTO.getSecondName();
        this.thirdName = userDTO.getThirdName();
        this.email = userDTO.getEmail();
        this.gender = userDTO.getGender();
        this.username = userDTO.getUsername();
    }

    public void setUsername(String username){this.username = username;}
    public String getUsername(){return this.username;}

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
}
