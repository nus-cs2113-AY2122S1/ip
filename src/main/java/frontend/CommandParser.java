package frontend;

import java.util.Scanner;

import backend.Command;

/*
 * Singleton class
 */
public class CommandParser {
    private static final Scanner scan = new Scanner(System.in);
    private static CommandParser commandParser = null;

    /**
     * Constructor
     */
    private CommandParser() {}

    /**
     * Singleton return
     */
    public static CommandParser getCommandParser() {
        if (commandParser == null) {
            commandParser = new CommandParser();
        }
        return commandParser;
    }


    /**
     * Parses input one line at a time and return a command object
     * This object can then be sent to backend to handle
     */
    public Command parseNextCommand() {
        String commandStr = scan.nextLine();
        final String[] commandTypeStrs = Command.getCommandTypeStrs();

        for (int i = 0; i < commandTypeStrs.length; i++) {
            if (commandStr.toLowerCase().equals(commandTypeStrs[i])) {
                return new Command(Command.CommandType.getCommanTypebyIndex(i));
            }
        }
        return new Command(Command.CommandType.INVALID);
    }
}
