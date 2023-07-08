package com.yigitcanyontem.aboutme.model;

public class Show {
    String id;
    boolean adult;
    String backdrop_path;
    String original_title;
    String overview;
    String poster_path;
    String first_air_date;
    String original_language;
    String imdb_url;
    Integer vote_count;
    Integer favorite_count;

    public Integer getFavorite_count() {
        return favorite_count;
    }



    public void setFavorite_count(Integer favorite_count) {
        this.favorite_count = favorite_count;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }

    public Show() {
    }

    public String getImdb_url() {
        return imdb_url;
    }

    public void setImdb_url(String imdb_url) {
        this.imdb_url = imdb_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }
    @Override
    public String toString() {
        return "Show{" +
                "id='" + id + '\'' +
                ", adult=" + adult +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", original_title='" + original_title + '\'' +
                ", overview='" + overview + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", first_air_date='" + first_air_date + '\'' +
                ", original_language='" + original_language + '\'' +
                ", imdb_url='" + imdb_url + '\'' +
                ", vote_count=" + vote_count +
                ", favorite_count=" + favorite_count +
                '}';
    }
}
