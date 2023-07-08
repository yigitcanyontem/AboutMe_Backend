package com.yigitcanyontem.aboutme.users;


import java.sql.Date;
import java.util.List;

public class UserModel {
    private Integer id;
    private String firstName;
    private String lastName;
    private Date date_of_birth;
    private Integer country;
    private String email;
    List<String> roles;
    private String username;
    String profileImageId;

    public UserModel(Integer id, String firstName, String lastName, Date date_of_birth, Integer country, String email, List<String> roles, String username, String profileImageId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date_of_birth = date_of_birth;
        this.country = country;
        this.email = email;
        this.roles = roles;
        this.username = username;
        this.profileImageId = profileImageId;
    }


    public UserModel(String firstName, String lastName, Date date_of_birth, Integer country, String email, List<String> roles, String username, String profileImageId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.date_of_birth = date_of_birth;
        this.country = country;
        this.email = email;
        this.roles = roles;
        this.username = username;
        this.profileImageId = profileImageId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getProfileImageId() {
        return profileImageId;
    }

    public void setProfileImageId(String profileImageId) {
        this.profileImageId = profileImageId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
