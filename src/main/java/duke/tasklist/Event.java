package duke.tasklist;

import duke.Time;

public class Event extends Task {

    public static final int INDEX_OF_EVENT = 5;
    protected String deadline;

    /**
     * Constructor that takes in only the description of the Event to be added to to do list and removes the
     * task type as well as other white spaces.
     *
     * @param description Event to be added to to do list
     */
    public Event(String description) {
        super(description.substring(description.indexOf("event") + INDEX_OF_EVENT, description.indexOf("/")).trim());
        deadline = description.substring(description.indexOf("/")).replace("/at", "").trim();
    }

    /**
     * Returns the task type "E" to represent task as an Event
     *
     * @return task type as "E"
     */
    @Override
    public String getTaskType() {
        return ("E");
    }

    /**
     * Returns the time of the Event specified by the user
     *
     * @return time of an Event
     */
    @Override
    public String getDeadline() {
        return (deadline);
    }

    /**
     * Converts the date from user input from "dd MM yyyy hh:mm" format to "dd MMM yyyy hh:mm a"
     *
     * @return time of the event in a specified format of "dd MMM yyyy hh:mm a"
     */
    public String convertDeadline() {
        return Time.convertDate(deadline);
    }

    /**
     * Prints a message to allow the user to know that an Event has been successfully marked as done
     *
     * @param taskNumber index of which the Event has been marked as done in the task list
     */
    @Override
    public void printMarkAsDoneMessage(int taskNumber) {
        System.out.println("Nice! I've marked this task as done:\n"
            + (taskNumber + 1) + ".[" + getTaskType() + "]"
            + "[" + getStatusIcon() + "] " + description + " (at:" + convertDeadline() + ")");
    }

    /**
     * Prints each Event in the task list
     *
     * @param listIndex position of the Event that is being printed in the task list.
     */
    @Override
    public void printIndividualTask(int listIndex) {
        System.out.println(listIndex + ".[" + getTaskType() + "]"
            + "[" + getStatusIcon() + "] " + description + " (at:" + convertDeadline() + ")");
    }
}
