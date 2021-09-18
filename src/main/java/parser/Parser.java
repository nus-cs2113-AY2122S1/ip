package parser;

import commands.*;
import errors.InvalidCommand;
import filter.FilterBy;

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
     * @return Command object containing command, description and date (if command was deadline or event)
     * @throws InvalidCommand If a command does not exist.
     */
    public Command processCommand(String userInput) throws InvalidCommand {
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
        //case WHATSON:
        //    return new FilterBy().Date(date);
        default:
            throw new InvalidCommand();
        }
    }

    /**
     * Splits the string by the delimiter provided
     *
     * @param delimiter   Delimiter to split string by
     * @param description String to be split
     * @return Array of string of size 2 after string is spilt by the delimiter
     */
    private String[] spiltString(String delimiter, String description) {
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
}
