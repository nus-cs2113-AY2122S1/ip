package commands;

import static constants.Message.INDENTATION;

/**
 * A Command class that send the help message to the Ui
 */
public class HelpCommand extends Command{

    public static final String HELP_COMMAND = "help";
    private static final String HELP_MESSAGE = "list\n" +INDENTATION +
            "- Shows you the list of tasks you have\n\n" +INDENTATION +
            "todo (description)\n" + INDENTATION +
            "- Saves a general todo task\n\n" + INDENTATION +
            "deadline (description) /by yyyy-MM-ddTHH:mm:ss\n" + INDENTATION +
            "- Saves a task with a given deadline\n\n" + INDENTATION +
            "event (description) /at yyyy-MM-ddTHH:mm:ss /to yyyy-MM-ddTHH:mm:ss\n" + INDENTATION +
            "- Saves an event happening at a certain time period\n\n" + INDENTATION +
            "delete (number)\n" + INDENTATION +
            "- Deletes a task with the corresponding number on the list\n\n" + INDENTATION +
            "done (number)\n" + INDENTATION +
            "- Marks the task with the corresponding number as done\n\n" + INDENTATION +
            "find (description)\n" + INDENTATION +
            "- Finds tasks that contains the String given\n\n" + INDENTATION +
            "find /d yyyy-MM-dd\n" + INDENTATION +
            "- Finds tasks with the same date as the date given";

    /**
     * Sends the help message to the Ui
     * @return A CommandResult that tells the Ui to print the help message.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(HELP_MESSAGE,PrintOptions.DEFAULT);
    }
}
