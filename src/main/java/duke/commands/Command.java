package duke.commands;

import duke.exceptions.DukeException;

public abstract class Command {

    protected String argument;

    protected Command() {
    }

    protected Command(String argument) {
        this.argument = argument;
    }

    protected boolean isEmptyArgument(String argument) {
        return argument.isEmpty();
    }

    public abstract CommandResult executeCommand() throws DukeException;
}
