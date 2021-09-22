package duke.parser;

import duke.command.*;

import duke.task.TaskManager;


public class Parser {

    private final String TERMINATE_CMD = "bye";
    private final String HELP_CMD = "help";
    private final String LIST_CMD = "list";
    private final String ADD_TODO_CMD = "todo";
    private final String ADD_DEADLINE_CMD = "deadline";
    private final String ADD_EVENT_CMD = "event";
    private final String SET_TASK_DONE_CMD = "done";
    private final String DELETE_TASK_CMD = "delete";
    private final String FIND_TASK_CMD = "find";

    public Command parseCommand(TaskManager taskManager, String userInput) {
        String[] inputArguments = userInput.split("\\s+", 2);
        String command = inputArguments[0];
        String commandArguments = "";

        if (inputArguments.length == 2) {
            commandArguments = inputArguments[1];
        }

        switch(command) {
        case TERMINATE_CMD:
            return new TerminateCommand();
        case HELP_CMD:
            return new HelpCommand();
        case LIST_CMD:
            return new ListCommand(taskManager);
        case ADD_TODO_CMD:
            return new AddToDoCommand(taskManager, commandArguments);
        case ADD_DEADLINE_CMD:
            return new AddDeadlineCommand(taskManager, commandArguments);
        case ADD_EVENT_CMD:
            return new AddEventCommand(taskManager, commandArguments);
        case SET_TASK_DONE_CMD:
            return new SetTaskDoneCommand(taskManager, commandArguments);
        case DELETE_TASK_CMD:
            return new DeleteTaskCommand(taskManager, commandArguments);
        case FIND_TASK_CMD:
            return new FindTaskCommand(taskManager, commandArguments);
        default:
            return new InvalidCommand();
        }
    }

}
