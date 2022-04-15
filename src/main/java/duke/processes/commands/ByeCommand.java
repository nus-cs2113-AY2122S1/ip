package duke.processes.commands;

public class ByeCommand extends Command {

    public static boolean isRunning = true;

    public ByeCommand() {
    }

    /**
     * Changes the boolean isRunning to false when executed
     *
     * @return CommandResult with the message asking user whether they are
     * sure they want to exit the program.
     */
    public CommandResult execute() {
        isRunning = false;
        return new CommandResult("Are you sure you want to leave [y/n]");
    }
}
