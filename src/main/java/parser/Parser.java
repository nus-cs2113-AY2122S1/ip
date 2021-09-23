package parser;

import command.*;
import exceptions.DukeException;

public class Parser {

    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_EXIT = "bye";
    public static final String COMMAND_DELETE = "delete";
    private static final int DATE_BUFFER = 3;
    public static final String PREFIX_BY = "/by";
    public static final String PREFIX_AT = "/at";
    public static final String ERROR_MISSING_PARAM_MESSAGE = "the description of a todo cannot be empty.";
    public static final String ERROR_INVALID_COMMAND_MESSAGE = "sorry, I didn't understand that command";

    /**
     * Parses raw user input passed into commands to execute and if possible, date and description format for
     * deadlines and events.
     *
     * @param userInput raw user input from standard input
     * @return executable type Command object
     * @throws DukeException error for either an invalid command or missing parameters for a todo task
     */
    public Command parseInput(String userInput) throws DukeException {
        int positionOfSpace = userInput.indexOf(" ");
        String commandWord = positionOfSpace > 0 ? userInput.substring(0, positionOfSpace).strip() : userInput;

        switch(commandWord) {
        case COMMAND_TODO:
        case COMMAND_DEADLINE:
        case COMMAND_EVENT:
            return parseAddCommand(commandWord, userInput.substring(positionOfSpace + 1));
        case COMMAND_LIST:
            return new ListCommand();
        case COMMAND_DONE:
            int doneIndex = Integer.parseInt(userInput.substring(positionOfSpace + 1)) - 1;
            return new DoneCommand(doneIndex);
        case COMMAND_DELETE:
            int deleteIndex = Integer.parseInt(userInput.substring(positionOfSpace + 1)) - 1;
            return new DeleteCommand(deleteIndex);
        case COMMAND_EXIT:
            return new ExitCommand();
        default:
            throw new DukeException(ERROR_INVALID_COMMAND_MESSAGE);
        }
    }

    /**
     * Filters either todo, deadline or event commands to determine the task to be added and if required, the date
     * needed for the deadline or event to create an AddCommand object.
     *
     * @param commandWord one of: "todo", "deadline" or "event", used to determine what type of task is to be added
     * @param taskDescription the full description of the task, including (if any) a date
     * @return the executable AddCommand with relevant fields configured
     * @throws DukeException error for either missing description for todo command or an invalid command
     */
    private AddCommand parseAddCommand(String commandWord, String taskDescription) throws DukeException {
        String taskName;

        switch (commandWord) {
        case COMMAND_TODO:
            if (taskDescription.equals(COMMAND_TODO)) {
                throw new DukeException(ERROR_MISSING_PARAM_MESSAGE);
            }
            taskName = taskDescription;
            return new AddCommand(COMMAND_TODO, taskName);
        case COMMAND_DEADLINE:
            String deadlineDate = taskDescription.substring(taskDescription.indexOf(PREFIX_BY) + DATE_BUFFER).trim();
            taskName = taskDescription.substring(0, taskDescription.indexOf(PREFIX_BY)).trim();
            return new AddCommand(COMMAND_DEADLINE, taskName, deadlineDate);
        case COMMAND_EVENT:
            String eventDate = taskDescription.substring(taskDescription.indexOf(PREFIX_AT) + DATE_BUFFER).trim();
            taskName = taskDescription.substring(0, taskDescription.indexOf(PREFIX_AT)).trim();
            return new AddCommand(COMMAND_EVENT, taskName, eventDate);
        default:
            throw new DukeException(ERROR_INVALID_COMMAND_MESSAGE);
        }
    }

}
