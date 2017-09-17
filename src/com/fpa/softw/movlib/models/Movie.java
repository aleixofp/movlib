package com.fpa.softw.movlib.models;

public class Movie {

    private int id, userId;
    private String title;
    private double userRating;
    private boolean watched, recommends;
    private int watchedYear;

    public Movie(int id, String title, double userRating, int watchedYear, boolean watched, boolean recommends, int userId) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.userRating = userRating;
        this.watched = watched;
        this.recommends = recommends;
        this.watchedYear = watchedYear;
    }

    public Movie(String title, double userRating, int watchedYear, boolean watched, boolean recommends, int userId) {

        this.userId = userId;
        this.title = title;
        this.userRating = userRating;
        this.watched = watched;
        this.recommends = recommends;
        this.watchedYear = watchedYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    public int getWatchedYear() {
        return watchedYear;
    }

    public void setWatchedYear(int watchedYear) {
        this.watchedYear = watchedYear;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    public boolean isRecommends() {
        return recommends;
    }

    public void setRecommends(boolean recommends) {
        this.recommends = recommends;
    }

    @Override
    public String toString() {
        return "Title: " + this.getTitle() + " | Your rating: " + this.getUserRating();
    }
}
