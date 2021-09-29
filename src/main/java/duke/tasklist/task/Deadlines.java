package duke.tasklist.task;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    protected String by;
    protected LocalDate date;
    protected LocalTime time;

    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
        this.date = LocalDate.parse(by.split(" ")[0], DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        this.time = LocalTime.parse(readTime(by));

    }
    /**
     * Reformat the time parameter input to one that is readable by the library
     *
     * @param str is the string input by the user after '/by'
     * @return a string that is reformatted to the right format for LocalTime.parse()
     */
    private String readTime(String str) {
        String hour = str.split(" ")[1].substring(0, 2);
        String colon = ":";
        String minutes = str.split(" ")[1].substring(2, 4);
        return hour.concat(colon).concat(minutes);
    }
    @Override
    public String getDate() {
        return this.by;
    }
    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }
    private String formatTime(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern("hh mm a"));
    }
    @Override
    public LocalDate getDeadline() {
        return this.date;
    }
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDate(this.date) + ", " + formatTime(this.time) + ")";
    }
}

