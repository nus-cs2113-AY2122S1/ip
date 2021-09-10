package kate.task;

public class ToDo extends Task {
    private static final String TODO_CHECKBOX = "[T]";

    public ToDo(String description) {
        super(description);
    }

    public String getTaskInfoForFile() {
        return TODO_CHECKBOX + " | " + isDone + " | " + description;
    }

    @Override
    public String getTaskInfo() {
        return TODO_CHECKBOX + super.getTaskInfo();
    }
}
