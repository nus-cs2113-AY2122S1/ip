package commands;

import console.InputParser;
import task.TaskManager;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public FindCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public String executeCommand() {
        taskManager.findTask(InputParser.getKeyword(commandComponents));
        return COMMAND_WORD;
    }
}
