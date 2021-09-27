package parser;

import commands.*;
import constants.Message;
import duke.DukeException;
import task.TaskType;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    private static final String TODO_FORMAT_ERROR = "Please type the todo in the format: 'todo (description)' :)";
    private static final String DEADLINE_FORMAT_ERROR = "Please type the deadline in the format:" +
            "'deadline (description) /by yyyy-MM-ddThh:mm' :)";
    private static final String EVENT_FORMAT_ERROR = "Please type the event in the format: \n" +
            Message.INDENTATION + "'event (description) /at yyyy-MM-ddThh:mm /to yyyy-MM-ddThh:mm' :)";
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

    public Command parseInput(String input) {
        String type = input.split(" ")[0].toLowerCase();
        switch(type) {
        case AddCommand.TODO_COMMAND:
            try {
                return prepareAddCommand(input,TaskType.TODO);

            } catch (StringIndexOutOfBoundsException error) {
                return new IncorrectCommand(TODO_FORMAT_ERROR);

            }
        case AddCommand.DEADLINE_COMMAND:
            try {
                return prepareAddCommand(input,TaskType.DEADLINE);

            } catch (StringIndexOutOfBoundsException error) {
                return new IncorrectCommand(DEADLINE_FORMAT_ERROR);

            }
        case AddCommand.EVENT_COMMAND:
            try {
                return prepareAddCommand(input,TaskType.EVENT);

            } catch (StringIndexOutOfBoundsException error) {
                return new IncorrectCommand(EVENT_FORMAT_ERROR);

            }
        case ListCommand.LIST_COMMAND:
            return new ListCommand();
        case DoneCommand.DONE_COMMAND:
            try {
                return prepareDoneCommand(input);

            } catch (DukeException error) {
                return new IncorrectCommand(Message.PROMPT_TASK_NUMBER);

            } catch (NumberFormatException error) {
                return new IncorrectCommand(Message.PROMPT_NUMBER);

            }
        case HelpCommand.HELP_COMMAND:
            return new HelpCommand();
        case DeleteCommand.DELETE_COMMAND:
            try {
                return prepareDeleteCommand(input);

            } catch (DukeException error) {
                return new IncorrectCommand(Message.PROMPT_TASK_NUMBER);

            } catch (NumberFormatException error) {
                return new IncorrectCommand(Message.PROMPT_NUMBER);

            }
        case ExitCommand.EXIT_COMMAND:
            return new ExitCommand();
        case FindCommand.FIND_COMMAND:
            try {
                return prepareFindCommand(input.strip().substring(FIND_STARTING_INDEX));

            } catch (DateTimeParseException error) {
                return new IncorrectCommand(DATE_FORMAT_ERROR);

            } catch (Exception error) {
                return new IncorrectCommand(FIND_FORMAT_ERROR);

            }

        default :
            return new IncorrectCommand(Message.TYPE_SUITABLE_COMMAND_MESSAGE);
        }
    }

    private Command prepareAddCommand(String input,TaskType type) throws StringIndexOutOfBoundsException {
        String description;
        String parsedOutput[];
        switch(type){
        case TODO:
            description = input.strip().substring(TODO_STARTING_INDEX);
            parsedOutput = new String[]{description};
            return new AddCommand(parsedOutput,TaskType.TODO);
        case EVENT:
            try {
                description = input.strip().substring(EVENT_STARTING_INDEX);
                parsedOutput = getParsedOutputForEvent(description);
                return new AddCommand(parsedOutput,TaskType.EVENT);

            } catch(ArrayIndexOutOfBoundsException error) {
                return new IncorrectCommand(EVENT_FORMAT_ERROR);

            }
        case DEADLINE:
            description = input.strip().substring(DEADLINE_STARTING_INDEX);
            parsedOutput = description.split(DEADLINE_DESCRIPTION_AND_DATE_SPLITTER);
            return new AddCommand(parsedOutput,TaskType.DEADLINE);
        default:
            return new IncorrectCommand(Message.DEFAULT_ERROR_MESSAGE);
        }
    }

    private String[] getParsedOutputForEvent(String description) throws ArrayIndexOutOfBoundsException{
        String[] parsedOutput;
        parsedOutput = new String[3];
        parsedOutput[0] = description.split(EVENT_DESCRIPTION_AND_DATE_TIME_SPLITTER)[0];
        parsedOutput[1] = description.split(EVENT_DESCRIPTION_AND_DATE_TIME_SPLITTER)[1].
                split(EVENT_FROM_AND_TO_DATE_TIME_SPLITTER)[0];
        parsedOutput[WITH_DATE] = description.split(EVENT_DESCRIPTION_AND_DATE_TIME_SPLITTER)[1].
                split(EVENT_FROM_AND_TO_DATE_TIME_SPLITTER)[1];
        return parsedOutput;
    }

    private FindCommand prepareFindCommand(String input) throws IndexOutOfBoundsException{
        String[] parsedOutput = input.split(" ");
        if(parsedOutput[0].equals("/d")) {
            return new FindCommand(LocalDate.parse(parsedOutput[1]));
        } else {
            return new FindCommand(parsedOutput[0]);
        }
    }

    private DoneCommand prepareDoneCommand(String input) throws DukeException, NumberFormatException{
        if(input.split(" ").length < EXPECTED_LENGTH_FOR_DONE_INPUT) {
            throw new DukeException();
        }
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        return new DoneCommand(index);
    }

    private DeleteCommand prepareDeleteCommand(String input) throws DukeException, NumberFormatException {
        if(input.split(" ").length < EXPECTED_LENGTH_FOR_DELETE_INPUT) {
            throw new DukeException();
        }
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        return new DeleteCommand(index);
    }
}
