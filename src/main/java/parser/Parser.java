package parser;

import commands.*;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput){
        String[] commandTypeAndParams =  splitCommandWordAndArgs(userInput);
        String commandType = commandTypeAndParams[0];
        String commandParams = commandTypeAndParams[1].trim();
        Command command;
        switch (commandType) {
        case AddToDoCommand.COMMAND_WORD:
            command = new AddToDoCommand(commandParams);
            break;
        case AddDeadlineCommand.COMMAND_WORD:
            command = new AddDeadlineCommand(commandParams);
            break;
        case AddEventCommand.COMMAND_WORD:
            command = new AddEventCommand(commandParams);
            break;
        case MarkTaskAsDoneCommand.COMMAND_WORD:
            command = new MarkTaskAsDoneCommand(commandParams);
            break;
        case DeleteCommand.COMMAND_WORD:
            command = new DeleteCommand(commandParams);
            break;
        case FindCommand.COMMAND_WORD:
            command = new FindCommand(commandParams);
            break;
        case ExitCommand.COMMAND_WORD:
            command = new ExitCommand();
            break;
        case ListCommand.COMMAND_WORD:
            command = new ListCommand();
            break;
        case HelpCommand.COMMAND_WORD:
            command = new HelpCommand();
            break;
        default:
            command = new InvalidCommand();
            break;
        }
        return command;
    }

    private static String[] splitCommandWordAndArgs(String userInput) {
        final String[] split = userInput.trim().split(" ", 2);
        return split.length == 2 ? new String[] { split[0].toLowerCase(), split[1] } : new String[] { split[0].toLowerCase() , "" };
    }

    /**
     * Splits the parameters of the deadline command into the Description and Deadline
     *
     * @param commandParams parameters of the command
     * @return Array containing Deadline Description and Deadline
     */
    public static String[] splitDeadlineDescriptionAndDeadline (String commandParams) {
        return commandParams.split("/by", 2);
    }

    /**
     * Splits the parameters of the deadline command into the Description and Time Range
     * @param commandParams parameters of the command
     * @return Array containing Event Description and Time Range
     */
    public static String[] splitEventDescriptionAndTimeRange(String commandParams) {
        return commandParams.split("/at", 2);
    }
}
