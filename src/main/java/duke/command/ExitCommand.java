package duke.command;

public class ExitCommand implements Command {
    public static final CommandType type = CommandType.EXIT;

    @Override
    public void run() {
        System.out.println("Exited Duke. See you later.");
    }

    @Override
    public CommandType getType() {
        return type;
    }
}
