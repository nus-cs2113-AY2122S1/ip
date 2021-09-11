package utils;

import console.InputParser;
import error.Error;
import task.TaskManager;

public class Command {
    public static final String COMMAND_EXIT_PROGRAM = "bye";
    public static final String COMMAND_MARK_DONE = "done";
    public static final String COMMAND_LIST_TASK = "list";
    public static final String COMMAND_ADD_TODO_TASK = "todo";
    public static final String COMMAND_ADD_DEADLINE_TASK = "deadline";
    public static final String COMMAND_ADD_EVENT_TASK = "event";
    public static final int COMMAND_INDEX = 0;

    public static boolean executeCommand(String[] commandComponents, TaskManager taskManager) {
        boolean hasExitCommand = true;
        switch (commandComponents[COMMAND_INDEX]) {
        case COMMAND_LIST_TASK:
            taskManager.listTask();
            break;
        case COMMAND_MARK_DONE:
            taskManager.markTaskAsCompleted(commandComponents);
            break;
        case COMMAND_ADD_TODO_TASK:
            taskManager.addTodoTask(InputParser.getTaskDetails(commandComponents));
            break;
        case COMMAND_ADD_DEADLINE_TASK:
            taskManager.addDeadlineTask(InputParser.getTaskDetails(commandComponents));
            break;
        case COMMAND_ADD_EVENT_TASK:
            taskManager.addEventTask(InputParser.getTaskDetails(commandComponents));
            break;
        case COMMAND_EXIT_PROGRAM:
            hasExitCommand = false;
            break;
        default:
            Error.displayInvalidCommandError();
            break;
        }

        return hasExitCommand;
    }
}
