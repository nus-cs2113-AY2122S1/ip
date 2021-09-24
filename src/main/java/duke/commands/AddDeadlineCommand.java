package duke.commands;

import duke.task.Deadline;
import duke.datasaver.DataManager;
import duke.task.TaskList;

public class AddDeadlineCommand extends Command {

    private final Deadline toAdd;

    public AddDeadlineCommand(Deadline toAdd) {
        this.toAdd = toAdd;
    }

    /**
     * Executes the addition of a {@code Deadline} to {@code taskList} by passing the user input to it.
     * An error message is printed if {@code userInput} lacks the deadline description or the deadline.
     * An error message is also printed if the date and time entered do not follow the format dd-MM-yyyy HH:mm or
     * if an invalid date and time is entered.
     *
     * @param taskList {@code TaskList} where the deadline is to be added to
     * @param dataManager {@code DataManager} which saves the new deadline to Duke's storage
     */
    @Override
    public void execute(TaskList taskList, DataManager dataManager) {
        taskList.addTask(toAdd);
        dataManager.saveData(taskList);
    }
}
