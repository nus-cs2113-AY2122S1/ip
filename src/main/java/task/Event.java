package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    private static final String dtFormat = "dd/MM/yyyy HHmm";
    protected DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dtFormat);

    public Event(String desc, String startString, String endString, Boolean status) throws DateTimeParseException {
        super(desc, status);
        LocalDateTime start = LocalDateTime.parse(startString, dtf);
        LocalDateTime end = LocalDateTime.parse(endString, dtf);
        setTime(start, end);
    }

    public void setTime(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start.format(dtf);
    }

    public String getEnd() {
        return end.format(dtf);
    }

    public String toString() {
        return "[e] [" + getStatus() + "] " + getDescription()
                + " (" + getStart() + " to " + getEnd() + ")";
    }

}
