package duke.tasklist;

public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor that takes in the description of the task to be added to to do list
     *
     * @param description task to be added to to do list
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Update status of task if user chooses to mark task as done */
    public void updateIsDone() {
        this.isDone = true;
    }

    /**
     * If status of task is done, then the function marks done task with an 'X'
     *
     * @return 'X' if task is done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the task type if it exists
     *
     * @return task type
     */
    public String getTaskType() {
        return ("");
    }

    /**
     * Returns a date/time specified by the user if it exists
     *
     * @return date/time of an event/deadline
     */
    public String getDeadline() {
        return ("");
    }

    /**
     * Returns the task description to be added to the task list
     *
     * @return description of task
     */
    public String getDescription(){
        return description;
    }

    /**
     * Prints a message to allow the user to know that a task has been successfully marked as done
     *
     * @param taskNumber index of which the task has been marked as done in the task list
     */
    public void printMarkAsDoneMessage(int taskNumber) {
        System.out.println("Nice! I've marked this task as done:\n" + (taskNumber + 1) + ".[" + getTaskType() + "]" + "[" + getStatusIcon() + "] " + description);
    }

    /**
     * Prints each task in the task list
     *
     * @param listIndex position of the task that is being printed in the task list.
     */
    public void printTaskList(int listIndex) {
        System.out.println(listIndex + ".[" + getTaskType() + "]" + "[" + getStatusIcon() + "] " + description);
    }

    /** Prints a message to allow the user to know that a task has been successfully added to the task list. */
    public void printTaskAddedMessage() {
        System.out.println("I can do that! I have added [" + description + "] to your task list!");
    }

    /**
     * Prints a message to allow the user to know that the task specified by the user has been successfully deleted from
     * the task list
     *
     * @param taskNumber index of the task which has been deleted from the task list
     */
    public void printTaskDeletedMessage(int taskNumber) {
        System.out.println("I got you! I've deleted this task:\n" + (taskNumber + 1) + ".[" + getTaskType() + "]" + "[" + getStatusIcon() + "] " + description);
    }

    /**
     * Overriding the toString() function as a class function
     *
     * @return description of the task to be added to the task list
     */
    public String toString() {
        return description;
    }
}
