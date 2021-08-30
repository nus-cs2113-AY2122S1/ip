package command;

import time.Time;
import task.TaskType;

public class Command {

    private CommandType cmdType;
    private Time taskTimeInfo;
    private TaskType taskType;
    private String taskDescription;

    /**
     * Constructor
     */
    public Command() {
        this.cmdType = CommandType.INVALID;
        this.taskType = TaskType.INVALID;
        this.taskTimeInfo = new Time();
        this.taskDescription = "";
    }

    public CommandType getCommandType() {
        return cmdType;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public Time getTimeInfo() {
        return taskTimeInfo;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setCommandType(CommandType cmdType) {
        this.cmdType = cmdType;
        return;
    }

    public void setTaskDescription(String description) {
        this.taskDescription = description;
        return;
    }

    public void appendTaskDescription(String description) {
        this.taskDescription += description;
        return;
    }

    public void setTimeInfo(Time time) {
        this.taskTimeInfo = time;
        return;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
        return;
    }

    public Boolean isType(CommandType cmdType) {
        return (this.cmdType == cmdType);
    }



}
