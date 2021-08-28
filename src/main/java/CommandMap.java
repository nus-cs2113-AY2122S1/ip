import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandMap {

    private final Map<String, Command> commands;

    private static final CommandMap COMMAND_MAP;

    private CommandMap() {
        commands = new HashMap<>();
    }

    public void executeCommand(UI ui, Unker unker, String cmdString) {
        Pattern cmdPattern = Pattern.compile("^(?<cmd>\\w+?)(?:\\s+(?<cmdData>.+))?+$");
        Matcher cmdMatcher = cmdPattern.matcher(cmdString);
        if (!cmdMatcher.matches()) {
            ui.printSection("Sorry, can ask something else? Unker don't know how help you.");
            return;
        }
        String cmdName = cmdMatcher.group("cmd");
        String cmdData = cmdMatcher.group("cmdData");

        Command cmd = commands.get(cmdName.toLowerCase());
        if (cmd != null) {
            cmd.execute(ui, unker, cmdData);
        } else {
            ui.printSection("Sorry, can ask something else? Unker don't know how help you.");
        }
    }

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
    }

}
