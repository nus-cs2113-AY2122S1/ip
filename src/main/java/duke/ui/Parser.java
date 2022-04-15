package duke.ui;

import duke.error.DukeException;
import duke.tasklist.TaskManager;
import duke.tasklist.command.Command;
import duke.tasklist.command.DeadlineCommand;
import duke.tasklist.command.DeleteCommand;
import duke.tasklist.command.EventCommand;
import duke.tasklist.command.ExitCommand;
import duke.tasklist.command.FindCommand;
import duke.tasklist.command.ListCommand;
import duke.tasklist.command.SetDoneCommand;
import duke.tasklist.command.ToDoCommand;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Class responsible to parse user commands.
 */
public class Parser {

    private static final int INDEX_COMMAND = 0;
    private static final int INDEX_TASK_INFO = 1;

    private static final int INDEX_DATE = 0;
    private static final int INDEX_TIME = 1;
    private static final String DATE_TIME_FORMAT = "dd MMM yyyy HH:mm";

    private static final String COMMAND_LIST_TASK = "list";
    private static final String COMMAND_ADD_TODO = "todo";
    private static final String COMMAND_ADD_DEADLINE = "deadline";
    private static final String COMMAND_ADD_EVENT = "event";
    private static final String COMMAND_FINISH_TASK = "done";
    private static final String COMMAND_DELETE_TASK = "delete";
    private static final String COMMAND_FIND_TASK = "find";
    private static final String COMMAND_EXIT = "bye";

    /**
     * Extracts the command type from the full user input.
     * @param fullCommand String containing the full command from the user.
     * @return String containing the command type from the user input.
     */
    public String extractCommand(String fullCommand) {
        String[] inputs = fullCommand.split(" ", 2);
        return inputs[INDEX_COMMAND];
    }

    /**
     * Extracts the indexes or task info from the full user input.
     * @param fullCommand String containing the full command from the user.
     * @return String containing indexes or task info from the user input.
     */
    public String extractTaskInfo(String fullCommand) {
        String[] inputs = fullCommand.split(" ", 2);
        return inputs[INDEX_TASK_INFO];
    }

    /**
     * Splits the task description from dateTime for 'deadline' and 'event' task types.
     * @param taskInfo contains the information of the task.
     * @return Array of strings where index 0: description, and index 1: dateTime.
     */
    public String[] splitTaskComponents(String taskInfo) {
        String[] taskComponents;
        taskComponents = taskInfo.replace("/", "#/").split("#");

        for (int i = 0; i < taskComponents.length; i++) {
            String taskComponent = taskComponents[i].replaceAll("/by", "");
            taskComponent = taskComponent.replaceAll("/at", "");
            taskComponents[i] = taskComponent.trim();
        }

        return taskComponents;
    }

    /**
     * This function processes the command from the user and returns the corresponding command object to be executed.
     * @param taskManager TaskManager object used to perform task operations.
     * @param parser Parser object used to perform parsing operations.
     * @param fullCommand String containing full command from user.
     * @return Corresponding command object from user command.
     * @throws DukeException Handles the errors related to Duke.
     */
    public Command parse(TaskManager taskManager, Parser parser, String fullCommand) throws DukeException {
        Command command;
        String taskInfo;
        String commandType = extractCommand(fullCommand);

        switch (commandType) {
        case COMMAND_LIST_TASK:
            command = new ListCommand(taskManager);
            break;
        case COMMAND_ADD_TODO:
            try {
                taskInfo = extractTaskInfo(fullCommand);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of " + commandType + " cannot be empty.");
            }
            command = new ToDoCommand(taskManager, taskInfo);
            break;
        case COMMAND_ADD_DEADLINE:
            try {
                taskInfo = extractTaskInfo(fullCommand);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of " + commandType + " cannot be empty.");
            }
            command = new DeadlineCommand(taskManager, parser, taskInfo);
            break;
        case COMMAND_ADD_EVENT:
            try {
                taskInfo = extractTaskInfo(fullCommand);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of " + commandType + " cannot be empty.");
            }
            command = new EventCommand(taskManager, parser, taskInfo);
            break;
        case COMMAND_FINISH_TASK:
            try {
                taskInfo = extractTaskInfo(fullCommand);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of " + commandType + " cannot be empty.");
            }
            command = new SetDoneCommand(taskManager, taskInfo);
            break;
        case COMMAND_DELETE_TASK:
            try {
                taskInfo = extractTaskInfo(fullCommand);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of " + commandType + " cannot be empty.");
            }
            command = new DeleteCommand(taskManager, taskInfo);
            break;
        case COMMAND_FIND_TASK:
            try {
                taskInfo = extractTaskInfo(fullCommand);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of " + commandType + " cannot be empty.");
            }
            command = new FindCommand(taskManager, taskInfo);
            break;

        case COMMAND_EXIT:
            command = new ExitCommand();
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return command;
    }

    public String parseDateTime(String dateTime) {
        String[] dateTimeComponents = dateTime.split(" ");
        LocalDate date = LocalDate.parse(dateTimeComponents[INDEX_DATE]);
        LocalTime time = LocalTime.parse(dateTimeComponents[INDEX_TIME]);
        LocalDateTime localDateTime = LocalDateTime.of(date, time);

        return localDateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }
}
