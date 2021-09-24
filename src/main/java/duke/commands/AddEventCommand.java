package duke.commands;

import duke.task.Event;
import duke.datasaver.DataManager;
import duke.task.TaskList;

public class AddEventCommand extends Command {

    private final Event toAdd;

    public AddEventCommand(Event toAdd) {
        this.toAdd = toAdd;
    }

    /**
     * Executes the addition of an {@code Event} to the {@code taskList} by passing the user input to it.
     * An error message is printed if {@code userInput} lacks the event description or the event time.
     * An error message is also printed if the date and time entered do not follow the format dd-MM-yyyy HH:mm or
     * if an invalid date and time is entered.
     *
     * @param taskList {@code TaskList} where the event is to be added to
     * @param dataManager {@code DataManager} which saves the new event to Duke's storage
     */
    @Override
    public void execute(TaskList taskList, DataManager dataManager) {
        taskList.addTask(toAdd);
        dataManager.saveData(taskList.getTaskList());
    }
}
