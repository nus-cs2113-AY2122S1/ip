import command.Command;
import command.CommandHandler;
import command.CommandType;
import parser.CommandParser;


public class Duke {
    static final String LOGO =
        " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
    static final String GREETING =
        "____________________________________________________________\n"
        + "Hello! I'm Duke\n"
        + "What can I do for you?\n";

    /** Main */
    public static void main(String[] args) {
        System.out.println("Hello from\n" + LOGO + GREETING);

        CommandHandler commandHandler = new CommandHandler();
        CommandParser commandParser = CommandParser.getCommandParser();

        while (true) {
            Command cmd = commandParser.parseNextCommand();
            commandHandler.handlerCommand(cmd);
            if (cmd.isType(CommandType.BYE)) {
                return;
            }
        }
    }
}
