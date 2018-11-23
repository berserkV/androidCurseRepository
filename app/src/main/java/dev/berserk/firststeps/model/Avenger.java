package dev.berserk.firststeps.model;

public class Avenger {

    private String name;

    private String gender;

    private String picture;

    private String phrase;

    private Boolean isDead;

    private Boolean gem;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public Boolean getDead() {
        return isDead;
    }

    public void setDead(Boolean dead) {
        isDead = dead;
    }

    public Boolean getGem() {
        return gem;
    }

    public void setGem(Boolean gem) {
        this.gem = gem;
    }
}
