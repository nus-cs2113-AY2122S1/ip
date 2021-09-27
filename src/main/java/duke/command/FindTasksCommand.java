package duke.command;

import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

/**
 * Represents a command to find tasks using keywords
 */
public class FindTasksCommand extends Command {

    private final String keywords;

    /**
     * FindTaskCommand constructor.
     * @param keywords the keywords to be searched for
     */
    public FindTasksCommand(String keywords) {
        this.keywords = keywords;
    }

    /**
     * Print all the tasks found which contain the keywords.
     * @param tasks   the TaskList object that takes in the new deadline task
     * @param ui      the Ui object responsible for printing messages
     * @param storage the Storage object responsible for saving data in a local txt file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList relevantTasks = new TaskList(tasks.getRelevantTaskList(this.keywords));
        ui.list(relevantTasks.getTaskList());
    }
}