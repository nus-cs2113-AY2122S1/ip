package Commands;

import Duke.TaskList;
import Tasks.Event;

/**
 * Adds an event task to the task list
 */
public class EventCommand extends Command {

    public String description;
    public String eventTime;
    public static final String SUCCESS_MESSAGE = "The following deadline task has been added\n";

    public EventCommand(String input){
        String[] inputParts = input.split("/at");
        description = inputParts[0].trim();
        eventTime = inputParts[1].trim();
    }

    @Override
    public String execute(TaskList taskList){
        taskList.addTask(new Event(description, false, eventTime));
        return SUCCESS_MESSAGE + taskList.printNewestTask() + "\n";
    }
}
