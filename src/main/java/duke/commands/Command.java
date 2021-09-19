package duke.commands;

import duke.exceptions.DukeException;

/**
 * The abstract parent class of all available commands on <code>Duke</code> which represents all the
 * information about a command, such as the execution of the command.
 */

public abstract class Command {

    protected String argument;

    /**
     * Constructs a <code>Command</code> object without arguments, such as <code>ClearCommand, ExitCommand,
     * HelpCommand, InvalidCommand, and ListCommand.</code>
     */

    protected Command() {
    }

    /**
     * Constructs a <code>Command</code> object that requires an argument, such as <code>AddDeadlineCommand,
     * AddEventCommand, AddToDoCommand, DeleteTaskCommand, EchoCommand, and MarkTaskDoneCommand.</code>
     * @param argument Argument from the user input for the command to execute properly
     */

    protected Command(String argument) {
        this.argument = argument;
    }

    protected boolean isEmptyArgument(String argument) {
        return argument.isEmpty();
    }

    /**
     * Retrieves the arguments (if any) and executes the command to return a <code>CommandResult</code> for
     * display on the user interface.
     *
     * @return <code>CommandResult</code> that shows feedback to the user
     * @throws DukeException If the arguments are required but not found
     */

    public abstract CommandResult executeCommand() throws DukeException;
}
