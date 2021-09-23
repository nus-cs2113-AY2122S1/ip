package duke.task;

import static duke.message.Messages.LOAD_DELIMITER;

/**
 * Todo task that has a description of task
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Return the char that represents the todo task
     * @return char for identifying todo
     */
    public String getCode() {
        return "T";
    }

    /**
     * To print todo task in a certain format
     * @return String that shows the information and status of todo task
     */
    @Override
    public String toString() {
        return "[T]" +
                super.getStatusIcon() +
                " " +
                super.getDescription();
    }

    /**
     * Get the information and status of todo task in a certain format for saving
     * @return String that is formatted and ready to save todo
     */
    public String toSave() {
        return this.getCode() +
                LOAD_DELIMITER +
                super.getDoneValue() +
                LOAD_DELIMITER +
                super.getDescription();
    }
}
