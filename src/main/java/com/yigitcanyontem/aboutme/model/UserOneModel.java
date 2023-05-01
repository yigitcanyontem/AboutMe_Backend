package com.yigitcanyontem.aboutme.model;

import com.yigitcanyontem.aboutme.entities.Description;
import com.yigitcanyontem.aboutme.entities.SocialMedia;
import com.yigitcanyontem.aboutme.entities.Users;

import java.util.List;

public class UserOneModel {
    private Users users;
    private SocialMedia socialMedia;
    private Description description;
    private List<Movie> favmovies;
    private List<Show> favshows;
    private List<Album> favalbums;
    private List<Book> favbooks;

    public UserOneModel() {
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public SocialMedia getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(SocialMedia socialMedia) {
        this.socialMedia = socialMedia;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public List<Movie> getFavmovies() {
        return favmovies;
    }

    public void setFavmovies(List<Movie> favmovies) {
        this.favmovies = favmovies;
    }

    public List<Show> getFavshows() {
        return favshows;
    }

    public void setFavshows(List<Show> favshows) {
        this.favshows = favshows;
    }

    public List<Album> getFavalbums() {
        return favalbums;
    }

    public void setFavalbums(List<Album> favalbums) {
        this.favalbums = favalbums;
    }

    public List<Book> getFavbooks() {
        return favbooks;
    }

    public void setFavbooks(List<Book> favbooks) {
        this.favbooks = favbooks;
    }
}
