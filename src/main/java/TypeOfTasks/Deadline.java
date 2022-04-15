package TypeOfTasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * A type of task with description, checkbox and a by deadline description.
 */
public class Deadline extends Task{
    protected String tag = "D";
    protected String by;
    protected LocalDate localdate;
    
    /**
     * Initialises Deadline object with its description and by description.
     *
     * @param description The deadline description.
     * @param by The description of when the task is due.
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.localdate = LocalDate.parse(by);
            this.by = localdate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException dateTimeParseException) {
            this.by = by;
        }
    }
    
    /**
     * Returns either a by or at description depending on task type
     * If the task is a Deadline returns the by description, if the task is Event returns the at description
     *
     * @return description for at/by in Event/Deadline respectively.
     */
    public String getInfo() {
        return by;
    }

    public LocalDate getLocaldate() {
        return localdate;
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
     * Prints all the details of the Deadline.
     *
     * @param task The specific task in the entire tasklist.
     * @param index The index of the task getting printed, Base 1.
     */
    public void printTaskDetails(Task task, int index) {
        System.out.println(index + ".[D][" + (task.getStatus()) + "] "+ task.getDescription() + "(by: " + by + ")");
    }
}
