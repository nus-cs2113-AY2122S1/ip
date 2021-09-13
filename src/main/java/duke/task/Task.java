package duke.task;

/**
 * The Task class represents a task with a description
 * and a status (whether it's done or not).
 *
 * @author richwill28
 */
public class Task {
    /** Description of the task */
    protected String description;

    /** Status of the task (whether it's done or not) */
    protected boolean isDone;

    /**
     * The constructor method. Initialize task description
     * and set the initial status to "not done".
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * The constructor method. Initialize task description
     * and set the initial status according to the given
     * parameter.
     *
     * @param description Task description.
     * @param isDone Initial status.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status of the task, whether it's done
     * or not.
     *
     * @return "X" if task is done, otherwise returns " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    /**
     * Serialize task data;
     *
     * @return Serialied task data.
     */
    public String serialize() {
        return description;
    }

    /**
     * Deserialize a line of string and match to suitable
     * task format.
     *
     * @param line A line of string.
     * @return The new task after deserialization.
     */
    public static Task deserialize(String line) throws IllegalArgumentException {
        try {
            String[] params = line.split("\\s*[|]\\s*");
            String taskType = params[0];
            boolean isDone;
            switch(params[1]) {
            case "0":
                isDone = false;
                break;
            case "1":
                isDone = true;
                break;
            default:
                throw new IllegalArgumentException("Error: Invalid format. Unable to deserialize string.");
            }
            String description = params[2];
            switch (taskType) {
            case "T":
                return new Todo(description, isDone);
            case "D":
                String by = params[3];
                return new Deadline(description, by, isDone);
            case "E":
                String at = params[3];
                return new Event(description, at, isDone);
            default:
                throw new IllegalArgumentException("Error: Invalid format. Unable to deserialize string.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Error: Invalid format. Unable to deserialize string.");
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
