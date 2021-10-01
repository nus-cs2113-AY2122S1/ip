package duke.commands;

import static duke.ui.Strings.MESSAGE_LIST;
import static duke.ui.Strings.MESSAGE_LIST_EMPTY;
import static duke.ui.Strings.MESSAGE_LIST_FIND;
import static duke.ui.Strings.MESSAGE_LIST_FIND_EMPTY;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.TextUi;
import java.util.ArrayList;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "<search_string>";
    public static final String MESSAGE_HELP = "Searches tasklist for tasks matching given string";

    private final String searchString;

    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public void execute(TaskList taskList, TextUi ui, Storage storage) {
        ArrayList<Task> tasks = taskList.getTaskList();

        if (tasks.size() <= 0) {
            ui.printMessage(MESSAGE_LIST_EMPTY);
        } else {
            ArrayList<String> todo = new ArrayList<>();

            int index = 1;

            for (Task task : tasks) {
                if (task.getDescription().contains(searchString)) {
                    todo.add(String.format("%d.%s", index, task.toFormattedString()));
                    index++;
                }
            }

            if (todo.isEmpty()) {
                ui.printMessage(String.format(MESSAGE_LIST_FIND_EMPTY, searchString));
            } else {
                ui.printMessage(String.format(MESSAGE_LIST_FIND, searchString));
                String[] toPrint = todo.toArray(String[]::new);
                ui.printMessage(toPrint);
            }
        }
    }
}
