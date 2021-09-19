public class ListCommand implements Command {
    public static final CommandType type = CommandType.LIST;

    @Override
    public void run() {
        TaskManager.listTasks();
    }

    @Override
    public CommandType getType() {
        return type;
    }
}
