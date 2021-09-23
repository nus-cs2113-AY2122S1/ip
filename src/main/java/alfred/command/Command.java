package alfred.command;

import alfred.task.TaskList;

public abstract class Command {
    protected TaskList taskList;

    /**
     * This method sets the context for Commands to take place
     * @param taskList TaskList to be passed in from main execution
     */
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * This method is the execution command for subclasses of Command
     */
    public abstract void execute();

    /**
     * This method checks if the parsed Command is an ExitAppCommand for program termination.
     * @return boolean This returns true if parsed Command is an ExitAppCommand
     */
    public boolean isExit() {
        return this instanceof ExitAppCommand;
    }
}
