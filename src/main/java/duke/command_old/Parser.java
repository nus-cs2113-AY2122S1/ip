package duke.command_old;

import duke.task.Task;

import java.util.ArrayList;

public class Parser {

    public static final String HELP = "help";
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String ADD_TODO = "todo";
    public static final String ADD_DEADLINE = "deadline";
    public static final String ADD_EVENT = "event";
    public static final String DELETE = "delete";
    public static final String BYE = "bye";
    public static final String EXIT = "exit";
    public static final String QUIT = "quit";
    public static final String GREETING = "hello";

    /**
     * Returns the command from the user's input.
     * @param input user input
     * @return command type, represented by an enumeration.
     */
    public CommandType parseCommand(String input) {
        String[] words = input.split(" ");
        String inputCommand = words[0];
        switch (inputCommand) {
        case LIST:
            return CommandType.LIST_TASKS;

        case DONE:
            return CommandType.MARK_DONE;

        case ADD_TODO:
            return CommandType.ADD_TODO;

        case ADD_DEADLINE:
            return CommandType.ADD_DEADLINE;

        case ADD_EVENT:
            return CommandType.ADD_EVENT;

        case DELETE:
            return CommandType.DELETE_TASK;

        case HELP:
            return CommandType.HELP;

        case GREETING:
            return CommandType.GREETING;

        case BYE:
            //Fallthrough
        case EXIT:
            //Fallthrough
        case QUIT:
            return CommandType.EXIT;

        default:
            return CommandType.DEFAULT;
        }
    }
}
