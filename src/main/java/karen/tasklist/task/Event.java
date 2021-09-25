package karen.tasklist.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private String at;
    private LocalDate date;
    private LocalTime time;
    private String eventTask;
    private String formattedDescription;
    private String formattedDateAndTime;

    public Event(String fullTaskDescription, String eventTask, String at) {
        super(fullTaskDescription);
        this.at = at;
        this.date = findDate();
        this.time = findTime();
        this.eventTask = eventTask;
        this.formattedDescription = getFormattedDescription();
        this.formattedDateAndTime = getFormattedDateAndTime();
    }


    public String getType(){
        return "Event";
    }

    public LocalDate findDate() {
        String[] splitDateAndTime = this.at.split(" ", 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(splitDateAndTime[0], formatter);
        return date;
    }

    public LocalTime findTime() {
        String[] splitDateAndTime = this.at.split(" ", 0);
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
        return String.format("%s (at: %s)", this.eventTask, this.formattedDateAndTime);
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
        return String.format("Event@%s@%s@%s",getStatusIcon(), this.eventTask, this.at);
    }

    //obtain the task to do from the input description
    public String getTask(){
        return eventTask;
    }

}
