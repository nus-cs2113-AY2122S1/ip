package allTasks.typesOfTasks;

import allTasks.Task;

public class Todo extends Task {

    protected String taskType = "[T]";

    /**
     * Creates new todo task
     * @param description name of task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Gets the type of task unique to this class
     * @return ["T"] for todo
     */
    @Override
    public String getTaskType() {
        return taskType;
    }

    /**
     * Prints the full task
     * @return taskType + done status + task name
     */
    @Override
    public String printTask () {
        return getTaskType() + getStatusIcon() + " " + getDescription();
    }

    @Override
    public String printForSave() {
        return getTaskType() + "," + getStatusIcon() + "," + getDescription();
    }
}