package duke.task;

/**
 * Shows the instances and methods of a todo task.
 */
public class ToDo extends Task{

    public ToDo(String todoName) {
        super(todoName);
        this.taskType = "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
