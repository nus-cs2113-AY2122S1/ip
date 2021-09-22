package duke.command;

import duke.task.TaskList;

public class HelpCommand extends Command {

    public HelpCommand() {
        super();
    }

    /**
     * Print help message.
     *
     * @param taskList Current task list.
     */
    @Override
    public void execute(TaskList taskList) {
        ui.printHelp();
    }
}
