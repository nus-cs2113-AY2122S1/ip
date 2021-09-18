package commands;

import console.InputParser;
import task.TaskManager;

public class ToDoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    public ToDoCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public String executeCommand() {
        taskManager.addTodoTask(InputParser.getTaskDetails(commandComponents));
        return COMMAND_WORD;
    }
}
