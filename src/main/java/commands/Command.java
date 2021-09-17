package commands;

import tasks.TaskList;
import errors.InvalidCommand;

import static commands.CommandList.*;

public class Command {
    String command;
    String description;
    String date;

    public Command(String command, String description) {
        this.command = command;
        this.description = description;
        this.date = null;
    }

    public Command(String command, String description, String date) {
        this.command = command;
        this.description = description;
        this.date = date;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Processes the commands entered by the user.
     *
     * @return Success/error message to be printed in console.
     * @throws InvalidCommand If an invalid command is entered.
     */
    public String execute() throws InvalidCommand {
        // Capitalise the command
        switch (command) {
        case LIST:
            return TaskList.listTasks();
        case TODO:
            return TaskList.addTodo(description);
        case DEADLINE:
            return TaskList.addDeadline(description, date);
        case EVENT:
            return TaskList.addEvent(description, date);
        case DONE:
            return TaskList.markDone(description);
        case DELETE:
            return TaskList.deleteTask(description);
        case HELP:
            return HelpCommand.helpMessage;
        default:
            throw new InvalidCommand();
        }
    }
}
