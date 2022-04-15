package main.java.duke.task;

/** Represents the parent task of Deadline, Event and ToDo. */
public class Task {

    public String description;
    protected boolean isDone;
    protected String HORIZONTAL_LINE = "\n____________________________________________________________\n";

    /**
     * Constructs a{@code Task} with details such as the description of
     * the task and the status of whether it is marked as done.
     *
     * @param description Description of the task found in the task list.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Finds the status icon of a specified task, ie whether it's marked with "X" or not.
     *
     * @return The status icon "X" or " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /** Sets the status icon of a task to "X". */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Checks if a task is marked as done.
     *
     * @return True if task is marked as done, false if it is not.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Checks the task type of the specified task and prints it to indicate the type of task.
     *
     * @return The task type "T", "D" or "E" of the task in its subclasses.
     */
    public String getTaskType() {
        return " ";
    }

    /**
     * Prints the details of the tasks in the task list.
     *
     * @return The full details of the task with the task type, status icon and description of task.
     */
    public String printTask() {
        return "." + "[" + getTaskType() + "]" + "["
                + getStatusIcon() + "] " + description;
    }

    /**
     * Prints the acknowledgment after adding a task to the task list.
     *
     * @return The acknowledgement of adding a task to Duke.
     */
    public String printOk() {
        return HORIZONTAL_LINE + " added: " + description + HORIZONTAL_LINE;
    }

    /**
     * Prints the header of the acknowledgement message from Duke after adding a task.
     *
     * @return The starting of the message of the acknowledgment message.
     */
    public String printOkStart() {
        return HORIZONTAL_LINE + " Got it. I've added this task:\n";
    }

    /**
     * Prints the footer of the acknowledgement message from Duke after adding a task.
     *
     * @return The ending part of the message of the acknowledgment message.
     */
    public String printOkEnd() {
        return " Now you have ";
    }

    /**
     * Prints the acknowledgment after marking a task as done.
     *
     * @return The acknowledgement of marking the specified task as done.
     */
    public String printDone() {
        return HORIZONTAL_LINE + " Nice! I've marked this task as done:\n   [" + getTaskType() + "]["
                + getStatusIcon() + "] " + description + HORIZONTAL_LINE;
    }

    /**
     * Prints the acknowledgment after removing a task from the task list.
     *
     * @return The acknowledgement of removing a task from Duke.
     */
    public String printDelete() {
        return HORIZONTAL_LINE + " Noted. I've removed this task:\n   [" + getTaskType() + "]["
                + getStatusIcon() + "] " + description;
    }

    /**
     * Reformats the code to match the format which is to be
     * added onto the text file which saves the tasks.
     *
     * @return The details of the task which needs to be saved onto the text file.
     */
    public String getFileContent() {
        return " [" + getStatusIcon() + "] " + description;
    }

}
