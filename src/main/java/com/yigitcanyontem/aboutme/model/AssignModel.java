package com.yigitcanyontem.aboutme.model;



public class AssignModel {
    private String username;
    private String description;
    private String instagramuser;
    private String pinterestuser;
    private String linkedinuser;
    private String twitteruser;

    public AssignModel() {
    }

    @Override
    public String toString() {
        return "AssignModel{" +
                "username='" + username + '\'' +
                ", description='" + description + '\'' +
                ", instagramuser='" + instagramuser + '\'' +
                ", pinterestuser='" + pinterestuser + '\'' +
                ", linkedinuser='" + linkedinuser + '\'' +
                ", twitteruser='" + twitteruser + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstagramuser() {
        return instagramuser;
    }

    public void setInstagramuser(String instagramuser) {
        this.instagramuser = instagramuser;
    }

    public String getPinterestuser() {
        return pinterestuser;
    }

    public void setPinterestuser(String pinterestuser) {
        this.pinterestuser = pinterestuser;
    }

    public String getLinkedinuser() {
        return linkedinuser;
    }

    public void setLinkedinuser(String linkedinuser) {
        this.linkedinuser = linkedinuser;
    }

    public String getTwitteruser() {
        return twitteruser;
    }

    public void setTwitteruser(String twitteruser) {
        this.twitteruser = twitteruser;
    }
}
