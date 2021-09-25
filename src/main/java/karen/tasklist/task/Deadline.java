package karen.tasklist.task;

public class Deadline extends Task{
    private String by;

    public Deadline(String taskDescription, String by) {
        super(taskDescription);
        this.by = by;
    }

    /**
     * Returns "Deadline" as the task type.
     *
     * @return String to represent task type of Deadline object
     */
    public String getType() {
        return "Deadline";
    }

    /**
     * Returns the date which the Deadline task object is to be completed by as a String.
     *
     * @return String to represent the date which the Deadline task object is to be completed by
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns a formatted task description of the Deadline task object, eg. "Finish Homework (by: 21-09-2021)".
     *
     * @return a formatted task description of the Deadline task object as a String
     */
    public String getFormattedDescription() {
        return String.format("%s (by: %s)", this.taskDescription, this.by);
    }

    /**
     * Returns a formatted task description of the Deadline task object as a String to be
     * saved in the storage file, eg. "Deadline, ,Finish Homework, 21-9-2021".
     *
     * @return a formatted task description of the Deadline task object as String to be saved in the storage file
     */
    public String getFormattedFileDescription() {
        return String.format("Deadline,%s,%s,%s",getStatusIcon(), this.taskDescription, this.by);
    }

}
