package command;

import task.TaskBase;
import task.TaskFactory;
import task.TaskManager;

public class CommandHandler {

    private TaskManager taskManager;
    private TaskFactory taskFactory;

    /**
     * Constructor to create taskManager and taskFactory
     **/
    public CommandHandler() {
        taskManager = new TaskManager();
        taskFactory = new TaskFactory();
    }

    /**
     * Handle command
     * @param cmd
     */
    public void handlerCommand(Command cmd) {
        CommandType cmdType = cmd.getCommandType();
        System.out.println("____________________________________________________________");

        if (cmdType == CommandType.LIST) {
            handleCommandList();
        } else if (cmdType == CommandType.ADD) {
            handleCommandAdd(cmd);
        } else if (cmdType == CommandType.DELETE) {
            handleCommandDelete();
        } else if (cmdType == CommandType.FIND) {
            handleCommandFind();
        } else if (cmdType == CommandType.BYE) {
            handleCommandBye();
        } else if (cmdType == CommandType.INVALID) {
            handleCommandInvalid();
        } else {
            handleCommandInvalid();
        }

        System.out.println("____________________________________________________________");
    }

    private void handleCommandList() {
        System.out.println("list");
        System.out.println("---------------");
        taskManager.listAllTasks();
    }

    private void handleCommandAdd(Command cmd) {
        System.out.println("added: " + cmd.getTaskContent());
        TaskBase task = taskFactory.makeTask(cmd);
        taskManager.addTask(task);
    }

    private void handleCommandDelete() {
        System.out.println("delete");
    }

    private void handleCommandFind() {
        System.out.println("find");
    }

    private void handleCommandBye() {
        System.out.println("Bye bye");
    }

    private void handleCommandInvalid() {
        System.out.println("Invalid Command Received! Have an exception handler here later?");
    }
}
