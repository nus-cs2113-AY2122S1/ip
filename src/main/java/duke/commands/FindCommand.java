package duke.commands;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {

    String keyword;

    /**
     * Command when user wants to add a Task (e.g. Todo, Deadline, Event).
     * When executed, it will find Tasks from the TaskList, and return a successful find message with the results.
     *
     * @param keyword Keyword to be used to find matching Tasks.
     */

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList taskList, Ui ui) {
        ui.findTask(keyword, taskList);
    }

    public boolean isExit() {
        return false;
    }
}
