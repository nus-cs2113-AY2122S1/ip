package duke.task;

public class ToDo extends Task {

    public static final String TODO_ICON = "[T]";

    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Exports the task as a string in the standard format to be stored in the save file.
     *
     * @return The formatted string with the task data.
     */
    @Override
    public String exportTask() {
        return "T|" + super.getStatus() + "|" + super.toString() + "|" + System.lineSeparator();
    }

    @Override
    public String toString() {
        return TODO_ICON + super.toString();
    }
}
