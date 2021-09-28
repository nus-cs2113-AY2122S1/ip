package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Shows help instructions.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "⮞ Example: " + COMMAND_WORD;

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(AddCommand.MESSAGE_USAGE);
        ui.showLine();
        System.out.println(FindCommand.MESSAGE_USAGE);
        ui.showLine();
        System.out.println(DoneCommand.MESSAGE_USAGE);
        ui.showLine();
        System.out.println(DeleteCommand.MESSAGE_USAGE);
        ui.showLine();
        System.out.println(ListCommand.MESSAGE_USAGE);
        ui.showLine();
        System.out.println(HelpCommand.MESSAGE_USAGE);
        ui.showLine();
        System.out.println(ExitCommand.MESSAGE_USAGE);
    }
}
