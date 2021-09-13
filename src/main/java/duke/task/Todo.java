package duke.task;

public class Todo extends Task {

    /**
     * Class Todo constructor.
     *
     * @param description Short description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Gets the description of the task.
     *
     * @return String containing task description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        String stringIsDone = isDone ? "1" : "0";
        return "T" + DELIMITER + stringIsDone + DELIMITER + description;
    }
}
