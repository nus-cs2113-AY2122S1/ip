package allTasks.typesOfTasks;

import allTasks.Task;

public class Deadline extends Task {

    protected String by;
    protected String taskType = "[D]";

    /**
     * Creates new deadline task
     * @param description name of task
     * @param by due date for task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets the due date of task
     * @return due date
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Gets the type of task unique to this class
     * @return ["D"] for deadline
     */
    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String printTask() {
        super.printTask();
        return getTaskType() + getStatusIcon() + " " + getDescription()
                + "(by: " + getBy() + ")";
    }

    @Override
    public String printForSave() {
        return getTaskType() + "," + getStatusIcon() + "," + getDescription() + "," + getBy();
    }
}