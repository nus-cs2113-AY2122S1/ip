package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {
    private static String MESSAGE_NO_MATCH = "There are no matches.";
    private static String MESSAGE_LIST_HEADER = "Here are the matching tasks:";

    public FindCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String searchString = argument.toLowerCase();

        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.getSize(); i += 1) {
            Task task = tasks.getTaskAt(i);
            if (task.getDescription().toLowerCase().contains(searchString)) {
                matchingTasks.add(task);
            }
        }

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
