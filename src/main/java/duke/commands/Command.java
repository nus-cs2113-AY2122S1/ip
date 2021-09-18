package duke.commands;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.tasks.TaskManager;

public abstract class Command {

    protected TaskManager taskManager = new TaskManager();
    protected Parser PARSER = new Parser();
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
