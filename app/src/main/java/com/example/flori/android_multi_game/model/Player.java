package com.example.flori.android_multi_game.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Player extends RealmObject {

    @PrimaryKey
    private String name;
    private String firstName;
    private int age;
    private String localisation;
    private String picture;
    private int score_dragndrop;
    private int score_swipe;
    private int score_fasttap;
    private int score_ipac;

    public Player() {
    }

    public Player(String name, String firstName, int age, String localisation, String picture) {
        setName(name);
        setFirstName(firstName);
        setAge(age);
        setLocalisation(localisation);
        setPicture(picture);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    public String getLocalisation() {
        return localisation;
    }

    private void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getPicture() {
        return picture;
    }

    private void setPicture(String picture) {
        this.picture = picture;
    }

    public int getScoreDragndrop() {
        return score_dragndrop;
    }

    public void setScoreDragndrop(int score_dragndrop) {
        this.score_dragndrop = score_dragndrop;
    }

    public int getScoreSwipe() {
        return score_swipe;
    }

    public void setScoreSwipe(int score_swipe) {
        this.score_swipe = score_swipe;
    }

    public int getScoreFasttap() {
        return score_fasttap;
    }

    public void setScoreFasttap(int score_fasttap) {
        this.score_fasttap = score_fasttap;
    }

    public int getScoreIpac() {
        return score_ipac;
    }

    public void setScoreIpac(int score_ipac) {
        this.score_ipac = score_ipac;
    }
}
