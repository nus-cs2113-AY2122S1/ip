package karen.tasklist.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private String by;
    private LocalDate date;
    private LocalTime time;
    private String deadlineTask;
    private String formattedDescription;
    private String formattedDateAndTime;

    public Deadline(String fullTaskDescription, String deadlineTask, String by) {
        super(fullTaskDescription);
        this.by = by;
        this.date = findDate();
        this.time = findTime();
        this.deadlineTask = deadlineTask;
        this.formattedDescription = getFormattedDescription();
        this.formattedDateAndTime = getFormattedDateAndTime();
    }

    public String getType() {
        return "Deadline";
    }

    public LocalDate findDate() {
        String[] splitDateAndTime = this.by.split(" ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(splitDateAndTime[0], formatter);
        return date;
    }

    public LocalTime findTime() {
        String[] splitDateAndTime = this.by.split(" ");
        if (splitDateAndTime.length == 2) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
            LocalTime time = LocalTime.parse(splitDateAndTime[1], formatter);
            return time;
        }
        return null;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public String getFormattedDescription() {
        return String.format("%s (by: %s)", this.deadlineTask, this.formattedDateAndTime);
    }

    public String getFormattedDateAndTime() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, E");
        String formattedDateAndTime = this.date.format(dateFormatter);
        if (this.time != null) {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
            formattedDateAndTime += String.format(", " + this.time.format(timeFormatter) + "h");

        }
        return formattedDateAndTime;
    }

    public String getFormattedFileDescription() {
        return String.format("Deadline@%s@%s@%s",getStatusIcon(), this.deadlineTask, this.by);
    }


    //obtain the task to do from the input description
    public String getTask() {
        return deadlineTask;
    }
}
