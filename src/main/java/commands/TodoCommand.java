package commands;

import storage.Storage;
import tasks.Task;
import tasks.Todo;
import ui.Ui;

import java.util.ArrayList;

/**
 * Represents the Todo command. Helps to do all operations of the Todo command such as
 * creating the Todo object and does all the error handling such as a empty description.
 */

public class TodoCommand extends Command {
    private static final String TODO_ERROR = "☹ OOPS!!! The description of a todo cannot be empty.";
    public static final String COMMAND_SYNTAX = "Command Syntax: todo <task name>";

    public String description;

    public TodoCommand(String command, String description) {
        super(command);
        this.description = description;
    }

    @Override
    public String help() {
        return COMMAND_SYNTAX;
    }

    public void execute(Ui ui, ArrayList<Task> tasks, Storage storage) {
        if (description.equals("")) {
            ui.customPrint(TODO_ERROR + "\n" + COMMAND_SYNTAX);
        } else {
            Todo todo = new Todo(description);
            tasks.add(todo);
            ui.customPrint(String.format(addTaskMessage, todo, tasks.size()));
            storage.saveData(ui, tasks);
        }
    }
}
