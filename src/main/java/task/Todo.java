package task;

public class Todo extends Task {

    /**
     * Creates a new task.Task object to be stored in Duke's list of Tasks,
     * sets the name of the task as the name passed in by the user and
     * marks the task as incomplete
     *
     * @param description the name of the task to be created
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
