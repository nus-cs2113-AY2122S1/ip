package duke.commands;

import static duke.ui.Strings.INDENT;
import static duke.ui.Strings.MESSAGE_FILE_NOT_FOUND_EXCEPTION;
import static duke.ui.Strings.MESSAGE_INVALID_TASK_INDEX_EXCEPTION;
import static duke.ui.Strings.MESSAGE_TASK_DELETE;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.TextUi;
import java.io.IOException;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " <task index in list>";
    public static final String MESSAGE_HELP = "Deletes the task at the supplied index";

    private final int index;

    public DeleteCommand(int index) {
        this.index = --index;
    }

    @Override
    public void execute(TaskList taskList, TextUi ui, Storage storage) {
        try {
            Task removedTask = taskList.removeTask(index);
            storage.save(taskList.getTaskList());
            ui.printMessage(MESSAGE_TASK_DELETE,
                    INDENT + removedTask.toFormattedString());
        } catch (IndexOutOfBoundsException e) {
            ui.printMessage(MESSAGE_INVALID_TASK_INDEX_EXCEPTION);
        } catch (IOException e) {
            ui.printMessage(MESSAGE_FILE_NOT_FOUND_EXCEPTION);
        }

    }
}
