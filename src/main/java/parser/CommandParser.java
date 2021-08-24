package parser;

import java.util.HashSet;
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
    private static final HashSet<String> validCommands =
        new HashSet<String>() {{
            add("list");
            add("add");
            add("delete");
            add("find");
            add("bye");
            add("invalid");
        }};

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

        CommandType cmdType = parseCommandType(cmdStr);
        Time timeInfo = parseTimeInfo(cmdStr);
        String taskContent = parseTaskContent(cmdStr);

        return new Command(cmdType, timeInfo, taskContent);
    }

    private CommandType parseCommandType(String cmdStr) {
        CommandType cmdType = CommandType.INVALID;
        String[] words = cmdStr.split(" ");
        for (String word : words) {
            word = word.toLowerCase();
            if (validCommands.contains(word)) {
                cmdType = Command.getCommandTypebyStr(word);
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
