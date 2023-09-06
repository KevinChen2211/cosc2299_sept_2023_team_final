package au.edu.rmit.sept.app.superPrice.models;

import java.io.Serializable;

public class OpeningTime implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum DayOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    private DayOfWeek dayOfWeek;
    private boolean isClosed;
    private String from;  // may use java.time.LocalTime 
    private String to;    // may use java.time.LocalTime

    public OpeningTime(DayOfWeek dayOfWeek, boolean isClosed, String from, String to) {
        this.dayOfWeek = dayOfWeek;
        this.isClosed = isClosed;
        this.from = from;
        this.to = to;
    }

    // Getters and Setters
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
