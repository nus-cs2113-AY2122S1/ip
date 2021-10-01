package duke.commands;

import static duke.ui.Strings.MESSAGE_FILE_NOT_FOUND_EXCEPTION;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.data.task.Todo;
import duke.storage.Storage;
import duke.ui.TextUi;
import java.io.IOException;

public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " <description>";
    public static final String MESSAGE_HELP = "Adds a new task of type todo without any date/time attached to it.";

    private final Task toAdd;

    public TodoCommand(String description) {
        this.toAdd = new Todo(description);
    }

    @Override
    public void execute(TaskList taskList, TextUi ui, Storage storage) {
        try {
            taskList.addTask(toAdd);
            storage.save(taskList.getTaskList());
            ui.printTaskAddedMessage(toAdd.toFormattedString(), taskList.getCount());
        } catch (IOException e) {
            ui.printMessage(MESSAGE_FILE_NOT_FOUND_EXCEPTION);
        }
    }


}
