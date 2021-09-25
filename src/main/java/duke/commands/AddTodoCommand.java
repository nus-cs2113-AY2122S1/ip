package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.ui.Ui;

/**
 * Adds a Todo Task.
 * If no arguments provided by user, prints error message.
 */
public class AddTodoCommand extends Command {
    String arguments;

    public AddTodoCommand(String command, String arguments) {
        super(command);
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (arguments.equals("")) {
            String output = " ☹ OOPS!!! The description of a todo cannot be empty.\n";
            ui.printOutput(output);
        } else {
            Todo newTodo = new Todo(arguments);
            tasks.addTask(newTodo);
            int taskListSize = tasks.sizeOfTaskList();
            ui.acknowledgeAddedTask(newTodo, taskListSize);
        }
    }
}