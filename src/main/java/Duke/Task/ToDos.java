package Duke.Task;

/**
 * The subclass for the ToDos task
 */
public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    @Override
    public String getTaskIcon() {
        return "T";
    }
}
