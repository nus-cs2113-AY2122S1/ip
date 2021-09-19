package IzzIbot.command;

import IzzIbot.Ui;
import IzzIbot.TaskList;
import IzzIbot.exceptions.IzzIbotException;

public abstract class Command {

    protected Ui ui;
    protected TaskList tasks;

    /**
     * Constructor for Command
     * @param ui UI to be used
     * @param tasks TaskList to be used
     */
    public Command(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    /**
     * Executes the appropriate command
     * @throws IzzIbotException if no command is specified
     */
    public void execute() throws IzzIbotException {
        throw new IzzIbotException("This method is unspecified!");
    }

}
