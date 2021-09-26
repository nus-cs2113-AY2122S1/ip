package parser;

import commands.*;
import constants.Message;
import task.TaskType;

public class Parser {

    private static final String DEADLINE_DESCRIPTION_AND_DATE_SPLITTER = " /by ";
    private static final String EVENT_DESCRIPTION_AND_DATE_TIME_SPLITTER = " /at ";
    private static final String FIND_FORMAT_ERROR = "Please type the find function in the format: " +
            "'find (description)' :)";
    private static final int FIND_STARTING_INDEX = 5;
    private static final int TODO_STARTING_INDEX = 5;
    private static final int DEADLINE_STARTING_INDEX = 9;
    private static final int EVENT_STARTING_INDEX = 6;

    public Command parseInputForDifferentTask(String input) {
        String type = input.split(" ")[0].toLowerCase();
        switch(type) {
        case "todo":
            try {
                return prepareAddCommand(input,TaskType.TODO);
            } catch (StringIndexOutOfBoundsException error) {
                return new IncorrectCommand(Message.PROMPT_TASK_DESCRIPTION);
            }
        case "deadline":
            try {
                return prepareAddCommand(input,TaskType.DEADLINE);
            } catch (StringIndexOutOfBoundsException error) {
                return new IncorrectCommand(Message.PROMPT_TASK_DESCRIPTION);
            }
        case "event":
            try {
                return prepareAddCommand(input,TaskType.EVENT);
            } catch (StringIndexOutOfBoundsException error) {
                return new IncorrectCommand(Message.PROMPT_TASK_DESCRIPTION);
            }
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(input.strip());
        case "help":
            return new HelpCommand();
        case "delete":
            return new DeleteCommand(input.strip());
        case "bye":
            return new ExitCommand();
        case "find":
            try {
                return new FindCommand(input.strip().substring(FIND_STARTING_INDEX));

            } catch (StringIndexOutOfBoundsException error) {
                return new IncorrectCommand(FIND_FORMAT_ERROR);

            }
        default :
            return new IncorrectCommand(Message.TYPE_SUITABLE_COMMAND_MESSAGE);
        }
    }

    private Command prepareAddCommand(String input,TaskType type) throws StringIndexOutOfBoundsException{
        String description;
        String parsedOutput[];
        switch(type){
        case TODO:
            description = input.strip().substring(TODO_STARTING_INDEX);
            parsedOutput = new String[]{description};
            return new AddCommand(parsedOutput,TaskType.TODO);
        case EVENT:
            description = input.strip().substring(EVENT_STARTING_INDEX);
            parsedOutput = description.split(EVENT_DESCRIPTION_AND_DATE_TIME_SPLITTER);
            return new AddCommand(parsedOutput,TaskType.EVENT);
        case DEADLINE:
            description = input.strip().substring(DEADLINE_STARTING_INDEX);
            parsedOutput = description.split(DEADLINE_DESCRIPTION_AND_DATE_SPLITTER);
            return new AddCommand(parsedOutput,TaskType.DEADLINE);
        default:
            return new IncorrectCommand(Message.DEFAULT_ERROR_MESSAGE);
        }
    }
}
