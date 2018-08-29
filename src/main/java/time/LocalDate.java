package time;

import java.util.Date;

/**
 * Meant to be used only for Android Development Purpose
 * @author RAM MAHARAJ
 */

public class LocalDate {
    
    private int year;
    private int month;
    private int day;

    public LocalDate() {
    }

    public LocalDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
    
    
    public Date getDate(){
        return new Date(year, month, day);
    }
    
}
