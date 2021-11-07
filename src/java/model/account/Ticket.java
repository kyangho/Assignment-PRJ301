/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.account;

import java.sql.Date;

/**
 *
 * @author Ducky
 */
public class Ticket {
    private int id;
    private int cinema_id;
    private int row_number;
    private int seat_number;
    private int movie_id;
    private Date ticket_purchase_date;
    private int performance_number;
    private int price_id;
    private float price;
    private int transaction_id;
    
    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCinema_id() {
        return cinema_id;
    }

    public void setCinema_id(int cinema_id) {
        this.cinema_id = cinema_id;
    }

    public int getRow_number() {
        return row_number;
    }

    public void setRow_number(int row_number) {
        this.row_number = row_number;
    }

    public int getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(int seat_number) {
        this.seat_number = seat_number;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public Date getTicket_purchase_date() {
        return ticket_purchase_date;
    }

    public void setTicket_purchase_date(Date ticket_purchase_date) {
        this.ticket_purchase_date = ticket_purchase_date;
    }

    public int getPerformance_number() {
        return performance_number;
    }

    public void setPerformance_number(int performance_number) {
        this.performance_number = performance_number;
    }

    public int getPrice_id() {
        return price_id;
    }

    public void setPrice_id(int price_id) {
        this.price_id = price_id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }
}
