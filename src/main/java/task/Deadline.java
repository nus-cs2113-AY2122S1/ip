package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime time;
    protected DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public Deadline (String desc, String timeString, Boolean status) throws DateTimeParseException {
        super(desc, status);
        LocalDateTime time = LocalDateTime.parse(timeString, dtf);
        setTime(time);
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getTime() {
        String formatted = time.format(dtf);
        return time.format(dtf);
    }

    public String toString() {
        return "[d] [" + getStatus() + "] " + getDescription()
                + " (By: " + getTime() + ")";
    }
}
