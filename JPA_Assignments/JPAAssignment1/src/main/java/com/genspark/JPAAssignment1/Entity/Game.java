package com.genspark.JPAAssignment1.Entity;

import javax.persistence.*;

@Entity
public class Game {

    @Id
    @Column(name="g_id")
    @GeneratedValue
    private int gameId;// Primary ID for game identification purposes
    @Column(name="g_Title")
    private String title; // title of the game
    @Column(name="g_totalplaytime")
    private int playTime; // time in hours rounded to nearest hour
    @Column(name="g_rating")
    private int rating; // out of 100

    public Game() {
    }

    public Game(String title, int playTime, int rating){
        this.title = title;
        this.playTime = playTime;
        this.rating = rating;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
