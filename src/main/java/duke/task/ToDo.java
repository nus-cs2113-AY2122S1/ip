package duke.task;

/**
 * Class that represents a Todo Task.
 */
public class ToDo extends Task {

    final private static String FLAG_TYPE = "[T]";

    public ToDo(String description) {
        super(description);
    }

    /**
     * Return the status icon of todo task which is a "[T]".
     *
     * @return Status icon of todo task
     */
    @Override
    public String getStatusIcon() {
        return FLAG_TYPE + super.getStatusIcon();
    }

    @Override
    public String toString() {
        return FLAG_TYPE + " | " + getDoneStatus() + " | " + this.getDescription();
    }

}
