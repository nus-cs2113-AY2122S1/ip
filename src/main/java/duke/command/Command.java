package duke.command;

/**
 * Contains the type and description of a user's command.
 */
public class Command {
    public static final String COMMAND_ADD_TODO = "todo";
    public static final String COMMAND_ADD_DEADLINE = "deadline";
    public static final String COMMAND_ADD_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_EXIT = "bye";
    public static final String COMMAND_FIND = "find";
    public static final String COMMAND_HELP = "help";
    
    
    private String commandType;
    private String commandDescription;

    /**
     * Constructor of this Command object, initializing the type of the command
     * and the command's description.
     * 
     * @param commandType the type of this command.
     * @param commandDescription the description of this command.
     */
    public Command(String commandType, String commandDescription) {
        this.commandType = commandType;
        this.commandDescription = commandDescription;
    }

    /**
     * Return the type of this command.
     * 
     * @return command type.
     */
    public String getCommandType() {
        return commandType;
    }

    /**
     * Return the description of this command.
     * 
     * @return command description.
     */
    public String getCommandDescription() {
        return commandDescription;
    }

    /**
     * Check whether this command is an exit command or not.
     * 
     * @return true if this is an exit command, false otherwise.
     */
    public boolean isExitCommand() {
        return commandType.equals("bye");
    }
}
