package duke.tasklist.task;

public class Todo extends Task {

    /**
     * @param description The description of the task given by the user
     */
    public Todo(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
