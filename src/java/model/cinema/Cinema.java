/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.cinema;

import java.util.ArrayList;
import model.movie.MovieShowing;
import model.movie.Movie_MovieShowing;

/**
 *
 * @author Ducky
 */
public class Cinema {
    private int id;
    private String name;
    private String address;
    private String phone;
    private ArrayList<MovieShowing> movieShowings = new ArrayList<>();
    private ArrayList<RowSeat> rowSeats = new ArrayList<>();
    private ArrayList<PerformanceNumber> performanceNumbers = new ArrayList<>();

    public Cinema() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<MovieShowing> getMovieShowings() {
        return movieShowings;
    }

    public void setMovieShowings(ArrayList<MovieShowing> movieShowings) {
        this.movieShowings = movieShowings;
    }

    public ArrayList<RowSeat> getRowSeats() {
        return rowSeats;
    }

    public void setRowSeats(ArrayList<RowSeat> rowSeats) {
        this.rowSeats = rowSeats;
    }

    public ArrayList<PerformanceNumber> getPerformanceNumbers() {
        return performanceNumbers;
    }

    public void setPerformanceNumbers(ArrayList<PerformanceNumber> performanceNumbers) {
        this.performanceNumbers = performanceNumbers;
    }
    
    
}
