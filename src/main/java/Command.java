/**
 * This class refactors the user command and
 * provides validation.
 *
 * @author richwill28
 */
public class Command {
    private String command;

    public Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public boolean isBye() {
        return command.equals("bye");
    }

    public boolean isList() {
        return command.equals("list");
    }

    public boolean isDone() {
        return command.split(" ")[0].equals("done");
    }

    public boolean isTodo() {
        return command.split(" ")[0].equals("todo");
    }

    public boolean isDeadline() {
        return command.split(" ")[0].equals("deadline");
    }

    public boolean isEvent() {
        return command.split(" ")[0].equals("event");
    }
}
