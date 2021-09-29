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
            "deadline (description) /by yyyy-MM-ddTHH:mm\n" + INDENTATION +
            "- Saves a task with a given deadline\n" + INDENTATION +
            "- yyyy is the year, MM is the month, dd is the day, HH is the hour,\n" +INDENTATION +
            "  and mm is the minute \n\n" + INDENTATION +
            "event (description) /at yyyy-MM-ddTHH:mm /to yyyy-MM-ddTHH:mm\n" + INDENTATION +
            "- Saves an event happening at a certain time period\n" + INDENTATION +
            "- yyyy is the year, MM is the month, dd is the day, HH is the hour,\n" +INDENTATION+
            "  and mm is the minute \n\n" + INDENTATION +
            "delete (integer)\n" + INDENTATION +
            "- Deletes a task with the corresponding number on the list\n\n" + INDENTATION +
            "done (integer)\n" + INDENTATION +
            "- Marks the task with the corresponding number as done\n\n" + INDENTATION +
            "find (keyword)\n" + INDENTATION +
            "- Finds tasks that contains the String given\n\n" + INDENTATION +
            "find /d yyyy-MM-dd\n" + INDENTATION +
            "- Finds tasks with the same date as the date given\n" + INDENTATION +
            "- yyyy is the year, MM is the month and dd is the day";

    /**
     * Sends the help message to the Ui
     * @return A CommandResult that tells the Ui to print the help message.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(HELP_MESSAGE,PrintOptions.DEFAULT);
    }
}
