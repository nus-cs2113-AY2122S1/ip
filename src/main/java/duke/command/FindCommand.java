package duke.command;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {
    private static String MESSAGE_NO_MATCH = "There are no matches.";
    private static String MESSAGE_LIST_HEADER = "Here are the matching tasks:";

    public FindCommand(String argument) {
        super(argument);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> matchingTasks = tasks.findTask(argument);

        if (matchingTasks.isEmpty()) {
            ui.printMessage(MESSAGE_NO_MATCH);
        } else {
            StringBuilder stringBuilder = new StringBuilder(MESSAGE_LIST_HEADER);
            for (int i = 0; i < matchingTasks.size(); i += 1) {
                stringBuilder.append("\n");
                stringBuilder.append(i + 1);
                stringBuilder.append(": ");
                stringBuilder.append(matchingTasks.get(i));
            }
            ui.printMessage(stringBuilder.toString());
        }
    }
}
