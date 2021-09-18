package commands;

import storage.Storage;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

public class HelpCommand extends Command {
    private static final String helpMessage = "Welcome to the help page."
            + "\nBye: Exits the program."
            + "\nDeadline: Adds a task with deadline. " + DeadlineCommand.commandSyntax
            + "\nDelete: Deletes a task. " + DeleteCommand.commandSyntax
            + "\nDone: Marks a task as completed. " + DoneCommand.commandSyntax
            + "\nEvent: Creates an event. " + EventCommand.commandSyntax
            + "\nHelp: Displays the help message."
            + "\nList: Lists all tasks."
            + "\nTodo: Creates a Todo task. " + TodoCommand.commandSyntax
            + "\nWhat's On: Lists all tasks occuring on the specified day. " + WhatsOnCommand.commandSyntax;


    public HelpCommand(String command) {
        super(command);
    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public void execute(Ui ui, ArrayList<Task> tasks, Storage storage) {
        ui.customPrint(helpMessage);
    }
}
