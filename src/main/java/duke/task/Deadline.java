package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MMM-yy");
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");
    private static final ZoneOffset zone = ZoneOffset.UTC;
    private LocalDate byDate;
    private LocalTime byTime;

    public Deadline(String taskName, LocalDate byDate, LocalTime byTime) {
        super(taskName);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    public Deadline(String taskName, LocalDateTime byDateTime) {
        super(taskName);
        byDate = byDateTime.toLocalDate();
        byTime = byDateTime.toLocalTime();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate.format(dateFormat) + " " + byTime.format(timeFormat) + ")";
    }

    @Override
    public String getStorageString() {
        String c = isCompleted ? "1" : "0";
        return "D | " + c + " | " + taskName + " | " + byTime.toEpochSecond(byDate, zone);
    }
}
