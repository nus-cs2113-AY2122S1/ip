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
        Command.CommandType cmdType = Command.getCommandTypebyStr(commandStr);
        return new Command(cmdType);
    }
}
