package commands;

import static ui.Ui.INDENTATION;

/**
 * A Command class that send the help message to the Ui
 */
public class HelpCommand extends Command{
    private static final String HELP_MESSAGE = "list\n" +INDENTATION +
            "- Shows you the list of tasks you have\n\n" +INDENTATION +
            "todo (insert task description)\n" + INDENTATION +
            "- Saves a general todo task\n\n" + INDENTATION +
            "deadline (insert deadline description) /by (insert time)\n" + INDENTATION +
            "- Saves a task with a given deadline\n\n" + INDENTATION +
            "event (insert event description) /at (insert time interval)\n" + INDENTATION +
            "- Saves an event happening at a certain time period\n\n" + INDENTATION +
            "delete (insert number)\n" + INDENTATION +
            "- Deletes a task with the corresponding number on the list\n\n" + INDENTATION +
            "done (insert number)\n" + INDENTATION +
            "- Marks the task with the corresponding number as done";

    /**
     * Sends the help message to the Ui
     * @return A CommandResult that tells the Ui to print the help message.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(HELP_MESSAGE,PrintOptions.DEFAULT);
    }
}
