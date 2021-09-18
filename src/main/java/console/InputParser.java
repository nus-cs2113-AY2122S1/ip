package console;

import commands.DeadlineCommand;
import commands.EventCommand;
import commands.ToDoCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.ListCommand;
import commands.MarkDoneCommand;
import error.Error;
import task.TaskManager;

import java.util.Scanner;

public abstract class InputParser {

    public static final int TASK_INDEX = 1;
    public static final int COMMAND_INDEX = 0;
    public static final String DATE_SEPARATOR = "/";
    public static final String SEPARATOR = " ";

    public static String getUserCommand(Scanner in) {
        return in.nextLine();
    }

    public static Command parseCommand(String[] commandComponents, TaskManager taskManager) {
        Command command;
        switch (commandComponents[COMMAND_INDEX]) {
        case ListCommand.COMMAND_WORD:
            command = new ListCommand(taskManager);
            break;
        case MarkDoneCommand.COMMAND_WORD:
            command = new MarkDoneCommand(taskManager);
            break;
        case ToDoCommand.COMMAND_WORD:
            command = new ToDoCommand(taskManager);
            break;
        case DeadlineCommand.COMMAND_WORD:
            command = new DeadlineCommand(taskManager);
            break;
        case EventCommand.COMMAND_WORD:
            command = new EventCommand(taskManager);
            break;
        case DeleteCommand.COMMAND_WORD:
            command = new DeleteCommand(taskManager);
            break;
        case ExitCommand.COMMAND_WORD:
            command = new ExitCommand(taskManager);
            break;
        default:
            // fake command first
            command = new ExitCommand(taskManager);
            Error.displayInvalidCommandError();
            break;
        }
        return command;
    }

    public static String getTaskDetails(String[] words) {
        StringBuilder taskName = new StringBuilder();
        for (int i = TASK_INDEX; i < words.length; i++) {
            taskName.append(words[i]);
            taskName.append(SEPARATOR);
        }
        return taskName.toString().trim();
    }

    public static String[] getCommandComponents(String commandInput) {
        return commandInput.trim().split(SEPARATOR);
    }

    public static int getTaskNumber(String[] commandComponents) {
        return Integer.parseInt(commandComponents[TASK_INDEX]) - 1;
    }

    public static String[] getTaskWithDateComponents(String taskInformation) {
        String[] taskComponents = taskInformation.split(DATE_SEPARATOR);
        for (int i = 0; i < taskComponents.length; i++) {
            taskComponents[i] = taskComponents[i].trim();
        }
        return taskComponents;
    }
}
