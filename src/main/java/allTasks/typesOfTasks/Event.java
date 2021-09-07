package allTasks.typesOfTasks;

import allTasks.Task;

public class Event extends Task {

    protected String by;
    protected String taskType = "[E]";

    /**
     * Creates new Event task
     * @param description name of event
     * @param by time at which event will happen
     */
    public Event(String description, String by){
        super(description);
        this.by = by;
    }

    /**
     * Gets the event time
     * @return event time
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Gets the type of task unique to this class
     * @return ["E"] for event
     */
    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String printTask() {
        super.printTask();
        return this.getTaskType() + this.getStatusIcon() + " " + this.getDescription()
                + "(at: " + this.getBy() + ")";
    }
}