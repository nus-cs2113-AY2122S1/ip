package commands;

import data.Storage;
import data.TaskList;
import ui.TextUI;
import task.ToDo;

import static common.Message.ADDED_TASK;
import static common.Error.ERROR_FORMAT_TODO;

/**
 * Represents the /todo command
 * This command creates a new ToDo task
 * COMMAND_WORD represents exact string user should provide to invoke this command
 */
public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "/todo";
    protected String args;
    public String description;

    /**
     * Sole constructor
     * @param args Additional arguments supplied by user after COMMAND_WORD
     */
    public ToDoCommand(String args) {
        this.args = args;
    }

    /**
     * Splits arguments supplied by user after COMMAND_WORD into description.
     * Creates new ToDo object and adds it to tasks container, writes changes
     * into data.txt, displaying success messages when complete. Error messages
     * are also output if command is malformed.
     * Overrides method from parent class
     * @param ui Object that handles user IO
     * @param tasks Object that stores current and updated list of tasks
     * @param data Object that handles storage, read and writes to data.txt
     */
    @Override
    public void execute(TextUI ui, TaskList tasks, Storage data) {
        try {
            description = args.substring(6);

            ToDo newTodo = new ToDo(description, false);
            tasks.addTask(newTodo);

            String userOutput = String.format(ADDED_TASK, newTodo, tasks.getSize());
            ui.showMessage(userOutput);
            data.write(tasks.getTaskList());
        } catch (StringIndexOutOfBoundsException e) {
            ui.showMessage(ERROR_FORMAT_TODO);
        }
    }
}
