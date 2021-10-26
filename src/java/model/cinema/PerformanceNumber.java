/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.cinema;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Ducky
 */
public class PerformanceNumber {
    private int number;
    private Time fromTime;
    private Time toTime;

    public PerformanceNumber() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Time getFromTime() {
        return fromTime;
    }

    public void setFromTime(Time fromTime) {
        this.fromTime = fromTime;
    }

    public Time getToTime() {
        return toTime;
    }

    public void setToTime(Time toTime) {
        this.toTime = toTime;
    }
    
    
}
