package duke.processes.commands;

public class Command {

    public Command() {
    }

    public CommandResult execute() {
        return new CommandResult("Sorry I did not quite understand, " +
                System.lineSeparator() + "try typing in help for brief user manual");
    }
}
