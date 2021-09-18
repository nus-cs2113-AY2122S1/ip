package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represent a task with a time limit, e.g. deadline
 */
public abstract class TimedTask extends Task{

    LocalDateTime datetime;

    /**
     * Constructs a TimedTask object.
     * Parses the datetime string into a LocalDateTime instance variable
     * @param name The name/description of the task
     * @param datetime The time limit
     * @throws IllegalArgumentException
     */
    public TimedTask(String name, String datetime) throws IllegalArgumentException{
        super(name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            this.datetime = LocalDateTime.parse(datetime, formatter);
        } catch(DateTimeParseException e) {
            String errorMessage = String.format("%s. Please input date-time in this format (yyyy-MM-dd HH:mm)", datetime);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    /**
     * Returns the due date of the task.
     * @return The due date
     */
    public LocalDateTime getTime() {
        return datetime;
    }

    /**
     * Prints the task in file-saving format
     * @return The raw string of task that can be saved in the task file
     */
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String deadlineString = datetime.format(formatter);
        return super.toFileString() + String.format(";%s", deadlineString);
    }

    /**
     * Prints the TimedTask as a String
     * @return The task in human-friendly format
     */
    public String toString() {
        return super.toString();
    }
}
