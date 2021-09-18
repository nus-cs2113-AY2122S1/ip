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
    private static final String helpMessage = "Welcome to the help page."
            + "\nBye: Exits the program."
            + "\nDeadline: Adds a task with deadline. " + DeadlineCommand.commandSyntax
            + "\nDelete: Deletes a task. " + DeleteCommand.commandSyntax
            + "\nDone: Marks a task as completed. " + DoneCommand.commandSyntax
            + "\nEvent: Creates an event. " + EventCommand.commandSyntax
            + "\nFind: Finds all tasks with the specified word. " + FindCommand.commandSyntax
            + "\nHelp: Displays the help message."
            + "\nList: Lists all tasks."
            + "\nTodo: Creates a Todo task. " + TodoCommand.commandSyntax
            + "\nWhat's On: Lists all tasks occurring on the specified day. " + WhatsOnCommand.commandSyntax;


    public HelpCommand(String command) {
        super(command);
    }

    @Override
    public void execute(Ui ui, ArrayList<Task> tasks, Storage storage) {
        ui.customPrint(helpMessage);
    }
}
