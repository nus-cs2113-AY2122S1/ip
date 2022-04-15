package TypeOfTasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A type of task with description, checkbox and an at timeframe.
 */
public class Event extends Task {
    protected String at;
    protected String tag = "E";
    protected LocalDate localdate;
    /**
     * Initialises Event object with its description and at description.
     * 
     * @param description The event description.
     * @param at The time frame when event is happening.
     */

    
    
    public Event(String description,String at) {
        super(description);
        this.at = at;
        try {
            this.localdate = LocalDate.parse(at);
            this.at = localdate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException dateTimeParseException) {
            this.at = at;
        }
    }

    /**
     * Returns either a by or at description depending on task type
     * If the task is a Deadline returns the by description, if the task is Event returns the at description
     * 
     * @return description for at/by in Event/Deadline respectively.
     */
    public String getInfo() {
        return at;
    }

    /**
     * Returns the task's tag to identify type of task
     * If the task is a Todo,Deadline,Event it returns T,D and E accordingly
     *
     * @return task's tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * Prints all the details of the event.
     * 
     * @param task The specific task in the entire tasklist.
     * @param index The index of the task getting printed, Base 1.
     */
    public void printTaskDetails(Task task, int index) {
        System.out.println(index + ".[E][" + task.getStatus() + "] "+ task.getDescription() + "(at: " + at + ")");
    }
}
