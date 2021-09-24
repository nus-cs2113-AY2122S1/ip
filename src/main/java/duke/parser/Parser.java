package duke.parser;

import duke.common.Command;
import duke.exception.EmptyCommandException;
import duke.exception.IllegalCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;


/**
 * Includes methods that require parsing of text, such as from commands entered by the
 * user or data saved in the data file.
 */
public class Parser {

    private final static String COMMAND_SEP = " ";

    private final static int TODO_LENGTH = 4;
    private final static int DEADLINE_LENGTH = 8;
    private final static int EVENT_LENGTH = 5;
    private final static String DEADLINE_SEP = "/by";
    private final static String EVENT_SEP = "/at";

    /**
     * Parses the command entered by the user for necessary information, such as the command
     * type or task details, and executes the required command.
     *
     * @param fullCommand the string entered by the user
     * @throws EmptyCommandException if the command entered is incomplete
     * @throws IllegalCommandException if an unknown command is entered
     * @throws IOException if there is an error writing to the file
     */
    public static void handleCommand(String fullCommand) throws EmptyCommandException, IllegalCommandException,
            IOException {
        String[] parsedCommand = fullCommand.split(COMMAND_SEP);

        switch (parsedCommand[0].toUpperCase()) {
        case "LIST":
            Command.executeList();
            break;
        case "TODO":
            parseAndAddTodo(fullCommand);
            break;
        case "DEADLINE":
            parseAndAddDeadline(fullCommand);
            break;
        case "EVENT":
            parseAndAddEvent(fullCommand);
            break;
        case "DONE":
            parseAndExecuteDone(parsedCommand);
            break;
        case "DELETE":
            parseAndExecuteDelete(parsedCommand);
            break;
        case "FIND":
            parseAndExecuteFind(fullCommand);
            break;
        case "DATE":
            parseAndExecuteDate(fullCommand);
            break;
        default:
            throw new IllegalCommandException();
        }
    }

    /**
     * Creates an appropriate Task object from the saved data file to be added to the task list in Duke.
     *
     * @param taskInfo the description of the saved task
     * @return the Task object to be added to the task array
     * @throws IOException if an invalid line is found in the data file
     */
    public static Task parseTaskFromData(String taskInfo) throws IOException {
        String[] parsedData = taskInfo.split(Task.ESCAPED_DATA_SEP);
        Task newTask;

        switch (parsedData[0].strip()) {
        case Task.DEADLINE_ICON:
            LocalDateTime parsedBy = parseDateTimeFromData(parsedData[3].strip());
            newTask = new Deadline(parsedData[2].strip(), parsedBy);
            break;
        case Task.EVENT_ICON:
            LocalDateTime parsedAt = parseDateTimeFromData(parsedData[3].strip());
            newTask = new Event(parsedData[2].strip(), parsedAt);
            break;
        case Task.TODO_ICON:
            newTask = new Todo(parsedData[2].strip());
            break;
        default:
            throw new IOException();
        }

        String parsedDoneIcon = parsedData[1].strip();
        if (parsedDoneIcon.equals(Task.DONE_ICON)) {
            newTask.setDone(true);
        }
        return newTask;
    }

    private static LocalDateTime parseDateTimeFromData(String stringFromData) {
        return LocalDateTime.parse(stringFromData, Task.dataFormat);
    }

    private static void parseAndAddTodo(String fullCommand) throws IOException {
        String description = fullCommand.substring(TODO_LENGTH).strip();
        Command.addTodo(description);
    }

    /**
     * Extracts the description of the Deadline task as a String and its 'by' (or its deadline) as a LocalDateTime
     * object and adds it to the tasklist.
     *
     * @param fullCommand the string entered by the user
     * @throws IOException if there is an error writing to the file
     * @throws IllegalCommandException if an unknown command is entered
     * @throws EmptyCommandException if the command entered is incomplete
     */
    private static void parseAndAddDeadline(String fullCommand) throws IOException, IllegalCommandException,
            EmptyCommandException {
        String commandDescription = fullCommand.substring(DEADLINE_LENGTH).strip();
        if (!commandDescription.contains(DEADLINE_SEP)) {
            throw new IllegalCommandException();
        }
        String[] descWithBy = commandDescription.split(DEADLINE_SEP);
        if (descWithBy.length < 2) {
            throw new EmptyCommandException();
        }
        if (descWithBy.length > 2) {
            throw new IllegalCommandException();
        }
        String trimmedDesc = descWithBy[0].strip();
        LocalDateTime byAsDateTime = parseByFromCommand(descWithBy[1].strip());
        Command.addDeadline(trimmedDesc, byAsDateTime);
    }

    private static LocalDateTime parseByFromCommand(String trimmedBy) throws DateTimeParseException {
        return LocalDateTime.parse(trimmedBy, Task.dataFormat);
    }

    /**
     * Extracts the description of the Event task as a String and its 'at' (or the date and time it occurs at)
     * as a LocalDateTime object and adds it to the tasklist.
     *
     * @param fullCommand the string entered by the user
     * @throws IOException if there is an error writing to the file
     * @throws IllegalCommandException if an unknown command is entered
     * @throws EmptyCommandException if the command entered is incomplete
     */
    private static void parseAndAddEvent(String fullCommand) throws IOException, IllegalCommandException,
            EmptyCommandException {
        String commandDescription = fullCommand.substring(EVENT_LENGTH).strip();
        if (!commandDescription.contains(EVENT_SEP)) {
            throw new IllegalCommandException();
        }
        String[] descWithAt = commandDescription.split(EVENT_SEP);
        if (descWithAt.length < 2) {
            throw new EmptyCommandException();
        } else if (descWithAt.length > 2) {
            throw new IllegalCommandException();
        }
        String trimmedDesc = descWithAt[0].strip();
        LocalDateTime atAsDateTime = parseAtFromCommand(descWithAt[1].strip());
        Command.addEvent(trimmedDesc, atAsDateTime);
    }

    private static LocalDateTime parseAtFromCommand(String trimmedAt) throws DateTimeParseException {
        return LocalDateTime.parse(trimmedAt, Task.dataFormat);
    }

    private static void parseAndExecuteDone(String[] parsedCommand) throws IllegalCommandException, IOException {
        try {
            int taskIndex = Integer.parseInt(parsedCommand[1]);
            Command.doneTask(taskIndex);
        } catch (NumberFormatException e) {
            throw new IllegalCommandException();
        }
    }

    private static void parseAndExecuteDelete(String[] parsedCommand) throws IllegalCommandException, IOException {
        try {
            int taskIndex = Integer.parseInt(parsedCommand[1]);
            Command.deleteTask(taskIndex);
        } catch (NumberFormatException e) {
            throw new IllegalCommandException();
        }
    }

    private static void parseAndExecuteFind(String fullCommand) throws EmptyCommandException {
        String keywords = fullCommand.replace("find", "").trim();
        if (keywords.isEmpty()) {
            throw new EmptyCommandException();
        }
        String[] keywordsArr = keywords.split(COMMAND_SEP);
        Command.findTaskByDesc(keywordsArr);
    }

    private static void parseAndExecuteDate(String fullCommand) throws EmptyCommandException {
        String dateToFind = fullCommand.replace("date","").trim();
        if (dateToFind.isEmpty()) {
            throw new EmptyCommandException();
        }
        LocalDate parsedDate = LocalDate.parse(dateToFind, Task.commandFormat);
        Command.findTaskByDate(parsedDate);
    }
}
