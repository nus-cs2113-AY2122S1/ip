package parser;

import java.util.Scanner;

import command.Command;
import command.CommandType;
import task.TaskType;
import time.Time;

/*
 * Singleton class
 * parse commandType, Time, TaskDescription
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
        String[] tokens = cmdStr.toLowerCase().split(" ");
        Command cmd = new Command();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            TokenType tokenType = TokenType.getTokenTypebyStr(token);
            if (tokenType == TokenType.TASK_TYPE) {
                cmd.setTaskType(parseTaskType(token));
            } else if (tokenType == TokenType.COMMAND_TYPE) {
                // TODO: Add handling to not set command types twice
                cmd.setCommandType(parseCommandType(token));
            } else if (tokenType == TokenType.TIME_TYPE) {
                // TODO: Assumption here is that after time token, the rest of
                // the string is all related to time
                String timeToken = new String("");
                for (int j = i; j < tokens.length; j++) {
                    timeToken += (tokens[j] + " ");
                }
                cmd.setTimeInfo(parseTimeInfo(timeToken));
                break; // end of parsing
            } else if (tokenType == TokenType.DESCRIPTION) {
                cmd.appendTaskDescription(token + " ");
            } else {
                System.out.println("[PARSER] Invalid token found from input! Token: " + token);
            }
        }

        // "add" command can be inferred, if task type is specified
        if (cmd.getTaskType() != TaskType.INVALID) {
            cmd.setCommandType(CommandType.ADD);
        }
        return cmd;
    }

    private CommandType parseCommandType(String token) {
        return CommandType.getCommandTypebyStr(token);
    }

    private Time parseTimeInfo(String cmdStr) {
        return new Time(cmdStr);
    }

    private TaskType parseTaskType(String cmdStr) {
        return TaskType.getTaskTypebyStr(cmdStr);
    }
}
