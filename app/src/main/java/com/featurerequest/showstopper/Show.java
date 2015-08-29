package com.featurerequest.showstopper;

/**
 * Created by kannon on 8/28/2015.
 */
public class Show
{
    private long id;
    private String title;
    private String genre;
    private int year;
    private boolean movie;
    private boolean banned;
    private int rating;
    private String synopsis;
    private int length;
    private boolean amazon;
    private boolean netflix;
    private boolean itunes;
    private boolean google;
    private int imdb;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isMovie() {
        return movie;
    }

    public void setMovie(boolean isMovie) {
        this.movie = isMovie;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isAmazon() {
        return amazon;
    }

    public void setAmazon(boolean amazon) {
        this.amazon = amazon;
    }

    public boolean isNetflix() {
        return netflix;
    }

    public void setNetflix(boolean netflix) {
        this.netflix = netflix;
    }

    public boolean isItunes() {
        return itunes;
    }

    public void setItunes(boolean itunes) {
        this.itunes = itunes;
    }

    public boolean isGoogle() {
        return google;
    }

    public void setGoogle(boolean google) {
        this.google = google;
    }

    public int getImdb() {
        return imdb;
    }

    public void setImdb(int imdb) {
        this.imdb = imdb;
    }

    @Override
    public String toString()
    {
        return this.title + "(" + year + ")";
    }
}
