package duke.task;

import duke.exception.DukeException;
import duke.ui.Message;

public abstract class Task {
    protected static final String TODO = "T";
    protected static final String DEADLINE = "D";
    protected static final String EVENT = "E";

    protected String description;
    protected boolean isDone;

    /**
     * Initializes task description and sets the initial
     * status to "not done".
     *
     * @param description task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Initializes task description and sets the initial
     * status according to the given parameter.
     *
     * @param description task description
     * @param isDone initial status
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    /**
     * Serializes task data.
     *
     * @return serialied task data
     */
    public String serialize() {
        return description;
    }

    /**
     * Deserializes a line of string and match it to
     * the suitable task format.
     *
     * @param line a line of string
     * @return the new task after deserialization
     * @throws DukeException if string is in invalid format
     */
    public static Task deserialize(String line) throws DukeException {
        try {
            String[] params = line.split("\\s*[|]\\s*");
            String taskType = params[0];
            String description = params[2];
            boolean isDone;

            switch(params[1]) {
            case "0":
                isDone = false;
                break;
            case "1":
                isDone = true;
                break;
            default:
                throw new DukeException(Message.ERROR_DESERIALIZING_DATA);
            }

            switch (taskType) {
            case TODO:
                return new Todo(description, isDone);
            case DEADLINE:
                String by = params[3];
                return new Deadline(description, by, isDone);
            case EVENT:
                String at = params[3];
                return new Event(description, at, isDone);
            default:
                throw new DukeException(Message.ERROR_DESERIALIZING_DATA);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(Message.ERROR_DESERIALIZING_DATA);
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
