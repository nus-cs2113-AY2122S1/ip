package duke.command;

public class Command {
    public static final String COMMAND_ADD_TODO = "todo";
    public static final String COMMAND_ADD_DEADLINE = "deadline";
    public static final String COMMAND_ADD_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_EXIT = "bye";
    
    private String commandType;
    private String commandDescription;
    
    public Command(String commandType, String commandDescription) {
        this.commandType = commandType;
        this.commandDescription = commandDescription;
    }
    
    public String getCommandType() {
        return commandType;
    }
    
    public String getCommandDescription() {
        return commandDescription;
    }
    
    public boolean isExitCommand() {
        return commandType.equals("bye");
    }
}
