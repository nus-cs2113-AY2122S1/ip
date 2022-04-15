package main.java.duke.task;

/** Represents a {@code Task} which is of type ToDo. */
public class ToDo extends Task {

    /**
     * Constructs a {@code ToDo} task with the basic information of its description.
     *
     * @param description Description of the task found in the task list.
     */
    public ToDo(String description) {
        super(description);
    }

    public String getTaskType() {
        return "T";
    }

    public String printOk() {
        return super.printOkStart() + "  [T][" + getStatusIcon() + "] "
                + description + "\n" + super.printOkEnd();
    }

    public String printDone() {
        return HORIZONTAL_LINE + " Nice! I've marked this task as done:\n   [" + getTaskType() + "]["
                + getStatusIcon() + "] " + description + HORIZONTAL_LINE;
    }

    public String getFileContent() {
        return "T [" + getStatusIcon() + "] " + description;
    }

}
