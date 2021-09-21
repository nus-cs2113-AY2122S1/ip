package duke.parser;

import duke.task.TaskManager;
import duke.command.Command;


public class Parser {

    private final String LIST_CMD = "list";
    private final String HELP_CMD = "help";
    private final String ADD_TODO_CMD = "todo";
    private final String ADD_DEADLINE_CMD = "deadline";
    private final String ADD_EVENT_CMD = "event";
    private final String SET_TASK_DONE_CMD = "done";
    private final String DELETE_TASK_CMD = "delete";
    private final String TERMINATE_CMD = "bye";

    public Command parseCommand(TaskManager taskManager, String input) {

        String[] inputArguments = input.split("\\s+", 2);
        String command = inputArguments[0];
        String commandArguments = "";

        if (inputArguments.length == 2) {
            commandArguments = inputArguments[1];
        }

        switch(command) {
        case LIST_CMD:
        case HELP_CMD:
        case ADD_TODO_CMD:
        case ADD_DEADLINE_CMD:
        case ADD_EVENT_CMD:
        case SET_TASK_DONE_CMD:
        case DELETE_TASK_CMD:
        case TERMINATE_CMD:
        }

        return new TerminateCommand(taskManager);
    }

}
