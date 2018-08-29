package java.time;

/**
 * Meant to be used only for Android Development Purpose
 * @author RAM MAHARAJ
 */
public class LocalDateTime {
    private LocalDate date;
    private LocalTime time;

    public LocalDateTime() {
    }

    public LocalDateTime(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }
    
    
    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
    
    
    
}
