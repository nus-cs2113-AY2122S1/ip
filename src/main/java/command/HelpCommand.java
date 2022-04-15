package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Prints all the valid commands and its format
 */
public class HelpCommand extends Command {
    public static final String COMMAND_KEYWORD = "help";
    private static final String HELP_MESSAGE =
            "Below are the list of valid commands:\n" +
            "todo <task_description>\n" +
            "event <task_description> | <date(d/m/yyyy)> <time(HHmm)>\n" +
            "deadline <task_description> | <date(d/m/yyyy)> <time(HHmm)>\n" +
            "done <task_id>\n" +
            "undo <task_id>\n" +
            "list\n" +
            "delete <task_id>\n" +
            "clear\n" +
            "bye\n" +
            "help";

    /**
     * Execute HelpCommand by prints=ing all the valid commands and its format.
     *
     * @param tasks The task list which stores the task
     * @param ui In charge of informing the user that the command has been executed
     * @param storage In charge of reading and writing to data file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(HELP_MESSAGE);
    }
}
