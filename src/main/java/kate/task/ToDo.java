package kate.task;

public class ToDo extends Task {
    private static final String TODO_CHECKBOX = "[T]";

    public ToDo(String description) {
        this.description = description;
        this.isDone = false;
    }

    public ToDo(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Retrieves the todo information to be written to a file
     *
     * @return Formatted String of Todo information
     */
    public String getTaskInfoForFile() {
        return "T" + " | " + isDone + " | " + description;
    }

    /**
     * Retrieves Task information and additional todo information
     *
     * @return String description of task and additional todo information
     */
    @Override
    public String getTaskInfo() {
        return TODO_CHECKBOX + super.getTaskInfo();
    }
}
