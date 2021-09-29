package duke.parser;

import duke.TaskType;
import duke.command.*;

import duke.exception.DukeException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Parser {

    private static Scanner setUpScanner;

    private static final int COMMAND_KEY = 0;

    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_UPCOMING = "upcoming";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";

    final public static String ERROR_UNKNOWN_INPUT = ":-( OOPS!!! I'm sorry, but I don't know what that means :-(";
    final public static String ERROR_MISSING_TASK_INDEX = "Please do not leave your task number empty :-(";
    final public static String ERROR_EMPTY_TASK_DESCRIPTION = "Please do not leave your task description empty :-(";
    final public static String ERROR_MISSING_FIND_DESCRIPTION = "What are you finding?? :o";
    final public static String ERROR_WRONG_HANDLE_TODO_DESCRIPTION = "Check for missing fields in your description!";
    final public static String ERROR_WRONG_HANDLE_EVENT_DESCRIPTION = "Include /at handler and insert date of event!";
    final public static String ERROR_WRONG_HANDLE_DEADLINE_DESCRIPTION = "Include /by handler and insert deadline!";
    final public static String ERROR_MISSING_INTEGER = "Please enter as follows: COMMAND (INT in number)";
    final public static String ERROR_MISSING_FIELDS = "Missing fields in database. Duke will not load database.";
    final public static String ERROR_UNNECESSARY_DESCRIPTION = "Please refrain from writing nonsense in Duke..";

    public Parser() {
    }

    public static Command parse(String fullCommand) throws DukeException {
        String[] splitStringBySpace = fullCommand.trim().split("\\s+", 2);
        switch (splitStringBySpace[COMMAND_KEY]) {
        case COMMAND_BYE:
            return parseByeCommand(fullCommand);
        case COMMAND_LIST:
            return parseListCommand(fullCommand);
        case COMMAND_DONE:
            return parseDoneCommand(fullCommand);
        case COMMAND_TODO:
            return parseTodoCommand(fullCommand);
        case COMMAND_EVENT:
            return parseEventCommand(fullCommand);
        case COMMAND_DEADLINE:
            return parseDeadlineCommand(fullCommand);
        case COMMAND_UPCOMING:
            return parseUpcomingCommand(fullCommand);
        case COMMAND_DELETE:
            return parseDeleteCommand(fullCommand);
        case COMMAND_FIND:
            return parseFindCommand(fullCommand);
        default:
            throw new DukeException(ERROR_UNKNOWN_INPUT);
        }
    }

    public static void setScanner(String filePath) throws IOException {
        File storedData = new File(filePath);
        setUpScanner = new Scanner(storedData);
    }

    public static String[] getLineData() throws DukeException {
        String lineDataString = setUpScanner.nextLine();
        String[] lineData = lineDataString.trim().split(" \\| ");
        if (isIncomplete(lineData)) {
            throw new DukeException(ERROR_MISSING_FIELDS);
        }
        return lineData;
    }

    private static void addTaskExceptionHandler(String userInput, TaskType specificTask) throws
            DukeException {
        if (isEmptyDescription(userInput)) {
            throw new DukeException(ERROR_EMPTY_TASK_DESCRIPTION);
        } else if (isIncorrectFormat(userInput, specificTask)) {
            if (specificTask.equals(TaskType.EVENT)) {
                throw new DukeException(ERROR_WRONG_HANDLE_EVENT_DESCRIPTION);
            } else if (specificTask.equals(TaskType.DEADLINE)) {
                throw new DukeException(ERROR_WRONG_HANDLE_DEADLINE_DESCRIPTION);
            } else {
                throw new DukeException(ERROR_WRONG_HANDLE_TODO_DESCRIPTION);
            }
        }
    }

    public static boolean hasNext() {
        return setUpScanner.hasNext();
    }

    public static boolean isDone(String isDoneString) {
        return isDoneString.equals("X");
    }

    private static boolean isIncorrectFormat(String userInput, TaskType specificTask) {
        switch (specificTask) {
        case EVENT:
            boolean hasEventPlaceholder = userInput.contains("/at");
            boolean hasNoEventPlaceholder = !hasEventPlaceholder;
            return hasNoEventPlaceholder;

        case DEADLINE:
            boolean hasDeadlinePlaceholder = userInput.contains("/by");
            boolean hasNoDeadlinePlaceholder = !hasDeadlinePlaceholder;
            return hasNoDeadlinePlaceholder;

        default:
            return false;
        }
    }

    private static boolean isIncomplete(String[] lineData) {
        return lineData.length < 3;
    }

    /**
     * Returns the search key in String entered by the user after splitting the user Input String.
     * If the search key is missing, exception is thrown and the programme breaks out of this function.
     *
     * @param userInput User's input in the Command Line.
     * @return Search key entered by user.
     * @throws DukeException If search key after 'find' command is left empty.
     */
    private static String getUserSearchKey(String userInput) throws DukeException {
        if (isEmptyDescription(userInput)) {
            throw new DukeException("What are you finding?? :o");
        }
        String[] splitStringBySpace = userInput.trim().split("\\s+", 2);
        String userInputSearchKey = splitStringBySpace[1];
        return userInputSearchKey;
    }

    /**
     * Returns boolean true if user's input lacks descriptive field, separated by space after the command keyword.
     *
     * @param userInput User's input in the Command Line.
     * @return Boolean for empty description in user's input.
     */
    private static boolean isEmptyDescription(String userInput) {
        String[] trimDescription = userInput.trim().split("\\s+", 2);
        return trimDescription.length < 2;
    }

    private static Command parseFindCommand(String fullCommand) throws DukeException {
        if (isEmptyDescription(fullCommand)) {
            throw new DukeException(ERROR_MISSING_FIND_DESCRIPTION);
        }
        String userInputSearchKey = getUserSearchKey(fullCommand);
        return new FindCommand(userInputSearchKey);
    }

    private static Command parseListCommand(String fullCommand) throws DukeException {
        if (!fullCommand.equals(COMMAND_LIST)) {
            throw new DukeException(ERROR_UNNECESSARY_DESCRIPTION);
        }
        return new ListCommand();
    }

    private static Command parseByeCommand(String fullCommand) throws DukeException {
        if (!fullCommand.equals(COMMAND_BYE)) {
            throw new DukeException(ERROR_UNNECESSARY_DESCRIPTION);
        }
        return new ByeCommand();
    }

    private static Command parseTodoCommand(String fullCommand) throws DukeException {
        addTaskExceptionHandler(fullCommand, TaskType.TODO);
        return new AddCommand(fullCommand, TaskType.TODO);
    }

    private static Command parseEventCommand(String fullCommand) throws DukeException {
        addTaskExceptionHandler(fullCommand, TaskType.EVENT);
        return new AddCommand(fullCommand, TaskType.EVENT);
    }

    private static Command parseDeadlineCommand(String fullCommand) throws DukeException {
        addTaskExceptionHandler(fullCommand, TaskType.DEADLINE);
        return new AddCommand(fullCommand, TaskType.DEADLINE);
    }

    private static Command parseUpcomingCommand(String fullCommand) throws DukeException {
        if (!fullCommand.equals(COMMAND_UPCOMING)) {
            throw new DukeException(ERROR_UNNECESSARY_DESCRIPTION);
        }
        return new UpcomingCommand();
    }

    private static Command parseDoneCommand(String fullCommand) throws DukeException, NumberFormatException {
        int getUserInputInt = getUserInputInt(fullCommand);
        return new DoneCommand(getUserInputInt);
    }

    private static Command parseDeleteCommand(String fullCommand) throws DukeException, NumberFormatException {
        int getUserInputInt = getUserInputInt(fullCommand);
        return new DeleteCommand(getUserInputInt);
    }

    /**
     * Returns the integer specified by the user after splitting the user input String.
     * If the integer is out-of-bounds, not formatted properly or left empty, an exception is thrown,
     * and the programme breaks out of the function.
     *
     * @param fullCommand User's input in the Command Line.
     * @return Integer within user's input for 'done' and 'delete' commands.
     * @throws NumberFormatException If the number in the form of string cannot be parsed into an Integer.
     * @throws DukeException         If description after the command is left empty.
     */
    private static int getUserInputInt(String fullCommand) throws DukeException, NumberFormatException {
        if (isEmptyDescription(fullCommand)) {
            throw new DukeException(ERROR_MISSING_TASK_INDEX);
        }
        String[] splitStringBySpace = fullCommand.trim().split("\\s+", 2);
        String userInputIntString = splitStringBySpace[1];
        int userInputInt = 0;
        try {
            userInputInt = Integer.parseInt(userInputIntString);
        } catch (NumberFormatException e) {
            throw new DukeException(ERROR_MISSING_INTEGER);
        }
        return userInputInt;
    }

    /**
     * Returns a boolean value of whether a task's deadline is less than three days away from today's date.
     * A task is due in less than three days if the date has not been passed yet,
     * and the difference in days is less than three.
     *
     * @param deadline Deadline of a task in the form (d/M/yyyy HHmm)
     * @return Boolean of whether a task's deadline is three days away from the today's day (current date time)
     */
    public static boolean isThreeDaysAway(String deadline) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime deadlineDateTime = LocalDateTime.parse(deadline, formatter);
        long diff = ChronoUnit.DAYS.between(currentDateTime, deadlineDateTime);
        final int THREE_DAYS = 3;
        boolean isWithinThreeDays = (int) diff <= THREE_DAYS;
        boolean hasNotPassed = (int) diff > 0;
        return isWithinThreeDays && hasNotPassed;
    }
}
