package duke.task;

/**
 * Todo class used to add todos as a type of task.
 * @author Mohamed Irfan
 */
public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String formatSaveToFile() {
        return "T" + super.formatSaveToFile();
    }
}
