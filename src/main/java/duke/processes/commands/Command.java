package duke.processes.commands;

public class Command {

    /**
     * Constructor for Command task
     */
    public Command() {
    }

    /**
     * if a Command does not have a subclass this method will return an error
     * message through the CommandResult class
     *
     * @return a CommandResult type with the specific error message
     */
    public CommandResult execute() {
        return new CommandResult("Sorry I did not quite understand, " +
                System.lineSeparator() + "try typing in help for brief user manual");
    }
}
