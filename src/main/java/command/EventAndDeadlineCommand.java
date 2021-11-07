package command;

import storage.Storage;
import task.Event;
import task.Deadline;
import task.TaskList;

/**
 * Represents event and deadline commands given by user
 * as task to be completed by a certain date (deadline),
 * or a task to be completed at some day (event).
 */
public class EventAndDeadlineCommand extends Command {
    private String type;
    private String description;
    private String timeOfEvent;

    public EventAndDeadlineCommand(String type, String description, String timeOfEvent) {
        this.type = type;
        this.description = description;
        this.timeOfEvent = timeOfEvent;
    }

    /**
     * Adds event or deadline task to the list.
     *
     * @param tasksList List of tasks
     * @param storage Storage object to save the data
     */
    @Override
    public void execute(TaskList tasksList, Storage storage){
        if (type.equals("event")) {
            Event event = new Event(description, timeOfEvent);
            tasksList.addToList(event);
        } else {
            Deadline deadline = new Deadline(description, timeOfEvent);
            tasksList.addToList(deadline);
        }
    }

    /**
     * Returns a boolean to check whether to exit application or not.
     *
     * @return false To continue taking user commands
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

