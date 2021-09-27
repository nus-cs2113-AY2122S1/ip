package parser;

import exceptions.DeadlineException;
import exceptions.EventException;
import tasklist.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {

    /**
     * Parses the user input to extract the command which is the first word.
     * @param userLine input given by user or line extracted from saved file
     * @return String[] a split between the command and task description from user
     */
    public static String[] parseCommand(String userLine) {
        return userLine.toLowerCase().split(" ", 2);
    }

    /**
     * Parses the user input to extract the task to be marked as done.
     * @param userLine input given by user or line extracted from saved file
     * @param taskList main list keeping track of user's tasks
     * @return taskNum int of the task in the list to be marked as done
     * @throws ArithmeticException if the taskNum is negative or is more than the size of list
     */
    public static int parseDoneTask(String userLine, ArrayList<Task> taskList) {
        String[] extractDoneTask = userLine.toLowerCase().split(" ", 2);
        int taskNum = Integer.parseInt(extractDoneTask[1]);
        if (taskNum <= 0 || taskNum > taskList.size()) {
            throw new ArithmeticException();
        }
        return taskNum;
    }

    /**
     * Parses the user input to extract the task to be deleted.
     * @param userLine input given by user or line extracted from saved file
     * @param taskList main list keeping track of user's tasks
     * @return taskNum int of the task in the list to be deleted
     * @throws ArithmeticException if the taskNum is negative or is more than the size of list
     */
    public static int parseDeleteTask(String userLine, ArrayList<Task> taskList) {
        String[] extractDeleteTask = userLine.toLowerCase().split(" ", 2);
        int taskNum = Integer.parseInt(extractDeleteTask[1]);
        if (taskNum <= 0 || taskNum > taskList.size()) {
            throw new ArithmeticException();
        }
        return taskNum;
    }

    /**
     * Parses the user input to extract the task description when todo command is called.
     * @param userLine input given by user or line extracted from saved file
     * @return str task description for todo task
     * @throws NumberFormatException if input is missing the description
     */
    public static String parseTodoTask(String userLine) {
        String[] todoInputs = userLine.split(" ", 2);
        if (todoInputs.length < 2) {
            throw new NumberFormatException();
        }
        return todoInputs[1];
    }

    /**
     * Parses the user input to extract the task description and deadline date & time when
     * deadline command is called.
     * @param userLine input given by user or line extracted from saved file
     * @return String[] where index 0 is the task description and index 1 is deadline date & time
     * @throws NumberFormatException if input is missing the description and deadline date & time
     * @throws DeadlineException if input does not contain '/by' key to split the description and
     * deadline date & time
     */
    public static String[] parseDeadlineTask(String userLine) throws DeadlineException {
        String[] deadlineInputs = userLine.split(" ", 2);
        if (deadlineInputs.length < 2) {
            throw new NumberFormatException();
        }
        if (!userLine.contains("/by")) {
            throw new DeadlineException();
        }
        return deadlineInputs[1].split(" /by ");
    }

    /**
     * Parses the user input to extract the task description and event date & time
     * when event command is called.
     * @param userLine input given by user or line extracted from saved file
     * @return String[] where index 0 is the task description and index 1 is event date & time
     * @throws NumberFormatException if input is missing the description and event date & time
     * @throws EventException if input does not contain '/by' key to split the description and
     * event date & time
     */
    public static String[] parseEventTask(String userLine) throws EventException {
        String[] eventInputs = userLine.split(" ", 2);
        if (eventInputs.length < 2) {
            throw new NumberFormatException();
        }
        if (!userLine.contains("/at")) {
            throw new EventException();
        }
        return eventInputs[1].split(" /at ");
    }

    /**
     * Returns the date & time as a LocalDateTime type from string input by user.
     * @param userDateTimeInput is the date & time input by user of String type
     * @return date & time input by user as a LocalDateTime type
     * @throws DateTimeParseException when date & time are input in the wrong format
     */
    public static LocalDateTime parseDateTime(String userDateTimeInput) throws DateTimeParseException {
        return LocalDateTime.parse(userDateTimeInput, DateTimeFormatter.ofPattern("[dd/MM/yyyy HHmm][dd-MM-yyyy HHmm]"));
    }

    /**
     * Returns the date & time as a LocalDateTime type from string read from saved text file.
     * @param storedDateTime is the date & time saved in text file from previous use of bot
     * @return date & time from text file as a LocalDateTime type
     * @throws DateTimeParseException when date & time are saved in the wrong format
     */
    public static LocalDateTime parseStoredDateTime(String storedDateTime) throws DateTimeParseException {
        return LocalDateTime.parse(storedDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Returns the keyword user is finding for when searching for tasks.
     * @param userLine input given by user
     * @return keyword extracted from user line
     */
    public static String parseFindTaskKey(String userLine) {
        String[] userFindInputs = userLine.split(" ", 2);
        return userFindInputs[1];
    }

}
