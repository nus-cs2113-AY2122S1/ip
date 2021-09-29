package duke.tasklist.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    protected String at;
    protected LocalDate date;
    protected LocalTime time;

    public Events(String description, String at) {
        super(description);
        this.at = at;
        this.date = LocalDate.parse(at.split(" ")[0], DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        this.time = LocalTime.parse(readTime(at));
    }
    private String readTime(String str) {
        String hour = str.split(" ")[1].substring(0, 2);
        String colon = ":";
        String minutes = str.split(" ")[1].substring(2, 4);
        return hour.concat(colon).concat(minutes);
    }
    @Override
    public String getDate() {
        return this.at;
    }
    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    private String formatTime(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern("hh mm"));
    }
    @Override
    public LocalDate getDeadline() {
        return this.date;
    }
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatDate(this.date) + " " + formatTime(this.time) + ")";
    }
}
