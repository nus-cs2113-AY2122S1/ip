package duke.commands;

import static duke.ui.Strings.MESSAGE_FILE_NOT_FOUND_EXCEPTION;

import duke.data.TaskList;
import duke.data.task.Deadline;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.TextUi;
import java.io.IOException;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " <description> /by <deadline>";
    public static final String MESSAGE_HELP = "Adds a new task that need to be done before a specific date/time.";

    private final Task toAdd;

    public DeadlineCommand(String description, String deadline) {
        this.toAdd = new Deadline(description, deadline);
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
