package duke.task;

public class Todo extends Task {
    protected String type;

    public Todo(boolean isDone, String taskName) {
        super(taskName);
        this.isDone = isDone;
        this.type = "T";
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveFile(String DELIMITER) {
        return "T" + super.toSaveFile(DELIMITER);
    }

}
