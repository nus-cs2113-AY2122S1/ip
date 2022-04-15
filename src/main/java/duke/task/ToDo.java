package duke.task;

/**
 * The ToDo class is a subclass of the Task class.
 * It is a specific task with only a description (<code>description</code>) given as input.
 */
public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String listTask() {
        return "[T]" + super.listTask();
    }

    @Override
    public String getIcon() {
        return "T";
    }
}
