package kate.task;

public class ToDo extends Task {
    private static final String TODO_CHECKBOX = "[T]";

    public ToDo(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the todo information to be written to a file
     *
     * @return Formatted String of Todo information
     */
    public String getTaskInfoForFile() {
        return TODO_CHECKBOX + " | " + isDone + " | " + description;
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
