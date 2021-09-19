package duke.tasklist;

import duke.Time;

public class Deadline extends Task {

    public static final int INDEX_OF_DEADLINE = 8;
    protected String deadline;

    /**
     * Constructor that takes in only the description of the Deadline to be added to to do list and removes the
     * task type as well as other white spaces.
     *
     * @param description Deadline to be added to to do list
     */
    public Deadline(String description) {
        super(description.substring(description.indexOf("deadline") + INDEX_OF_DEADLINE, description.indexOf("/")).trim());
        deadline = description.substring(description.indexOf("/")).replace("/by", "").trim();
    }

    /**
     * Returns the task type "D" to represent task as a Deadline
     *
     * @return task type as "D"
     */
    @Override
    public String getTaskType() {
        return ("D");
    }

    /**
     * Returns the date and time of the Deadline specified by the user
     *
     * @return date and time of the Deadline
     */
    @Override
    public String getDeadline() {
        return (deadline);
    }

    /**
     * Converts the date from user input from "dd MM yyyy hh:mm" format to "dd MMM yyyy hh:mm a"
     *
     * @return time of the Deadline in a specified format of "dd MMM yyyy hh:mm a"
     */
    public String convertDeadline() {
        return Time.convertDate(deadline);
    }

    /**
     * Prints a message to allow the user to know that a Deadline has been successfully marked as done
     *
     * @param taskNumber index of which the Deadline has been marked as done in the task list
     */
    @Override
    public void printMarkAsDoneMessage(int taskNumber) {
        System.out.println("Nice! I've marked this task as done:\n" + (taskNumber + 1) + ".[" + getTaskType() + "]" + "[" + getStatusIcon() + "] " + description + " (by:" + convertDeadline() + ")");
    }

    /**
     * Prints each Deadline in the task list
     *
     * @param listIndex position of the Deadline that is being printed in the task list.
     */
    @Override
    public void printTaskList(int listIndex) {
        System.out.println(listIndex + ".[" + getTaskType() + "]" + "[" + getStatusIcon() + "] " + description + " (by:" + convertDeadline() + ")");
    }
}
