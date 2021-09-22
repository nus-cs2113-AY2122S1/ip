package unker.command;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import unker.ui.UI;
import unker.task.Unker;

public class CommandMap {

    private static final String COMMAND_SPLITTER_REGEX = "^(?<cmd>\\w+?)(?:\\s+(?<cmdData>.+))?+$";
    private final Map<String, Command> commands;

    private static final CommandMap COMMAND_MAP;

    private CommandMap() {
        commands = new HashMap<>();
    }

    /**
     * Finds and executes a command based on the user input.
     * 
     * @param ui The UI that executed the command
     * @param unker The task manager to update the tasks with
     * @param cmdString The command that the user sent
     */
    public void executeCommand(UI ui, Unker unker, String cmdString) throws InvalidCommandException {
        Pattern cmdPattern = Pattern.compile(COMMAND_SPLITTER_REGEX);
        Matcher cmdMatcher = cmdPattern.matcher(cmdString);
        if (!cmdMatcher.matches()) {
            throw new InvalidCommandException("Sorry, can ask something else? Unker don't know how help you.");
        }
        String cmdName = cmdMatcher.group("cmd");
        String cmdData = cmdMatcher.group("cmdData");

        Command cmd = commands.get(cmdName.toLowerCase());
        if (cmd != null) {
            cmd.execute(ui, unker, cmdData);
        } else {
            throw new InvalidCommandException("Sorry, can ask something else? Unker don't know how help you.");
        }
    }

    /**
     * Get a singleton instance of CommandMap
     * 
     * @return A singleton instance of CommandMap
     */
    public static CommandMap getCommandMapInstance() {
        return COMMAND_MAP;
    }

    static {
        COMMAND_MAP = new CommandMap();
        Map<String, Command> map = COMMAND_MAP.commands;
        map.put("bye", new ByeCommand());
        map.put("list", new ListCommand());
        map.put("done", new DoneCommand());
        map.put("todo", new ToDoCommand());
        map.put("deadline", new DeadlineCommand());
        map.put("event", new EventCommand());
        map.put("delete", new DeleteCommand());
        map.put("find", new FindCommand());
    }

}
