package karen.tasklist.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDate date;
    private LocalTime time;
    private String by;


    public Deadline(String taskDescription, String by, LocalDate date, LocalTime time) {
        super(taskDescription);
        this.by = by;
        this.date = date;
        this.time = time;
    }

    /**
     * Returns "Deadline" as the task type.
     *
     * @return String to represent task type of Deadline object
     */
    public String getType() {
        return "Deadline";
    }

    /**
     * Returns completion date of the task.
     *
     * @return task date as a LocalDate
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns time of the task to finish by.
     *
     * @return task time as a LocalTime
     */
    public LocalTime getTime() {
        return this.time;
    }

    /**
     * Returns the date and time which the Deadline task object is set to be completed by, as a String.
     *
     * @return String to represent the date and time which the Deadline task object is to be completed by
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Returns a formatted task description of the Deadline task object,
     * eg. "Finish Homework (by: Dec 18 2021, Mon, 1800h)".
     *
     * @return a formatted task description of the Deadline task object as a String
     */
    public String getFormattedDescription() {
        String formattedDateAndTime = getFormattedDateAndTime();
        return String.format("%s (by: %s)", this.taskDescription, formattedDateAndTime);
    }

    /**
     * Returns a formatted date and time String of the Deadline task object,
     * eg. "Dec 18 2021, Mon, 1800h"
     * @return a formatted date and time String of the Deadline task object
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

    /**
     * Returns a formatted task description of the Deadline task object as a String to be
     * saved in the storage file, eg. "Deadline@ @Finish Homework@ 21-9-2021 1830".
     *
     * @return a formatted task description of the Deadline task object as String to be saved in the storage file
     */
    public String getFormattedFileDescription() {
        return String.format("Deadline@%s@%s@%s",getStatusIcon(), this.taskDescription, this.by);
    }
}
