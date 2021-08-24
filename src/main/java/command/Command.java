package command;

import java.util.HashMap;

import time.Time;

public class Command {

    private static final HashMap<String, CommandType> CommandStr2Type =
        new HashMap<String, CommandType>() {{
            put("list", CommandType.LIST);
            put("add", CommandType.ADD);
            put("delete", CommandType.DELETE);
            put("find", CommandType.FIND);
            put("bye", CommandType.BYE);
            put("invalid", CommandType.INVALID);
        }};

    private CommandType cmdType;
    private Time taskTimeInfo;
    private String taskContent;

    /**
     * Constructor
     * @param cmdType
     */
    public Command(CommandType cmdType, Time timeInfo, String taskContent) {
        this.cmdType = cmdType;
        this.taskTimeInfo = timeInfo;
        this.taskContent = taskContent;
        // System.out.print("Command received: ");
        // printCommandType(cmdType);
    }

    public static CommandType getCommandTypebyStr(String cmdStr) {
        return CommandStr2Type.get(cmdStr);
    }

    public CommandType getCommandType() {
        return cmdType;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public Time getTimeInfo() {
        return taskTimeInfo;
    }

    public Boolean isType(CommandType cmdType) {
        return (this.cmdType == cmdType);
    }



}
