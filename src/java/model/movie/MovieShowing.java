/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.movie;

import java.sql.Date;

/**
 *
 * @author Ducky
 */
public class MovieShowing {
    private int id;
    private Date fromShowing;
    private Date toShowing;

    public MovieShowing() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFromShowing() {
        return fromShowing;
    }

    public void setFromShowing(Date fromShowing) {
        this.fromShowing = fromShowing;
    }

    public Date getToShowing() {
        return toShowing;
    }

    public void setToShowing(Date toShowing) {
        this.toShowing = toShowing;
    }
    
    
}
