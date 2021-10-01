package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;

    protected String taskType;

    protected boolean isDone;

    protected LocalDate eventDate;

    public static final String DATE_FORMAT = "MM dd yyyy";


    public Task(String description) {
        this.description = description;

        this.isDone = false;

        this.taskType = "";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setAsDone() {
        this.isDone = true;
    }

    public String getType() {
        return taskType;
    }

    public LocalDate getWhen() {
        return eventDate;
    }

    @Override
    public String toString() {
        return ("[" + taskType + "]" + "[" + getStatusIcon() + "] " + description);
    }

}
