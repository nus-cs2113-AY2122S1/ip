package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Display all Tasks in ArrayList tasks
 */
public class HelpCommand extends Command {
    public HelpCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String output = ""
                + " Command   |   Usage:\n"
                + " list      |   list\n"
                + " todo      |   todo <TASK_DESCRIPTION>\n"
                + " deadline  |   deadline <TASK_DESCRIPTION> /by <yyyy-MM-dd HH:mm>\n"
                + " event     |   event <TASK_DESCRIPTION> /at <yyyy-MM-dd HH:mm>\n"
                + " done      |   done <TASK_NUMBER>\n"
                + " delete    |   delete <TASK_NUMBER>\n"
                + " schedule  |   schedule <yyyy-MM-dd>\n"
                + " find      |   find <KEYWORD>\n"
                + " help      |   help\n"
                + " purge     |   purge\n"
                + " bye       |   bye\n\n"
                + " Detailed User Guide at https://remusteo.github.io/ip/\n";
        ui.printOutput(output);
    }
}
