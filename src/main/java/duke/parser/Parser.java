package duke.parser;


import duke.exception.TaskNotFoundException;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.exception.InvalidTaskDescriptionException;
import duke.task.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;


public class Parser {

    private static final String DATE_AND_TIME_FORMAT = "dd/MM/yyyy[ HH:mm]";
    private static final String PRINT_DATE_AND_TIME_FORMAT = "MMM dd yyyy HH:mm";

    /**
     * Extracts the command from input provided by the user.
     *
     * @param userInput input from user from the command line interface
     * @return command extracted from user input
     *
     */
    public static String getCommand(String userInput) {
        String[] input = userInput.trim().toLowerCase().split(" ");
        return input[0];
    }

    /**
     * Processes extracted command and description from input provided by the user and executes the task if the input is valid.
     * An error message will be printed if the extracted command is invalid, or if the description of the task is invalid,
     * or if the user tries to find a task that does not exist in the list.
     *
     * @param userInput input from user from the command line interface
     * @param tasks ArrayList of tasks
     */
    public static void parseCommand(String userInput, ArrayList<Task> tasks) {
        try {
            String command = getCommand(userInput);
            switch (command) {
            case ("bye"):
                Ui.printFarewellMessage();
                break;
            case ("list"):
                TaskList.requestList(tasks);
                break;
            case ("done"):
            case ("undo"):
                TaskList.changeDoneStatus(userInput, tasks);
                break;
            case ("todo"):
                TaskList.addTodo(userInput, tasks);
                break;
            case ("deadline"):
            case ("event"):
                TaskList.addDeadlineOrEvent(userInput, tasks);
                break;
            case ("delete"):
                TaskList.deleteTask(userInput, tasks);
                break;
            case ("find"):
                String filteredInput = getFindDescription(userInput);
                TaskList.findTasks(filteredInput, tasks);
                break;
            default:
                Ui.printErrorMessage();
                break;
            }
        } catch (InvalidTaskDescriptionException | TaskNotFoundException e) {
            Ui.printHorizontalLine();
            System.out.println(e.getMessage());
            Ui.printHorizontalLine();
        }
    }

    /**
     * Extracts a substring containing the description of a Todo task.
     *
     * @param userInput input from user from the command line interface
     * @return string containing task description of a Todo
     */
    public static String getTodoDescription(String userInput) {
        int descriptionPosition = userInput.trim().indexOf(" ");
        return userInput.trim().substring(descriptionPosition);
    }

    /**
     * Returns an int representing the starting position of the description and deadline of
     * a Deadline or Event task.
     *
     * @param userInput input from user from the command line interface
     * @return starting position of task description for Deadline or Event tasks
     */
    public static int DeadlineOrEventDescriptionPosition(String userInput) {
        return userInput.trim().indexOf(" ");
    }

    /**
     * Returns an int representing the starting position of the deadline for a Deadline or Event
     * task.
     * This method distinguishes between a Deadline and Event task. For example, if a Deadline task
     * if passed into this method, indexOf("/at") will have a value of -1 as the /at attribute is for
     * an Event task. indexOf("/by"), for a Deadline task, is guaranteed to be more than 0.
     *
     * @param userInput input from user from the command line interface
     * @return starting position of the date and/or time deadline for a Deadline or Event task.
     */
    public static int DeadlineOrEventTimePosition(String userInput) {
        return Math.max(userInput.trim().indexOf("/by"), userInput.trim().indexOf("/at"));
    }

    /**
     * Extracts a substring containing the description of a Deadline or Event task.
     *
     * @param userInput input from user from the command line interface
     * @return string containing Deadline or Event description
     */
    public static String getDeadlineOrEventDescription(String userInput) throws InvalidTaskDescriptionException {
        int descriptionPosition = DeadlineOrEventDescriptionPosition(userInput);
        int timePosition = DeadlineOrEventTimePosition(userInput);
        if (!isValidPosition(timePosition)) {
            throw new InvalidTaskDescriptionException("Add a /by for deadline and /at for event! Try again!");
        }
        return userInput.substring(descriptionPosition, timePosition);
    }

    /**
     * Extracts date and time deadline from a Deadline task, or duration from an Event task.
     *
     * @param userInput input from user from the command line interface
     * @return string containing date and time for a Deadline or Event task.
     */
    public static String getDateAndTimeSubstring(String userInput) {
        int timePosition = DeadlineOrEventTimePosition(userInput);
        return userInput.substring(timePosition + 3).trim();
    }

    /**
     * Converts date and time from a string to a LocalDateTime object. The string must
     * contain the date in the required format, while the time attribute is optional. Otherwise,
     * an error message will be shown prompting the date and time to be entered in a specific format.
     *
     * @param input string containing date and time for a Deadline or Event task.
     * @return LocalDateTime object representing date and time for given task.
     */
    public static LocalDateTime convertSubStringToDateAndTime(String input) {
        LocalDateTime dateAndTime = null;
        //@@author alwinangys-reused
        //Reused from https://stackoverflow.com/questions/48280447/java-8-datetimeformatter-with-optional-part
        // with minor modifications
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_AND_TIME_FORMAT);
            TemporalAccessor temporalAccessor = formatter.parseBest(input, LocalDateTime::from, LocalDate::from);
            if (temporalAccessor instanceof LocalDateTime) {
                dateAndTime = (LocalDateTime) temporalAccessor;
            } else {
                dateAndTime = ((LocalDate) temporalAccessor).atStartOfDay();
            }
        } catch (DateTimeParseException e) {
            Ui.printHorizontalLine();
            System.out.println("Enter date and or time in this format: dd/MM/yyyy HH:mm (time is optional)");
            Ui.printHorizontalLine();
        }
        //@@author
        return dateAndTime;
    }

    public static String printDateAndTimeAsString(LocalDateTime dateAndTime) {
        return dateAndTime.format(DateTimeFormatter.ofPattern(PRINT_DATE_AND_TIME_FORMAT));
    }

    public static String storeDateAndTimeAsString(LocalDateTime dateAndTime) {
        return dateAndTime.format(DateTimeFormatter.ofPattern(DATE_AND_TIME_FORMAT));
    }

    /**
     * Extracts task number when the user tries to do or undo tasks.
     * +1 in taskNumberPosition is to account for the whitespace between the command and task number.
     * -1 in the return value is to accommodate for the 0-based ArrayList of tasks.
     *
     * @param userInput input from user from the command line interface
     * @return int representing task number.
     */
    public static int getTaskNumber(String userInput) {
        int taskNumberPosition = userInput.trim().indexOf(" ") + 1;
        return Integer.parseInt(userInput.trim().substring(taskNumberPosition)) - 1;
    }

    public static boolean isValidTaskDescription(String userInput) {
        String[] description = userInput.trim().split(" ");
        return description.length > 1;
    }

    public static boolean isValidDeadlineOrEventDescription(String userInput) {
        return isValidDeadlineFormat(userInput) || isValidEventFormat(userInput);
    }

    public static boolean isValidDeadlineFormat(String userInput) {
        return userInput.contains("/by");
    }

    public static boolean isValidEventFormat(String userInput) {
        return userInput.contains("/at");
    }

    /**
     * Extracts keyword to find any tasks containing the keyword. This method reuses
     * getTodoDescription but under a different name to better suit the Find command.
     *
     * @param userInput input from user from the command line interface
     * @return string containing keyword to search for tasks
     */
    public static String getFindDescription(String userInput) throws InvalidTaskDescriptionException {
        String findDescription;
        try {
            findDescription = getTodoDescription(userInput);
            return findDescription;
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidTaskDescriptionException("Tell me what kind of tasks you would like to find!");
        }
    }

    public static boolean isDeleteAll(String userInput) {
        return userInput.trim().equalsIgnoreCase("delete all");
    }

    /**
     * Used in conjunction with DeadlineOrEventTimePosition method. As that method employs Math.max(),
     * if the userInput does not contain either /by or /at, Math.max() will return -1.
     * @param position index of the userInput where /by or /at is located, if it exists
     * @return true if position is any value other than -1
     */
    public static boolean isValidPosition(int position) {
        return position != -1;
    }
}
