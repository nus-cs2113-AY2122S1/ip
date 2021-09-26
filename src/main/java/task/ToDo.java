package task;

/**
 * Represents a single ToDo task
 * Each ToDo contains a description of the todo and a status indicating whether
 * it has been marked as done
 */
public class ToDo extends Task {
    /**
     * Sole constructor for ToDo object
     * @param desc Represents name of the todo
     * @param status Represents whether the todo is marked as complete
     */
    public ToDo(String description, Boolean isDone) {
        super(description, isDone);
    }

    /**
     * Gets the complete string representation of an individual ToDo task, meant for user output.
     * This method overrides from the parent class
     * @return String ToDo represented by string including type, status and description
     */
    @Override
    public String toString() {
        return "[t] [" + getStatus() + "] " + getDescription();
    }
}
