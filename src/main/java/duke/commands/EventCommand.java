package duke.commands;

import static duke.ui.Strings.MESSAGE_FILE_NOT_FOUND_EXCEPTION;

import duke.data.TaskList;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.TextUi;
import java.io.IOException;

public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " <description> /at <time range>";
    public static final String MESSAGE_HELP = "Adds a new task that starts and ends at a specific time";

    private final Task toAdd;

    public EventCommand(String description, String deadline) {
        this.toAdd = new Event(description, deadline);
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
