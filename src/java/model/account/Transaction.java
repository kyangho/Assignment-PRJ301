/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.account;

import java.sql.Date;
import java.util.ArrayList;
import model.cinema.Cinema;
import model.cinema.PerformanceNumber;
import model.movie.Movie;

/**
 *
 * @author Ducky
 */
public class Transaction {
    private int id;
    private Date transaction_made_date;
    private float price;
    private Movie movie;
    private PerformanceNumber performanceNumber;
    private ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    private Cinema cinema;
    public Transaction() {
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }
    
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public PerformanceNumber getPerformanceNumber() {
        return performanceNumber;
    }

    public void setPerformanceNumber(PerformanceNumber performanceNumber) {
        this.performanceNumber = performanceNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTransaction_made_date() {
        return transaction_made_date;
    }

    public void setTransaction_made_date(Date transaction_made_date) {
        this.transaction_made_date = transaction_made_date;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }
    
    
}
