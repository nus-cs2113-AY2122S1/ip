package task;

import java.time.LocalDateTime;

public class Todo extends Task {
    /**
     * Constructs a new todo task.
     * @param description Todo task description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts todo task object into a String, which is used to store it in the file.
     * @return String consisting of all the information related to the task
     */
    public String toFileFormat() {
        return "T # " + (isDone ? "1" : "0") + " # " + description + "\n";
    }

    /**
     * Converts todo task object into a String, which is used to display in the console.
     * @return String consisting of all the information related to the task
     */
    @Override
    public String toString() {
        return "[T] [" + getStatus() + "] " + getDescription();
    }

    /**
     * An abstract function used to return date and time details.
     * Since, todo task does not contain date and time details,
     * it returns null.
     * @return Null
     */
    @Override
    public LocalDateTime getDT() {
        return null;
    }
}