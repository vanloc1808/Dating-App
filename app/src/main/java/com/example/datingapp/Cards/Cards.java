package com.example.datingapp.Cards;

public class Cards {
    private String email;
    private String name;
    private String profilePictureURL;
    private String need;
    private String give;
    private String budget;


    public Cards(String email, String name, String profilePictureURL, String need, String give, String budget) {
        this.email = email;
        this.name = name;
        this.profilePictureURL = profilePictureURL;
        this.need = need;
        this.give = give;
        this.budget = budget;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    public String getNeed() {
        return need;
    }

    public String getGive() {
        return give;
    }

    public String getBudget() {
        return budget;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfilePictureURL(String profilePictureURL) {
        this.profilePictureURL = profilePictureURL;
    }

    public void setNeed(String need) {
        this.need = need;
    }

    public void setGive(String give) {
        this.give = give;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }
}
