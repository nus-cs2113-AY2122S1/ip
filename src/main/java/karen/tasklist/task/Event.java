package karen.tasklist.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDate date;
    private LocalTime time;
    private String at;

    public Event(String taskDescription, String at, LocalDate date, LocalTime time){
        super(taskDescription);
        this.at = at;
        this.date = date;
        this.time = time;
    }

    /**
     * Returns "Event" as the task type.
     *
     * @return String to represent task type of Event object
     */
    public String getType() {
        return "Event";
    }

    /**
     * Returns the date that the task occurs on.
     *
     * @return task date as a LocalDate
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns the time that the task occurs on.
     *
     * @return task time as a LocalTime
     */
    public LocalTime getTime() {
        return this.time;
    }

    /**
     * Returns the date and time which the Event task object is at, as a String.
     *
     * @return String to represent the date which the Event task object is at
     */
    public String getAt() {
        return at;
    }

    /**
     * Returns a formatted task description of the Event task object,
     * eg. "Attend Lessons (at: Dec 18 2021, Mon, 1800h)".
     *
     * @return a formatted task description of the Event task object as a String
     */
    public String getFormattedDescription() {
        String formattedDateAndTime = getFormattedDateAndTime();
        return String.format("%s (at: %s)", this.taskDescription, formattedDateAndTime);
    }

    /**
     * Returns a formatted task description of the Event task object as a String to be
     * saved in the storage file, eg. "Event@X@Finish Homework@ 21-9-2021 1800h".
     *
     * @return a formatted task description of the Event task object as String to be saved in the storage file
     */
    public String getFormattedFileDescription() {
        return String.format("Event@%s@%s@%s",getStatusIcon(), taskDescription, this.at);
    }

    /**
     * Returns a formatted date and time String of the Event task object,
     * eg. "Dec 18 2021, Mon, 1800h"
     * @return a formatted date and time String of the Event task object
     */
    public String getFormattedDateAndTime() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, E");
        String formattedDateAndTime = this.date.format(dateFormatter);
        if (this.time != null) {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
            formattedDateAndTime += String.format(", " + this.time.format(timeFormatter) + "h");
        }
        return formattedDateAndTime;
    }
}
