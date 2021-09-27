package duke.task;

public class Todo extends Task {

    public Todo(String description, char taskType) {
        super(description, taskType);
    }

    @Override
    public String toString() {
        return " [" + getTaskType() + "][" + getStatusIcon() + "] " + super.toString();
    }

    @Override
    public String toFileFormat() {
        String isDoneString = isDone ? "1" : "0";
        return getTaskType() + " | " + isDoneString + " | " + super.toFileFormat();
    }
}
