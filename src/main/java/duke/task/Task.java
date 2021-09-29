package duke.task;

public class Task {

    /* Description of task */
    protected String description;

    /* Status of task. Is completed when is true */
    protected boolean isDone;

    /* Type of task: T, D or E */
    protected char type;

    /**
     * Constructor for any type of task
     *
     * @param description Description of the task processed by TaskManager
     * @param type        Type of task processed by TaskManager
     */
    public Task(String description, char type) {
        setDescription(description);
        this.isDone = false;
        this.type = type;
    }

    /* Getter for boolean if task is done */
    public int getIsDone() {
        return isDone ? 1 : 0;
    }

    /**
     * Set isDone based on integer given. True = 1, False = 0
     *
     * @param booleanValue either 1 or 0 according to what was saved in data.txt
     */
    public void setIsDone(boolean booleanValue) {
        this.isDone = booleanValue;
    }

    /* Getter for task type */
    public char getType() {
        return type;
    }

    /* Getter for task description */
    public String getDescription() {
        return description;
    }

    /* Setter for task name */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Return the icon of status to show if task is completed.
     *
     * @return Icon of status
     */
    public char getStatusIcon() {
        return (isDone ? 'X' : ' ');
    }

    /**
     * Set status of task to be completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Formats description of task to be displayed to user
     *
     * @return Formatted string of a task
     */
    @Override
    public String toString() {
        return String.format("[%c][%c] %s", this.getType(), this.getStatusIcon(), this.getDescription());
    }

    /**
     * Formats description of task to be saved as txt
     *
     * @return Formatted string of a task
     */
    public String toSave() {
        return String.format("%s,%d,%s", this.getType(), this.getIsDone(), this.getDescription());
    }


}
