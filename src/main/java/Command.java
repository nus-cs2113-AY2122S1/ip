public abstract class Command {
    protected static boolean isExit = false;

    public void hasExecutedExitCommand() {
        isExit = true;
    }

    public static boolean getIsExit() {
        return isExit;
    }

    public abstract void executeCommand(TaskManager taskManager) throws DukeException;
}
