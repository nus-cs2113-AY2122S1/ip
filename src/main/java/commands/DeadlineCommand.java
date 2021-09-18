package commands;

import console.InputParser;
import task.TaskManager;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    public DeadlineCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public String executeCommand() {
        taskManager.addDeadlineTask(InputParser.getTaskDetails(commandComponents));
        return COMMAND_WORD;
    }
}
