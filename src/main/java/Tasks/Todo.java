package Tasks;

/**
 * Represents a todo task
 */
public class Todo extends Task{
    public Todo(String task, boolean isDone) {
        super(task, isDone, TaskTypes.TODO);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
