package handleTask;

public class Todo extends Task {

    public Todo(String description) {
        super(description, 'T');
    }

    @Override
    public String toString() {
        return "[" + taskType + "][" + getStatusIcon() + "]"
                + description;
    }
}
