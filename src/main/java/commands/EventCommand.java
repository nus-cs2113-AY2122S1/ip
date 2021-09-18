package commands;

import console.InputParser;
import task.TaskManager;

public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    public EventCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public String executeCommand() {
        taskManager.addEventTask(InputParser.getTaskDetails(commandComponents));
        return COMMAND_WORD;
    }
}
