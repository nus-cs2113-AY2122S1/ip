package duke.parser;

import duke.CommonFormat;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    final static String MESSAGE_INVALID_FORMAT = "Error: Incorrect format detected.\n%s";
    final static String MESSAGE_INVALID_TASK_NUMBER = "Error: Invalid Task Number.";
    final static String MESSAGE_INVALID_COMMAND = "Error: Command not found.";
    final static String MESSAGE_INVALID_DATE = "Error: Incorrect date time format. Format: "
            + CommonFormat.FORMAT_DATETIME;


    public Parser() {

    }

    /**
     * Function that maps user command to its respective functionality. Upon a successful valid command, a command
     * object will be return. As for an invalid command, an exception will be thrown containing an error message
     * regarding the issues of the given command.
     *
     * @param userInput Raw user inputs from scanner
     * @return Command object
     * @throws ParserException Error regarding the validness of existing commands
     */
    public Command parseCommand(String userInput) throws ParserException {
        Command command = null;
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

    /**
     * Method to get the base command from the user inputs. Base command are the first word of the user inputs.
     *
     * @param userInput Raw user inputs from scanner
     * @return The first word of userInput separated by a space
     */
    private String getCommonCommand(String userInput) {
        return userInput.split(" ")[0];
    }

    /**
     * Get arguments for the base command.
     *
     * @param userInput Raw user inputs from scanner
     * @return An edited userInput excluding the first word in it
     */
    private String getCommandData(String userInput) {
        String[] payload = userInput.split(" ");
        payload[0] = "";
        return String.join(" ", payload).trim();
    }

    /**
     * Get a number from user inputs after the base command. This number represents the task number in which some
     * commands need to reference from.
     *
     * @param userInput Raw user inputs from scanner
     * @return An integer number that represents the task number in the list
     * @throws ParserException Error regarding invalid non-numeric inputs
     */
    private int getTaskIndex(String userInput) throws ParserException {
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

    /**
     * Method to check the validation and perform the todo command.
     *
     * @param userInput Raw user inputs from scanner
     * @return Valid todo command
     * @throws ParserException Error regarding the validness of the todo command format
     */
    private Command executeTodoCommand(String userInput) throws ParserException {
        String errorMessage = String.format(MESSAGE_INVALID_FORMAT, TodoCommand.MESSAGE_FORMAT);
        String arguments = getCommandData(userInput);
        if (isStringEmpty(arguments)) {
            throw new ParserException(errorMessage);
        }
        return new TodoCommand(arguments);
    }

    /**
     * Method to check the validation and perform the deadline command.
     *
     * @param userInput Raw user inputs from scanner
     * @return Valid deadline command
     * @throws ParserException Error regarding the validness of the deadline command format
     */
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
        } else if (!isValidTimeFormat(argumentArray[1])) {
            throw new ParserException(MESSAGE_INVALID_DATE);
        }
        return new DeadlineCommand(argumentArray);
    }

    /**
     * Method to check the validation and perform the event command.
     *
     * @param userInput Raw user inputs from scanner
     * @return Valid event command
     * @throws ParserException Error regarding the validness of the event command format
     */
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
        } else if (!isValidTimeFormat(argumentArray[1])) {
            throw new ParserException(MESSAGE_INVALID_DATE);
        }
        return new EventCommand(argumentArray);
    }

    private boolean isValidTimeFormat(String s) {
        boolean isValid = true;
        s = s.trim();
        try {
            LocalDateTime date = LocalDateTime.parse(s, CommonFormat.formatter);
        } catch (DateTimeParseException e) {
            isValid = false;
        }
        return isValid;
    }
}
