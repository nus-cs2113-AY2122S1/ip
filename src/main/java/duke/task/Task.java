package duke.task;
import duke.exception.DukeException;

/**
 * Represents a task that the user has added into Duke. A <code>Task</code> object is defined by its description String.
 * It also has a char type which indicates if it is a Todo, Event or Deadline. The boolean isDone is true if the Task
 * is done. The static int totalTasks represents the total number of tasks in circulation.
 */
public abstract class Task {
    public static final String DATE_FORMAT = "MMM dd yyyy";
    protected String description;
    protected char type;
    protected boolean isDone = false;
    protected static int totalTasks = 0;

    public Task(String description) {
        this.description = description;
        totalTasks++;
    }

    /**
     * Returns the String interpretation of this Task.
     * @return String The String interpretation of this Task.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[" + type + "][X] " + description;
        } else {
            return "[" + type + "][ ] " + description;
        }
    }

    /**
     * Returns the String interpretation of this Task when it is saved.
     * @return String The interpretation of this Task when it is saved.
     */
    public abstract String saveString();

    /**
     * Marks this task complete.
     * @exception DukeException If the Task is already done.
     */
    public void markComplete() throws DukeException {
        if (isDone) {
            throw new DukeException(DukeException.TASK_ALREADY_DONE);
        } else {
            isDone = true;
        }
    }

    /**
     * Returns the total number of Tasks in circulation.
     * @return int The total number of Tasks in circulation.
     */
    public static int getTotalTasks() {
        return totalTasks;
    }

    /**
     * Returns the description of this Task.
     * @return String The description of this Task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Decrements the number of total Tasks by one.
     */
    public void decrementTaskNumber() {
        totalTasks--;
    }
}
