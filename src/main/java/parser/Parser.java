package parser;

import commands.Command;
import commands.AddCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.IncorrectCommand;
import commands.DeleteCommand;
import commands.ListCommand;
import commands.HelpCommand;
import commands.DoneCommand;

import constants.Message;
import duke.DukeException;
import task.TaskType;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * A class for parsing the input to determine the command to execute
 */
public class Parser {

    private static final String TODO_FORMAT_ERROR = "Please type the todo in the format: 'todo (description)' :)";
    private static final String DATE_FORMAT_ERROR = "Please write the date in the format of 'yyyy-MM-dd' :)";
    private static final String DEADLINE_DESCRIPTION_AND_DATE_SPLITTER = " /by ";
    private static final String EVENT_DESCRIPTION_AND_DATE_TIME_SPLITTER = " /at ";
    private static final String FIND_FORMAT_ERROR = "Please type the find input in the format: \n" +
            Message.INDENTATION + "- 'find /d yyyy-MM-dd' to find tasks with a specific date \n" +
            Message.INDENTATION + "- 'find (description)' to find tasks with a specific description :)";
    private static final int EXPECTED_LENGTH_FOR_DELETE_INPUT = 2;
    private static final int EXPECTED_LENGTH_FOR_DONE_INPUT = 2;
    private static final int FIND_STARTING_INDEX = 5;
    private static final String EVENT_FROM_AND_TO_DATE_TIME_SPLITTER = " /to ";
    private static final int TODO_STARTING_INDEX = 5;
    private static final int DEADLINE_STARTING_INDEX = 9;
    private static final int EVENT_STARTING_INDEX = 6;
    public static final int WITH_DATE = 2;

    /**
     * Parses the input to determine the required Command object to be created.
     * The command returned depends on the first word of the input before the
     * first space. If the first word does not match any of the cases, a default
     * error message is returned with the prompt to type 'help'.
     *
     * @param input Input from user
     * @return Certain type of command depending on the input
     */
    public Command parseInput(String input) {
        String type = input.split(" ")[0].toLowerCase();
        switch(type) {
        case AddCommand.TODO_COMMAND:
            return executeTaskCase(input, TaskType.TODO, TODO_FORMAT_ERROR);
        case AddCommand.DEADLINE_COMMAND:
            return executeTaskCase(input, TaskType.DEADLINE, AddCommand.DEADLINE_FORMAT_ERROR);
        case AddCommand.EVENT_COMMAND:
            return executeTaskCase(input, TaskType.EVENT, AddCommand.EVENT_FORMAT_ERROR);
        case ListCommand.LIST_COMMAND:
            return new ListCommand();
        case DoneCommand.DONE_COMMAND:
            return executeDoneCase(input);
        case HelpCommand.HELP_COMMAND:
            return new HelpCommand();
        case DeleteCommand.DELETE_COMMAND:
            return executeDeleteCase(input);
        case ExitCommand.EXIT_COMMAND:
            return new ExitCommand();
        case FindCommand.FIND_COMMAND:
            return executeFindCase(input);
        default :
            return new IncorrectCommand(Message.TYPE_SUITABLE_COMMAND_MESSAGE);
        }
    }

    private Command executeFindCase(String input) {
        try {
            return prepareFindCommand(input.strip().substring(FIND_STARTING_INDEX));
        } catch (DateTimeParseException error) {
            return new IncorrectCommand(DATE_FORMAT_ERROR);
        } catch (Exception error) {
            return new IncorrectCommand(FIND_FORMAT_ERROR);
        }
    }

    private Command executeDeleteCase(String input) {
        try {
            return prepareDeleteCommand(input);
        } catch (DukeException error) {
            return new IncorrectCommand(Message.PROMPT_TASK_NUMBER);
        } catch (NumberFormatException error) {
            return new IncorrectCommand(Message.PROMPT_NUMBER);
        }
    }

    private Command executeDoneCase(String input) {
        try {
            return prepareDoneCommand(input);
        } catch (DukeException error) {
            return new IncorrectCommand(Message.PROMPT_TASK_NUMBER);
        } catch (NumberFormatException error) {
            return new IncorrectCommand(Message.PROMPT_NUMBER);
        }
    }

    private Command executeTaskCase(String input, TaskType type, String todoFormatError) {
        try {
            return prepareAddCommand(input, type);
        } catch (StringIndexOutOfBoundsException error) {
            return new IncorrectCommand(todoFormatError);
        }
    }

    /**
     * Parses the input further to create a different AddCommand Object for different task
     * types. Returns an error message if something unexpected happens.
     *
     * @param input Input from the user
     * @param type Type of task
     * @return AddCommand object with a different parsed Output depending on the type of task
     * @throws StringIndexOutOfBoundsException If the format of the input is incorrect
     */
    private Command prepareAddCommand(String input,TaskType type) throws StringIndexOutOfBoundsException {
        String description;
        String parsedOutputs[];
        switch(type){
        case TODO:
            description = input.strip().substring(TODO_STARTING_INDEX);
            parsedOutputs = new String[]{description};
            return new AddCommand(parsedOutputs,TaskType.TODO);
        case EVENT:
            try {
                description = input.strip().substring(EVENT_STARTING_INDEX);
                parsedOutputs = getParsedOutputForEvent(description);
                return new AddCommand(parsedOutputs,TaskType.EVENT);
            } catch(ArrayIndexOutOfBoundsException error) {
                return new IncorrectCommand(AddCommand.EVENT_FORMAT_ERROR);
            }
        case DEADLINE:
            description = input.strip().substring(DEADLINE_STARTING_INDEX);
            parsedOutputs = description.split(DEADLINE_DESCRIPTION_AND_DATE_SPLITTER);
            return new AddCommand(parsedOutputs,TaskType.DEADLINE);
        default:
            return new IncorrectCommand(Message.DEFAULT_ERROR_MESSAGE);
        }
    }

    private String[] getParsedOutputForEvent(String description) throws ArrayIndexOutOfBoundsException{
        String[] parsedOutputs = new String[3];
        parsedOutputs[0] = description.split(EVENT_DESCRIPTION_AND_DATE_TIME_SPLITTER)[0];
        parsedOutputs[1] = description.split(EVENT_DESCRIPTION_AND_DATE_TIME_SPLITTER)[1].
                split(EVENT_FROM_AND_TO_DATE_TIME_SPLITTER)[0];
        parsedOutputs[WITH_DATE] = description.split(EVENT_DESCRIPTION_AND_DATE_TIME_SPLITTER)[1].
                split(EVENT_FROM_AND_TO_DATE_TIME_SPLITTER)[1];
        return parsedOutputs;
    }

    /**
     * Parses the input further to create a different FindCommand object for finding task with
     * a specific description / date.
     *
     * @param input Parsed input from the user.
     * @return FindCommand object with a different parsed output depending on whether the
     * description / date needs to be found
     * @throws IndexOutOfBoundsException throws an exception when user inputs a number out
     * of the range of the ArrayList
     */
    private FindCommand prepareFindCommand(String input) throws IndexOutOfBoundsException{
        String[] parsedOutputs = input.split(" ");
        if(parsedOutputs[0].equals("/d")) {
            return new FindCommand(LocalDate.parse(parsedOutputs[1]));
        } else {
            return new FindCommand(parsedOutputs[0]);
        }
    }

    /**
     * Parses the input further to create a DoneCommand with the index of the task to be
     * marked as done.
     *
     * @param input Parsed input from the user.
     * @return DoneCommand object with the index of the completed task.
     * @throws DukeException throws an exception when the input from the user
     * is incorrect
     * @throws NumberFormatException throws an exception when the user inputs
     * something that is not an integer.
     */
    private DoneCommand prepareDoneCommand(String input) throws DukeException,
            NumberFormatException{
        if(input.split(" ").length < EXPECTED_LENGTH_FOR_DONE_INPUT) {
            throw new DukeException();
        }
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        return new DoneCommand(index);
    }

    /**
     * Parses the input further to create a DoneCommand with the index of the task to be
     * marked as done.
     *
     * @param input Parsed input from the user.
     * @return DoneCommand object with the index of the completed task.
     * @throws DukeException throws an exception when the input from the user
     * is incorrect
     * @throws NumberFormatException throws an exception when the user inputs
     * something that is not an integer.
     */
    private DeleteCommand prepareDeleteCommand(String input) throws DukeException,
            NumberFormatException {
        if(input.split(" ").length < EXPECTED_LENGTH_FOR_DELETE_INPUT) {
            throw new DukeException();
        }
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        return new DeleteCommand(index);
    }
}
