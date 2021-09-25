package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime time;
    private static final String dtFormat = "dd/MM/yyyy HHmm";
    protected DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dtFormat);

    public Deadline (String desc, String timeString, Boolean status) throws DateTimeParseException {
        super(desc, status);
        LocalDateTime time = LocalDateTime.parse(timeString, dtf);
        setTime(time);
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getTime() {
        return time.format(dtf);
    }

    @Override
    public String toString() {
        return "[d] [" + getStatus() + "] " + getDescription()
                + " (By: " + getTime() + ")";
    }

    public Boolean compareDate(LocalDate ld) {
        return ld.equals(time.toLocalDate());
    }
}
