package duke.logic.parser;

import duke.logic.commands.AddDeadlineCommand;
import duke.logic.commands.AddEventCommand;
import duke.logic.commands.AddTodoCommand;
import duke.logic.commands.ByeCommand;
import duke.logic.commands.Command;
import duke.logic.commands.CommandListCommand;
import duke.logic.commands.DeleteTaskCommand;
import duke.logic.commands.IncorrectCommand;
import duke.logic.commands.ListCommand;
import duke.logic.commands.MarkTaskAsDoneCommand;
import duke.logic.commands.exceptions.InvalidCommandFormatException;
import duke.logic.commands.exceptions.MissingTaskDescriptionException;

import static duke.ui.Ui.EMPTY;

/**
 *
 * Method of parsing commands partially adapted from https://github.com/se-edu/addressbook-level2
 */
public class Parser {

     private static final String ERROR_MESSAGE_COMMAND_DOES_NOT_EXIST = "Command does not exist @_@";

    /**
     * Returns a String array where 0th index is command string and 1st index is the remaining parameters
     * Command string and parameter string is assumed to be separated by the first " " in input
     * If no parameters are provided in the input, 1st index will be set to EMPTY
     *
     * @param input Raw user input string
     * @return String array [command, parameters]
     */
    private String[] splitInputIntoCommandAndParams(String input) {
        String[] commandAndParams = new String[2];
        final String[] splitInput = input.trim().split(" ", 2);
        //command string
        commandAndParams[0] = splitInput[0];
        //param string, if not given, set to EMPTY for error handling
        commandAndParams[1] = (splitInput.length >= 2) ? splitInput[1] : EMPTY;
        return commandAndParams;
    }

    /**
     * Returns a String array where the 0th index is the task description and 1st index is the additional info (i.e date)
     * Description and info is assumed to be separated by the first "/" in input
     * If no description is provided, throw MissingTaskDescriptionException
     * If no additional info is provided, 1st index will be set to EMPTY
     *
     * @param params Params string intended to be returned from splitInputIntoCommandAndParams(),
     *               thus assumed to be from a valid command.
     * @return String array [description, info]
     */
    private String[] splitParamsIntoDescriptionAndInfo(String params) throws MissingTaskDescriptionException {
        final String[] splitParams = params.trim().split("/");
        String[] descriptionAndInfo = new String[2];
        //description string
        descriptionAndInfo[0] = splitParams[0].trim();
        if (descriptionAndInfo[0].equals(EMPTY)) {
            throw new MissingTaskDescriptionException();
        }
        //other info string, if not given, return EMPTY for error handling
        descriptionAndInfo[1] = (splitParams.length >= 2) ? splitParams[1].trim() : EMPTY;
        return descriptionAndInfo;
    }

    /**
     * Returns the date of the task in String form
     * Date is assumed to be after the command prefix strings "at" or "by"
     * If invalid command prefix is given or no date is provided, throw InvalidCommandFormatException
     *
     * @param commandPrefix Prefix to extract date with
     * @param info String containing prefix and date
     * @return Date in String form
     */
    public static String extractDate(String commandPrefix, String info) throws InvalidCommandFormatException {
        final String[] words = info.split(" ", 2);
        if (!words[0].equals(commandPrefix) || words.length == 1) {
            throw new InvalidCommandFormatException();
        } else {
            return words[1];
        }
    }

    private Command parseAddTodo (String params) {
        final String[] descriptionAndInfo;
        try {
            descriptionAndInfo = splitParamsIntoDescriptionAndInfo(params);
            final String description = descriptionAndInfo[0];
            return new AddTodoCommand(description);
        } catch (MissingTaskDescriptionException e) {
            return new IncorrectCommand(e.toString());
        }
    }

    private Command parseAddDeadline (String params) {
        final String[] descriptionAndInfo;
        try {
            descriptionAndInfo = splitParamsIntoDescriptionAndInfo(params);
            final String description = descriptionAndInfo[0];
            final String info = descriptionAndInfo[1];
            String date = extractDate(AddDeadlineCommand.COMMAND_PREFIX, info);
            return new AddDeadlineCommand(description, date);
        } catch (MissingTaskDescriptionException e) {
            return new IncorrectCommand(e.toString());
        } catch (InvalidCommandFormatException e) {
            return new IncorrectCommand(AddDeadlineCommand.MESSAGE_INVALID_FORMAT);
        }
    }

    private Command parseAddEvent (String params) {
        final String[] descriptionAndInfo;
        try {
            descriptionAndInfo = splitParamsIntoDescriptionAndInfo(params);
            final String description = descriptionAndInfo[0];
            final String info = descriptionAndInfo[1];
            String date = extractDate(AddEventCommand.COMMAND_PREFIX, info);
            return new AddEventCommand(description, date);
        } catch (MissingTaskDescriptionException e) {
            return new IncorrectCommand(e.toString());
        } catch (InvalidCommandFormatException e) {
            return new IncorrectCommand(AddEventCommand.MESSAGE_INVALID_FORMAT);
        }
    }

    private Command parseMarkTaskAsDone (String params) {
        try {
            int taskNum = Integer.parseInt(params);
            return new MarkTaskAsDoneCommand(taskNum);
        } catch (NumberFormatException e) {
            return new IncorrectCommand(MarkTaskAsDoneCommand.MESSAGE_INVALID_FORMAT);
        }
    }

    private Command parseDeleteTask (String params) {
        try {
            int taskNum = Integer.parseInt(params);
            return new DeleteTaskCommand(taskNum);
        } catch (NumberFormatException e) {
            return new IncorrectCommand(DeleteTaskCommand.MESSAGE_INVALID_FORMAT);
        }
    }

    /**
     * Returns the correct command to be executed depending on user input
     *
     * @param input Raw user input string
     */
    public Command parseCommand(String input) {
        final String[] commandAndParams = splitInputIntoCommandAndParams(input);
        final String commandWord = commandAndParams[0];
        final String params = commandAndParams[1];

        switch (commandWord) {
        case AddTodoCommand.COMMAND_WORD:
            return parseAddTodo(params);
        case AddDeadlineCommand.COMMAND_WORD:
            return parseAddDeadline(params);
        case AddEventCommand.COMMAND_WORD:
            return parseAddEvent(params);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkTaskAsDoneCommand.COMMAND_WORD:
            return parseMarkTaskAsDone(params);
        case DeleteTaskCommand.COMMAND_WORD:
            return parseDeleteTask(params);
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        case CommandListCommand.COMMAND_WORD:
            return new CommandListCommand();
        default:
            return new IncorrectCommand(ERROR_MESSAGE_COMMAND_DOES_NOT_EXIST);
        }
    }

}

