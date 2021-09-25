package commands;

import data.Storage;
import data.TaskList;
import ui.TextUI;
import task.ToDo;

import static common.Message.ADDED_TASK;
import static common.Error.ERROR_FORMAT_TODO;

public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "/todo";
    protected String args;
    public String description;

    public ToDoCommand(String args) {
        this.args = args;
    }

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
