package parser;

import java.util.Scanner;

import command.Command;
import command.CommandType;
import time.Time;

/*
 * Singleton class
 * parse commandType, Time, taskContent
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
     */
    public Command parseNextCommand() {

        String cmdStr = scan.nextLine();
        cmdStr = cmdStr.toLowerCase();

        // First get command type
        CommandType cmdType = parseCommandType(cmdStr);

        // remove command string
        cmdStr = cmdStr.replace(CommandType.getCommandStrbyType(cmdType), "");
        cmdStr = cmdStr.strip();

        // then get time
        Time timeInfo = parseTimeInfo(cmdStr);

        // remove time?

        // Lastly get content
        String taskContent = parseTaskContent(cmdStr);

        return new Command(cmdType, timeInfo, taskContent);
    }

    private CommandType parseCommandType(String cmdStr) {
        CommandType cmdType = CommandType.INVALID;
        String[] words = cmdStr.split(" ");
        for (String word : words) {
            if (CommandType.isValidCommandStr(word)) {
                cmdType = CommandType.getCommandTypebyStr(word);
                break;
            }
        }
        return cmdType;
    }

    private Time parseTimeInfo(String cmdStr) {
        return new Time();
    }

    private String parseTaskContent(String cmdStr) {
        return cmdStr;
    }
}
