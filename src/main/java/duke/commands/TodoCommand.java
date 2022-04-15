package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Todo;

/**
 * Command Class that executes the creation of a new Todo task.
 */
public class TodoCommand extends Command {
    public static final int END_OF_TODO_INDEX = 4;

    /**
     * Initializes new TodoCommand object.
     * @param fullCommand full user input as a string
     */
    public TodoCommand(String fullCommand) {
        this.isExit = false;
        this.fullCommand = fullCommand;
    }

    /**
     * Executes creation of new Todo task with description as inputted by user.
     * @param tasks TaskList object of all tasks in the programme
     * @param ui Ui object for calling Ui methods
     * @param storage Storage object for writing to memory
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        handleTodoTask(tasks, ui, storage);
        ui.showTaskConfirmation(tasks);
    }

    /**
     * Parses command input by user and adds new Todo Task to {@code tasks}.
     * @param tasks TaskList object containing all tasks as an ArrayList
     * @param ui Ui object for calling of ui methods
     * @param storage Storage object for writing to memory
     */
    private void handleTodoTask(TaskList tasks, Ui ui, Storage storage) {
        String description = fullCommand.substring(END_OF_TODO_INDEX).trim();
        String memWritableText = formatForMemory(description, tasks.getTasks().size());
        tasks.getTasks().add(new Todo(description));
        storage.appendToMem(memWritableText);
    }

    /**
     * Writes user input into format for storage in the memory file.
     * If size == 0, line separator is not included at the front of the string.
     * @param description description of task from user input
     * @param size size of ArrayList of tasks
     * @return formatted string for writing to memory
     */
    private String formatForMemory(String description, int size) {
        if (size == 0) {
            return "T~0~" + description;
        } else {
            return System.lineSeparator() + "T~0~" + description;
        }
    }
}
