package parser;

import commands.AddCommand;
import commands.DoneCommand;
import commands.ListCommand;
import commands.IncorrectCommand;
import commands.HelpCommand;
import commands.ExitCommand;
import commands.DeleteCommand;
import commands.Command;

import constants.Message;
import task.TaskType;

/**
 * A class for parsing the input to determine the command to execute
 */
public class Parser {

    private static final String DEADLINE_DESCRIPTION_AND_DATE_SPLITTER = " /by ";
    private static final String EVENT_DESCRIPTION_AND_DATE_TIME_SPLITTER = " /at ";
    private static final int TODO_STARTING_INDEX = 5;
    private static final int DEADLINE_STARTING_INDEX = 9;
    private static final int EVENT_STARTING_INDEX = 6;

    /**
     * Parses the input to determine the required Command object to be created.
     * The command returned depends on the first word of the input before the
     * first space. If the first word does not match any of the cases, a default
     * error message is returned with the prompt to type 'help'.
     *
     * @param input Input from user
     * @return Certain type of command depending on the input
     */
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
            return new DoneCommand(input);
        case "help":
            return new HelpCommand();
        case "delete":
            return new DeleteCommand(input);
        case "bye":
            return new ExitCommand();
        default :
            return new IncorrectCommand(Message.TYPE_SUITABLE_COMMAND_MESSAGE);
        }
    }

    /**
     * Further parses the input to create a different AddCommand Object for different task
     * types. Returns an error message if something unexpected happens.
     *
     * @param input Input from the user
     * @param type Type of task
     * @return AddCommand object with a different parsed Output depending on the type of task
     * @throws StringIndexOutOfBoundsException If the format of the input is incorrect
     */
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
