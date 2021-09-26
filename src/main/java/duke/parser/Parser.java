package duke.parser;

import duke.commands.Command;
import duke.commands.ListCommand;
import duke.commands.MarkDoneCommand;
import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.commands.DeleteCommand;
import duke.commands.FindCommand;
import duke.commands.ByeCommand;

import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.Deadline;
import duke.tasks.Event;

import duke.exceptions.DukeException;

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
        case "find":
            return new FindCommand(command, arguments);
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
        switch (taskType) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            task = new Deadline(description, date);
            break;
        case "E":
            task = new Event(description, date);
            break;
        default:
            throw new DukeException();
        }
        if (taskStatus) {
            task.markAsDone();
        }
        return task;
    }

    public static String getTaskType(String taskDetails) {
        String[] splitTaskDetails = taskDetails.split("\\|");
        String taskType = splitTaskDetails[0];
        return taskType;
    }

    public static Boolean getTaskStatus(String taskDetails) {
        String[] splitTaskDetails = taskDetails.split("\\|");
        Boolean taskStatus = false;
        if (splitTaskDetails[1].equals("true")) {
            taskStatus = true;
        }
        return taskStatus;
    }

    public static String getTaskDescription(String taskDetails) {
        String[] splitTaskDetails = taskDetails.split("\\|");
        String description = splitTaskDetails[2];
        return description;
    }

    public static String getTaskDate(String taskDetails) {
        String[] splitTaskDetails = taskDetails.split("\\|");
        String date = "";
        if (splitTaskDetails.length > 3) {
            date = splitTaskDetails[3];
        }
        return date;
    }


}
