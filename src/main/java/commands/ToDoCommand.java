package commands;

import data.Storage;
import data.TaskList;
import ui.TextUI;
import task.ToDo;

import static common.Message.ADDED_TODO;
import static common.Error.ERROR_FORMAT_TODO;

public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "/todo";
    public String description;

    public ToDoCommand(String args) {
        description = args.substring(6);
    }

    @Override
    public void execute(TextUI ui, TaskList tasks, Storage data) {
        try {
            ToDo newTodo = new ToDo(description, false);
            tasks.addTask(newTodo);
            String userOutput = String.format(ADDED_TODO, newTodo, tasks.getSize());
            ui.showMessage(userOutput);
            data.write(tasks.getTaskList());
        } catch (StringIndexOutOfBoundsException e) {
            ui.showMessage(ERROR_FORMAT_TODO);
        }
    }
}
