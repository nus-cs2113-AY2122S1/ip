package duke.command;

import duke.DukeException;
import duke.Output;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand to search for tasks that contain the given keyword
     *
     * @param keyword the keyword to be searched for in TaskList
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Searches for Tasks in taskList that contain the keyword and lists them in the terminal
     *
     * @param taskList the taskList that will be searched through and read
     * @param storage  not used in this subclass implementation
     * @param ui       the ui class instance that will print out the completion message
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        String outputMessage = taskList.getSearchedTasksMessage(keyword);
        ui.printOutput(outputMessage);
    }
}
