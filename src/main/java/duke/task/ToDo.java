package duke.task;

public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
        this.taskType = "T";
    }

    /**
     * Returns the icon and description of to-do  task with the appropriate formatting.
     *
     * @return Icon and description of task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

