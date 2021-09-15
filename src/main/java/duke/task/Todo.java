package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String getTaskDetailsInFileFormat() {
        return "T | " + isDone + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
