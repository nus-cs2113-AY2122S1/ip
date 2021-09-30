package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.TextUi;

/**
 * Displays usage information about all available commands in the program.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows usage information for all commands in the program. "
            + "\n"
            + "Example: " + COMMAND_WORD
            + "\n";
    public static final String MESSAGE_HELP = "Help initiated! Look below for command information~";

    /**
     * Prints usage messages to the user for every command.
     *
     * @param tasks a task list that contains all the tasks
     * @param ui accesses format and messages to show to the user
     * @param storage accesses a text file which stores the task list
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        ui.showToUser(MESSAGE_HELP,
                ui.EMPTY_STRING,
                TodoCommand.MESSAGE_USAGE,
                DeadlineCommand.MESSAGE_USAGE,
                EventCommand.MESSAGE_USAGE,
                ListCommand.MESSAGE_USAGE,
                MarkCompleteCommand.MESSAGE_USAGE,
                DeleteCommand.MESSAGE_USAGE,
                ClearCommand.MESSAGE_USAGE,
                FindCommand.MESSAGE_USAGE,
                HelpCommand.MESSAGE_USAGE,
                ExitCommand.MESSAGE_USAGE);
    }
}
