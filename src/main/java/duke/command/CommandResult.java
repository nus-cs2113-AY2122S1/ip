package duke.command;

/**
 * Contains the command from the user and the result after its execution.
 */
public class CommandResult {
    public static final String EXECUTION_SUCCESS = "S";
    public static final String EXECUTION_FAIL = "F";

    public static final String BLANK_DESCRIPTION = "0";
    public static final String INVALID_TODO = "1";
    public static final String INVALID_DEADLINE = "2";
    public static final String INVALID_EVENT = "3";
    public static final String INVALID_TASK_ALREADY_DONE = "4";
    public static final String INVALID_NUMBER = "5";
    public static final String INVALID_ADD_TASK = "6";
    public static final String INVALID_COMMAND = "7";
    
    private Command command;
    private String commandResult;
    private String resultDescription;

    /**
     * Constructor of this CommandResult object, initializing the command 
     * retrieved from the user, the result after the execution, and description
     * of the result (if any).
     * 
     * @param command command input from the user.
     * @param commandResult result of the command after execution.
     * @param resultDescription description of the result.
     */
    public CommandResult(Command command, String commandResult, String resultDescription) {
        this.command = command;
        this.commandResult = commandResult;
        this.resultDescription = resultDescription;
    }

    /**
     * Return the original command of this command result.
     * 
     * @return the command of this result.
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Return the result from the command.
     * 
     * @return the command result.
     */
    public String getResult() {
        return commandResult;
    }

    /**
     * Return the description of the result.
     * 
     * @return the result description.
     */
    public String getResultDescription() {
        return resultDescription;
    }
}
