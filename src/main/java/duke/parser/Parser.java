package duke.parser;

import duke.commands.Command;
import duke.commands.ListCommand;
import duke.commands.MarkDoneCommand;
import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.commands.DeleteCommand;
import duke.commands.ScheduleCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.PurgeCommand;
import duke.commands.ByeCommand;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.Deadline;
import duke.tasks.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parser class handles all parsing of information.
 * Information can be in the form of user input or data from file.
 * After parsing, it will execute commands accordingly
 */
public class Parser {
    /**
     * Split the full user input and returns the command.
     *
     * @param userInput
     * @return command
     */
    public static String getCommand(String userInput) {
        String[] splitUserInput = userInput.trim().split("\\s+", 2);
        return splitUserInput[0];
    }

    /**
     * Split the full user input and returns the arguments.
     *
     * @param userInput
     * @return arguments
     */
    public static String getArguments(String userInput) {
        String[] splitUserInput = userInput.trim().split("\\s+", 2);
        if (splitUserInput.length > 1) {
            return splitUserInput[1];
        }
        return "";
    }

    /**
     * Used when adding Event & Deadline Tasks to split command using specified delimiter.
     *
     * @param delimiter Delimiter to look for to split.
     * @param arguments Arguments of the command in execution.
     * @return Array of the split values for easier processing.
     */
    public static String[] splitByDelimiter(String delimiter, String arguments) {
        String[] splitValues = new String[2];
        int indexOfDelimiter = arguments.indexOf(delimiter);
        splitValues[0] = arguments.substring(0, indexOfDelimiter).trim();
        splitValues[1] = arguments.substring(indexOfDelimiter + delimiter.length(), arguments.length()).trim();
        return splitValues;
    }

    /**
     * Parse the command based on user input and executes it
     *
     * @param userInput Input from user
     * @throws DukeException Invalid command
     */
    public static Command parseCommand(String userInput) throws DukeException {
        String command = getCommand(userInput);
        String arguments = getArguments(userInput);

        switch (command) {
        case "list":
            return new ListCommand(command);
        case "done":
            return new MarkDoneCommand(command, arguments);
        case "todo":
            return new AddTodoCommand(command, arguments);
        case "deadline":
            return new AddDeadlineCommand(command, arguments);
        case "event":
            return new AddEventCommand(command, arguments);
        case "delete":
            return new DeleteCommand(command, arguments);
        case "schedule":
            return new ScheduleCommand(command, arguments);
        case "find":
            return new FindCommand(command, arguments);
        case "help":
            return new HelpCommand(command);
        case "purge":
            return new PurgeCommand(command);
        case "bye":
            return new ByeCommand(command);
        default:
            throw new DukeException();
        }
    }

    /**
     * Process data from file for purpose of loading tasks from storage.
     *
     * @param taskDetails Details of task from file
     * @return Task object for writing into TaskList
     * @throws DukeException Invalid task
     */
    public static Task parseFile(String taskDetails) throws DukeException {
        String taskType = getTaskType(taskDetails);
        Boolean taskStatus = getTaskStatus(taskDetails);
        String description = getTaskDescription(taskDetails);
        String date = getTaskDate(taskDetails);

        Task task;
        LocalDateTime dateTime;
        switch (taskType) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            dateTime = LocalDateTime.parse(date);
            task = new Deadline(description, dateTime);
            break;
        case "E":
            dateTime = LocalDateTime.parse(date);
            task = new Event(description, dateTime);
            break;
        default:
            throw new DukeException();
        }
        if (taskStatus) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Parse data of task from file to get task type
     *
     * @param taskDetails Detail of task from file
     * @return Type of task
     */
    private static String getTaskType(String taskDetails) {
        String[] splitTaskDetails = taskDetails.split("\\|");
        String taskType = splitTaskDetails[0];
        return taskType;
    }

    /**
     * Parse data of task from file to get task status
     *
     * @param taskDetails Detail of task from file
     * @return Status of task
     */
    private static Boolean getTaskStatus(String taskDetails) {
        String[] splitTaskDetails = taskDetails.split("\\|");
        Boolean taskStatus = false;
        if (splitTaskDetails[1].equals("true")) {
            taskStatus = true;
        }
        return taskStatus;
    }

    /**
     * Parse data of task from file to get task description
     *
     * @param taskDetails Detail of task from file
     * @return Description of task
     */
    private static String getTaskDescription(String taskDetails) {
        String[] splitTaskDetails = taskDetails.split("\\|");
        String description = splitTaskDetails[2];
        return description;
    }

    /**
     * Parse data of task from file to get task date
     *
     * @param taskDetails Detail of task from file
     * @return Date of task
     */
    private static String getTaskDate(String taskDetails) {
        String[] splitTaskDetails = taskDetails.split("\\|");
        String date = "";
        if (splitTaskDetails.length > 3) {
            date = splitTaskDetails[3];
        }
        return date;
    }

    /**
     * Parse user input of date time into specific format of yyyy-MM-dd HH:mm.
     * Used by event and deadline tasks.
     *
     * @param date Date given by user
     * @return LocalDateTime object
     */
    public static LocalDateTime parseDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        return dateTime;
    }

    /**
     * Converts the LocalDateTime object into a more reader friendly format.
     *
     * @param dateTime Date parsed
     * @return Reader friendly string format of datetime
     */
    public static String getFormattedDateTime(LocalDateTime dateTime) {
        String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return formattedDateTime;
    }

    /**
     * Parse user input of date into specific format of yyyy-MM-dd.
     * Used by schedule command.
     *
     * @param date Date given by user
     * @return LocalDate object
     */
    public static LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(date, formatter);
        return parsedDate;
    }

    /**
     * Converts the LocalDate object into a more reader friendly format
     *
     * @param date Date parsed
     * @return Reader friendly string format of date
     */
    public static String getFormattedDate(LocalDate date) {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return formattedDate;
    }
}
