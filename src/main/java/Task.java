import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class to contain TASKs type.
 */
public class Task {
    protected String description;
    protected String taskType;
    protected boolean isDone;
    protected LocalDate eventDate;
    public static final String DATE_FORMAT = "MMM dd yyyy";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "";
    }

    /**
     * Gets the status of the task object and returns the corresponding icon.
     *
     * @return "X" if the task is marked as done, and blank otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the type of task.
     *
     * @return the type of the task if it is a todo, deadline or event.
     */
    public String getType() {
        return taskType;
    }

    /**
     * Gets the formatted date of when the task is due/occurring.
     *
     * @return the due/occurring date of the task in the DATE_FORMAT set in this class.
     */
    public String getFormattedWhen() {
        return eventDate.format(DateTimeFormatter.ofPattern((Task.DATE_FORMAT)));
    }

    /**
     * Gets the unformatted date of when the task is due/occurring.
     *
     * @return the unformatted due/occurring date of the task.
     */
    public String getUnformattedWhen() {
        return eventDate.toString();
    }

    /**
     * Gets the description of the task.
     *
     * @return description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Overrides the toString() method, returning the
     * type of class, the status of the object (done/not done) and the object's description.
     *
     * @return the type of task, the status of the object, and the object's description.
     */
    @Override
    public String toString() {
        return ("[" + taskType + "]" + "[" + getStatusIcon() + "] " + description);
    }
}
