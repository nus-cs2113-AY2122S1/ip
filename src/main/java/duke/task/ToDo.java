package duke.task;

public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getStorageString() {
        String c = isCompleted ? "1" : "0";
        return "T | " + c + " | " + taskName;
    }
}
