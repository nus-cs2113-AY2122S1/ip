package parser;

import commands.*;
import task.TaskType;

public class Parser {

    private static final String DEADLINE_DESCRIPTION_AND_DATE_SPLITTER = " /by ";
    private static final String EVENT_DESCRIPTION_AND_DATE_TIME_SPLITTER = " /at ";
    private static final int TODO_STARTING_INDEX = 5;
    private static final int DEADLINE_STARTING_INDEX = 9;
    private static final int EVENT_STARTING_INDEX = 6;

    public Command parseInputForDifferentTask(String input) {
        String type = input.split(" ")[0].toLowerCase();
        switch(type) {
        case "todo":
            return prepareAddCommand(input,TaskType.TODO);
        case "deadline":
            return prepareAddCommand(input,TaskType.DEADLINE);
        case "event":
            return prepareAddCommand(input,TaskType.EVENT);
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
            return new IncorrectCommand();
        }
    }

    private Command prepareAddCommand(String input,TaskType type) {
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
            return new IncorrectCommand();
        }
    }
}
