package shikabot.command;

import shikabot.ui.TextUi;

public class ListCommand extends Command {

    public ListCommand() {
    }

    /**
     * Function that prints the list of all tasks.
     */
    public void execute() {
        TextUi.printTasks(taskList);
    }
}
