package java.time;

import java.util.Date;

/**
 * Meant to be used only for Android Development Purpose
 * @author RAM MAHARAJ
 */
public class LocalTime {
 
    private int hour;
    private int minute;
    private int second;
    private int nano;

    public LocalTime() {
    }

    public LocalTime(int hour, int minute, int second, int nano) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.nano = nano;
    }

    
    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getNano() {
        return nano;
    }

    public void setNano(int nano) {
        this.nano = nano;
    }
    
    public Date getTime(){
        return new Date(0,0,0,hour, minute, second);
    }
    
}
