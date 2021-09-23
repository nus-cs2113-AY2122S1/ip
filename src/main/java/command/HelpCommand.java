package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class HelpCommand extends Command {
    public static final String COMMAND_KEYWORD = "help";
    private static final String HELP_MESSAGE =
            "Below are the list of valid commands:\n" +
            "todo <task_description>\n" +
            "event <task_description> | <date_and_time_information>\n" +
            "deadline <task_description> | <date_and_time_information>\n" +
            "done <task_id>\n" +
            "undo <task_id>\n" +
            "list\n" +
            "delete <task_id>\n" +
            "clear\n" +
            "bye\n" +
            "help";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(HELP_MESSAGE);
    }
}
