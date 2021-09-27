package Tasks;

/**
 * A todo task
 */
public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return super.toString() + " [T][" + super.getStatusIcon() + "] " + description + "\n";
    }

    @Override
    public String getDescription() {
        return description + "\n";
    }

}