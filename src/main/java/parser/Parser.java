package parser;

import commands.*;
import errors.InvalidCommand;
import errors.InvalidFile;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.Date;

import static commands.CommandList.*;
import static commands.CommandList.WHATSON;

/**
 * The Parser class helps extract the user's command as well as the task description
 * and date if applicable.
 */

public class Parser {
    /**
     * Processes the user input into a Command Object.
     *
     * @param userInput Input provided by user.
     * @return Command object containing command, description and date (if command was deadline or event).
     * @throws InvalidCommand If a command does not exist.
     */
    public static Command processCommand(String userInput) throws InvalidCommand {
        // Splits user input by spaces
        String[] userInputSplitted = userInput.split("\\s+", 2);
        String command = userInputSplitted[0].toUpperCase();
        String description = "";
        String[] descriptionAndDate;

        // Ensure description exists
        if (userInputSplitted.length > 1) {
            description = userInputSplitted[1];
        }

        switch (command) {
        case LIST:
            return new ListCommand(command);
        case TODO:
            return new TodoCommand(command, description);
        case DEADLINE:
            descriptionAndDate = spiltString("/by", description);
            return new DeadlineCommand(command, descriptionAndDate[0], descriptionAndDate[1]);
        case EVENT:
            descriptionAndDate = spiltString("/at", description);
            return new EventCommand(command, descriptionAndDate[0], descriptionAndDate[1]);
        case DONE:
            return new DoneCommand(command, description);
        case DELETE:
            return new DeleteCommand(command, description);
        case HELP:
            return new HelpCommand(command);
        case BYE:
            return new ByeCommand(command);
        case FIND:
            return new FindCommand(command, description);
        case WHATSON:
            return new WhatsOnCommand(command, description);
        default:
            throw new InvalidCommand();
        }
    }

    /**
     * Splits the string by the delimiter provided.
     *
     * @param delimiter   Delimiter to split string by.
     * @param description String to be split.
     * @return Array of string of size 2 after string is spilt by the delimiter.
     */
    private static String[] spiltString(String delimiter, String description) {
        String[] returnValues = new String[2];
        int byIndex = description.indexOf(delimiter);
        if (byIndex == -1) {
            returnValues[0] = description;
        } else {
            String taskDescription = description.substring(0, byIndex).trim(); // Remove trailing spaces
            String date = description.substring(byIndex + delimiter.length()).trim(); // Remove leading spaces
            returnValues[0] = taskDescription;
            returnValues[1] = date;
        }
        return returnValues;
    }

    /**
     * Returns a Task object based on a given line from the data file.
     *
     * @param line   A line from the data file.
     * @return Task object containing the task's description.
     * @throws InvalidFile If the file is not in a correct format.
     */
    public static Task fileParser(String line) throws InvalidFile{
        String[] dataSplit = line.split("\\|"); // Split by |

        if (dataSplit.length < 3) { // Ensure that there should be at least 3 elements
            throw new InvalidFile();
        }

        String taskType = dataSplit[0];
        boolean taskCompleted = dataSplit[1].equals("true");
        String description = dataSplit[2];
        Date date = null;

        if (dataSplit.length > 3) { // There is a date
            date = DateParser.stringToDateTime(dataSplit[3]);
        }

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
            throw new InvalidFile();
        }
        if (taskCompleted) {
            task.markAsDone();
        }
        return task;
    }
}
