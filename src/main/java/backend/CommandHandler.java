package backend;

public class CommandHandler {
    public CommandHandler() {}

    /**
     * Handle command
     * @param cmd
     */
    public void handlerCommand(Command cmd) {
        Command.CommandType cmdType = cmd.getCommandType();
        System.out.println("____________________________________________________________");

        if (cmdType == Command.CommandType.LIST) {
            handleCommandList();
        } else if (cmdType == Command.CommandType.ADD) {
            handleCommandAdd();
        } else if (cmdType == Command.CommandType.DELETE) {
            handleCommandDelete();
        } else if (cmdType == Command.CommandType.FIND) {
            handleCommandFind();
        } else if (cmdType == Command.CommandType.INVALID) {
            handleCommandInvalid();
        } else {
            handleCommandInvalid();
        }
        System.out.println("____________________________________________________________");
    }

    private void handleCommandList() {
        System.out.println("list");
    }

    private void handleCommandAdd() {
        System.out.println("add");
    }

    private void handleCommandDelete() {
        System.out.println("delete");
    }

    private void handleCommandFind() {
        System.out.println("find");
    }

    private void handleCommandInvalid() {
        System.out.println("Invalid Command Received! Have an exception handler here later?");
    }
}
