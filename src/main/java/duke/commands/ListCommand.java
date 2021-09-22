package duke.commands;

import static duke.ui.Strings.MESSAGE_LIST;
import static duke.ui.Strings.MESSAGE_LIST_EMPTY;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.TextUi;
import java.util.ArrayList;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD;
    public static final String MESSAGE_HELP = "Prints an indexed list of all added tasks";

    @Override
    public void execute(TaskList taskList, TextUi ui, Storage storage) {

        ArrayList<Task> tasks = taskList.getTaskList();

        if (tasks.size() <= 0) {
            ui.printMessage(MESSAGE_LIST_EMPTY);
        } else {
            ui.printMessage(MESSAGE_LIST);

            int index = 1;

            for (Task task : tasks) {
                ui.printMessage(String.format("%d.%s", index, task.toFormattedString()));
                index++;
            }
        }
    }
}
