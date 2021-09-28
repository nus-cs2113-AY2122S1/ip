package commands;

import storage.Storage;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

/**
 * Represents the Help command. Helps to list out the details of the commands and the
 * syntax of the commands.
 */

public class HelpCommand extends Command {
    private static final String HELP_MESSAGE = "Welcome to the help page."
            + "\nBye: Exits the program."
            + "\nDeadline: Adds a task with deadline. " + DeadlineCommand.COMMAND_SYNTAX
            + "\nDelete: Deletes a task. " + DeleteCommand.COMMAND_SYNTAX
            + "\nDone: Marks a task as completed. " + DoneCommand.COMMAND_SYNTAX
            + "\nEvent: Creates an event. " + EventCommand.COMMAND_SYNTAX
            + "\nFind: Finds all tasks with the specified word. " + FindCommand.COMMAND_SYNTAX
            + "\nHelp: Displays the help message."
            + "\nList: Lists all tasks."
            + "\nTodo: Creates a Todo task. " + TodoCommand.COMMAND_SYNTAX
            + "\nWhat's On: Lists all tasks occurring on the specified day. " + WhatsOnCommand.COMMAND_SYNTAX;


    public HelpCommand(String command) {
        super(command);
    }

    @Override
    public void execute(Ui ui, ArrayList<Task> tasks, Storage storage) {
        ui.customPrint(HELP_MESSAGE);
    }
}
