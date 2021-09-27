package tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(boolean done, String name, String date) {
        super(done, name);
        this.deadline = LocalDate.parse(date);
    }

    public Deadline() {
        super(false, "Nothing");
        this.deadline = LocalDate.parse("2021-12-31");
    }

    public LocalDate getTaskDate() {
        return deadline;
    }

    public String getStringDeadline() {
        return deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    @Override
    public String getPrefix() {
        return "[D]";
    }

    @Override
    public String toString() {
        return getPrefix() + super.toString() + "(by: " + getStringDeadline() + ")";
    }
}