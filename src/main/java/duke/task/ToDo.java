package duke.task;

/**
 * ToDo object contains description of the ToDo
 */
public class ToDo extends Task {
    protected String description;

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSave() {
        return "T |" + super.toSave();
    }
}
