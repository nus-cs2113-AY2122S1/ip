package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Todo;

public class TodoCommand extends Command {
    public static final int END_OF_TODO_INDEX = 4;

    public TodoCommand(String fullCommand) {
        this.isExit = false;
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        handleTodoTask(tasks, ui, storage);
        ui.showTaskConfirmation(tasks);
    }

    private void handleTodoTask(TaskList tasks, Ui ui, Storage storage) {
        String description = fullCommand.substring(END_OF_TODO_INDEX).trim();
        String memWritableText = formatForMemory(description, tasks.getTasks().size());
        tasks.getTasks().add(new Todo(description));
        storage.appendToMem(memWritableText);
    }

    private String formatForMemory(String description, int size) {
        if (size == 0) {
            return "T~0~" + description;
        } else {
            return System.lineSeparator() + "T~0~" + description;
        }
    }
}
