package duke.Type;

import duke.date.DateValidator;
import duke.date.DateValidatorUsingDateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Task object, represents a generic task with descriptions and when to complete it by
 */
public abstract class Task implements Serializable {
    protected final String DEFAULT_DATE = "1999-11-30";
    protected String description;
    protected boolean isDone = false;
    protected LocalDate timeOfTask = LocalDate.parse(DEFAULT_DATE);
    protected boolean hasValidDate = false;

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Sets a valid time of a task, only when the following format is followed: YYYY-MM-DD
     *  Note that this only returns one valid date, on a first-come-first-serve basis
     *  e.g  for tasks with a end date (events that last a few days)
     * @param fullUserInput full sentence input by user
     */
    public void setTime(String fullUserInput) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        DateValidator validator = new DateValidatorUsingDateFormat(dateFormatter);
        List<String> chunksOfText = Arrays.asList(fullUserInput.split(" "));
        List<String> suitableDate = chunksOfText.stream()
                .filter(validator::isValid)
                .collect(Collectors.toList())
                ;
        if (!suitableDate.isEmpty()) {
            this.timeOfTask = LocalDate.parse(suitableDate.get(0)); //1st date by index
            System.out.println("great! i've recorded a date with the task: " + this.getDescription()
                                + " " + "with the date: "
                                + timeOfTask.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        this.hasValidDate = true;
        }
    }

    public LocalDate getTaskDate() {
        return timeOfTask;
    }
}
