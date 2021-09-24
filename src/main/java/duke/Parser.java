package duke;

import duke.commands.AddCommand;
import duke.commands.ListCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ExitCommand;
import duke.commands.CommandType;
import duke.commands.Command;
import duke.commands.FindCommand;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeException;
import duke.exceptions.IncompleteInformationException;
import duke.exceptions.InvalidRequestException;
import duke.exceptions.InvalidFilterException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * This class is used to parse string inputs from users and return the input commands as objects
 * that can be executed, according to the commands specified by the user
 */
public abstract class Parser {
    public static final int TIME_INFO_START_INDEX = 1;
    public static final int SPECIAL_DATE_SEQUENCE = 4;

    /**
     * Returns an executable command object based on the input string where the command type and other additional
     * information is specified. If the command cannot be parsed appropriately or if the command the user has
     * input is incorrect, an exception will be thrown
     *
     * @param request It is the string input users enter in the command line to interact with the application
     * @return The method returns a command object which contains a method to carry out the specified command on the task list
     * @throws Exception it is thrown when an error occur when attempting to parse the input command
     */
    public static Command parse(String request) throws Exception {
        if (CommandType.isList(request)) {
            return new ListCommand(CommandType.LIST);
        } else if (CommandType.isDone(request)) {
            int taskIndex = getTaskIndex(request);
            return new DoneCommand(CommandType.DONE, taskIndex);
        } else if (CommandType.isDelete(request)) {
            int taskIndex = getTaskIndex(request);
            return new DeleteCommand(CommandType.DELETE, taskIndex);
        } else if (CommandType.isBye(request)) {
            return new ExitCommand(CommandType.BYE);
        } else if (CommandType.isFind(request)) {
            String filterBy = getTaskFilterWord(request);
            return new FindCommand(CommandType.FIND, filterBy);
        } else {
            return getTask(request);
        }
    }

    private static String getTaskFilterWord(String request) throws InvalidFilterException {
        try {
            return getDescription(request.trim());
        } catch (Exception e) {
            throw new InvalidFilterException();
        }
    }

    private static int getTaskIndex(String request) {
        try {
            int taskIndexStart = request.indexOf(" ");
            String taskIndex = request.substring(taskIndexStart);
            return Integer.parseInt(taskIndex) - 1;
        } catch (Exception e){
            throw new NumberFormatException("Sorry there's no integer I can read!");
        }
    }

    private static Command getTask(String request) throws Exception {
        if (CommandType.isTodo(request)) {
            return buildTodo(request.trim());
        } else if (CommandType.isSpecialTask(request)) {
            return buildSpecialTask(request.trim());
        }
        throw new InvalidRequestException();
    }

    private static Command buildTodo(String request) throws Exception{
        try {
            String description = getDescription(request);
            Task task = new Task(description);
            return new AddCommand(CommandType.TODO,task);
        } catch (Exception ex) {
            throw new IncompleteInformationException(CommandType.TODO);
        }
    }

    private static Command buildSpecialTask(String request) throws Exception {
        try {
            String commandType = CommandType.isEvent(request) ? CommandType.EVENT : CommandType.DEADLINE;
            String description = getDescription(request);
            String time = getTime(request);
            Task task = CommandType.isEvent(request) ? new Event(description, time) : new Deadline(description, time);
            return new AddCommand(commandType, task);
        } catch (Exception ex) {
            throw new IncompleteInformationException(CommandType.getCommand(request));
        }
    }

    private static String getTime(String request) throws EmptyTimeException{
            int timeIndex = request.indexOf("/");
            if (timeIndex < 0) {
                throw new EmptyTimeException();
            }
            try {
                String time = request.substring(timeIndex + SPECIAL_DATE_SEQUENCE).trim();
                LocalDate date = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            } catch (Exception ex) {
                return request.substring(timeIndex + TIME_INFO_START_INDEX).trim();
            }
    }

    private static String getDescription(String request) throws EmptyDescriptionException {
        try {
            int descriptionStartIndex = request.indexOf(" ");
            int descriptionEndIndex = CommandType.isSpecialTask(request) ? request.indexOf("/") : request.length();
            return request.substring(descriptionStartIndex,descriptionEndIndex).trim();
        } catch (Exception e) {
            throw new EmptyDescriptionException();
        }
    }
}
