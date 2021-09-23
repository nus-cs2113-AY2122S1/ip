package duke.parser;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

public class Parser {

    final static String MESSAGE_INVALID_FORMAT = "Error: Incorrect format detected.\n%s";
    final static String MESSAGE_INVALID_TASK_NUMBER = "Error: Invalid Task Number.";
    final static String MESSAGE_INVALID_COMMAND = "Error: Command not found.";


    public Parser() {

    }

    public Command parseCommand(String userInput) throws ParserException {
        Command command;
        switch (getCommonCommand(userInput)) {
        case ListCommand.COMMAND_WORD:
            command = new ListCommand();
            break;
        case ByeCommand.COMMAND_WORD:
            command = new ByeCommand();
            break;
        case DoneCommand.COMMAND_WORD:
            command = new DoneCommand(getTaskIndex(userInput));
            break;
        case DeleteCommand.COMMAND_WORD:
            command = new DeleteCommand(getTaskIndex(userInput));
            break;
        case TodoCommand.COMMAND_WORD:
            command = executeTodoCommand(userInput);
            break;
        case DeadlineCommand.COMMAND_WORD:
            command = executeDeadlineCommand(userInput);
            break;
        case EventCommand.COMMAND_WORD:
            command = executeEventCommand(userInput);
            break;
        default:
            throw new ParserException(MESSAGE_INVALID_COMMAND);
        }
        return command;
    }

    private String getCommonCommand(String userInput) {
        return userInput.split(" ")[0];
    }

    private String getCommandData(String userInput) {
        String[] payload = userInput.split(" ");
        payload[0] = "";
        return String.join(" ", payload).trim();
    }

    private int getTaskIndex(String userInput) throws ParserException{
        int result = -1;
        try {
            result = Integer.parseInt(userInput.split(" ")[1]);
        } catch (Exception e) {
            throw new ParserException(MESSAGE_INVALID_TASK_NUMBER);
        }
        return result;
    }

    private static boolean isStringEmpty(String s) {
        return (s == null || s.isEmpty());
    }

    private static boolean isAnyStringEmpty(String[] strings) {
        boolean isEmpty = false;
        for (String s : strings) {
            if (isStringEmpty(s.trim())) {
                isEmpty = true;
                break;
            }
        }
        return isEmpty;
    }

    private Command executeTodoCommand(String userInput) throws ParserException {
        String errorMessage = String.format(MESSAGE_INVALID_FORMAT, TodoCommand.MESSAGE_FORMAT);
        String arguments = getCommandData(userInput);
        if (isStringEmpty(arguments)) {
            throw new ParserException(errorMessage);
        }
        return new TodoCommand(arguments);
    }

    private Command executeDeadlineCommand(String userInput) throws ParserException {
        String errorMessage = String.format(MESSAGE_INVALID_FORMAT, DeadlineCommand.MESSAGE_FORMAT);
        String arguments = getCommandData(userInput);
        if (isStringEmpty(arguments)) {
            throw new ParserException(errorMessage);
        }
        String[] argumentArray = arguments.split(DeadlineCommand.TAG_DUE_DATE_DEADLINE);
        if (argumentArray.length < 2) {
            throw new ParserException(errorMessage);
        } else if (isAnyStringEmpty(argumentArray)) {
            throw new ParserException(errorMessage);
        }
        return new DeadlineCommand(argumentArray);
    }

    private Command executeEventCommand(String userInput) throws ParserException {
        String errorMessage = String.format(MESSAGE_INVALID_FORMAT, EventCommand.MESSAGE_FORMAT);
        String arguments = getCommandData(userInput);
        if (isStringEmpty(arguments)) {
            throw new ParserException(errorMessage);
        }
        String[] argumentArray = arguments.split(EventCommand.TAG_START_DATE_DEADLINE);
        if (argumentArray.length < 2) {
            throw new ParserException(errorMessage);
        } else if (isAnyStringEmpty(argumentArray)) {
            throw new ParserException(errorMessage);
        }
        return new EventCommand(argumentArray);
    }
}
