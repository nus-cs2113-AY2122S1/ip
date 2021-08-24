package command;


import time.Time;

public class Command {



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
