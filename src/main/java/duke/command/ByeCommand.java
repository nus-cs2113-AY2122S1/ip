package duke.command;

import duke.task.TaskList;

public class ByeCommand extends Command {

    public ByeCommand() {
        super();
    }

    /**
     * Prints bye message.
     *
     * @param taskList Current task list.
     */
    @Override
    public void execute(TaskList taskList) {
        ui.printBye();
    }
}
