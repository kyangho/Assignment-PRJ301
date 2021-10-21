/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.account;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Ducky
 */
public class Transaction {
    private int id;
    private Date transaction_made_date;
    private ArrayList<Ticket> tickets = new ArrayList<Ticket>();

    public Transaction() {
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
