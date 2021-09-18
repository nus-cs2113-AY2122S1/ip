package duke.command;

/**
 * Contains the result of an executed command.
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
    
    public CommandResult(Command command, String commandResult, String resultDescription) {
        this.command = command;
        this.commandResult = commandResult;
        this.resultDescription = resultDescription;
    }

    public Command getCommand() {
        return command;
    }
    
    public String getResult() {
        return commandResult;
    }
    
    public String getResultDescription() {
        return resultDescription;
    }
}
