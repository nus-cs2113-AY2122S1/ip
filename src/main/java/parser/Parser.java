package parser;

import TextUi.TextUi;
import commands.*;


public class Parser {
    public Command parseCommand(String userInput){
        String[] commandTypeAndParams =  splitCommandWordAndArgs(userInput);
        String commandType = commandTypeAndParams[0];
        String commandParams = commandTypeAndParams[1];
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

    public static String[] splitDeadlineDescriptionAndDeadline (String commandParams) {
        return commandParams.split("/by", 2);
    }

    public static String[] splitEventDescriptionAndTimeRange(String commandParams) {
        return commandParams.split("/at", 2);
    }
}
