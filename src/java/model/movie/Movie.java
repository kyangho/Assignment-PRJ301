/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.movie;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Ducky
 */
public class Movie {
    private int id;
    private String name;
    private String director;
    private String cast;
    private ArrayList<MovieGenre> genre = new ArrayList<>();
    private ArrayList<MovieLanguage> languages = new ArrayList<>();
    private Date runningTime;
    private MovieShowing movieShowing;
    private int duration;
    private String rated;
    private String description;
    private String urlTrailer;
    private String urlImage;

    public Movie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }


    public MovieShowing getMovieShowing() {
        return movieShowing;
    }

    public void setMovieShowing(MovieShowing movieShowing) {
        this.movieShowing = movieShowing;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlTrailer() {
        return urlTrailer;
    }

    public void setUrlTrailer(String urlTrailer) {
        this.urlTrailer = urlTrailer;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public ArrayList<MovieGenre> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<MovieGenre> genre) {
        this.genre = genre;
    }

    public ArrayList<MovieLanguage> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<MovieLanguage> languages) {
        this.languages = languages;
    }

    public Date getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(Date runningTime) {
        this.runningTime = runningTime;
    }

    
    
}
