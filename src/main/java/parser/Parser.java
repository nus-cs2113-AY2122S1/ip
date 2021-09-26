package parser;

import commands.*;
import constants.Message;
import task.TaskType;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    private static final String TODO_FORMAT_ERROR = "Please type the todo in the format: 'todo (description)' :)";
    private static final String DEADLINE_FORMAT_ERROR = "Please type the deadline in the format:" +
            "'deadline (description) /by yyyy-MM-ddThh:mm' :)";
    private static final String EVENT_FORMAT_ERROR = "Please type the event in the format: " +
            "'event (description) /at yyyy-MM-ddThh:mm /to yyyy-MM-ddThh:mm' :)";
    private static final String DATE_FORMAT_ERROR = "Please write the date in the format of 'yyyy-MM-dd' :)";
    private static final String DEADLINE_DESCRIPTION_AND_DATE_SPLITTER = " /by ";
    private static final String EVENT_DESCRIPTION_AND_DATE_TIME_SPLITTER = " /at ";
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
            try {
                return prepareListCommand(input);

            } catch (DateTimeParseException error) {
                return new IncorrectCommand(DATE_FORMAT_ERROR);

            }
        case DoneCommand.DONE_COMMAND:
            return new DoneCommand(input);
        case HelpCommand.HELP_COMMAND:
            return new HelpCommand();
        case DeleteCommand.DELETE_COMMAND:
            return new DeleteCommand(input);
        case ExitCommand.EXIT_COMMAND:
            return new ExitCommand();
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

    private Command prepareListCommand(String input) throws DateTimeParseException {
        if(input.split(" ").length == WITH_DATE) {
            return new ListCommand(LocalDate.parse(input.split(" ")[1]));
        } else {
            return new ListCommand();
        }
    }
}
