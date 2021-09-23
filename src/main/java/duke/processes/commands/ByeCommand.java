package duke.processes.commands;

public class ByeCommand extends Command {
    public static boolean isRunning = true;

    public ByeCommand() {
    }

    public CommandResult execute() {
        isRunning = false;
        return new CommandResult("Are you sure you want to leave [y/n]");
    }
}
