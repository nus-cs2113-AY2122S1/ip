package duke.commands;

/** Includes the operations needed to print the help menu to the user interface. */
public class HelpCommand extends Command {

    private static final String HELP_MESSAGE =
            "Below is the list of commands and input formats I am currently able to understand:\n"
                    + "1. list - Lists all your current tasks.\n"
                    + "2. clear - Clears all your existing tasks in your list.\n"
                    + "3. todo [task description] - Adds a task to your list.\n"
                    + "4. deadline [task description] /by [due date] - Adds a task with a due date to your list.\n"
                    + "5. event [event description] /at [date and time] - Adds an upcoming event to your list.\n"
                    + "6. done [task number] - Marks the task as done. Use the list to check the task number!\n"
                    + "7. delete [task number] - Deletes the task. Use the list to check the task number!\n"
                    + "8. date [yyyy-mm-dd] - Finds tasks with the date specified attached to it.\n"
                    + "9. find [keyword] - Finds tasks with the keyword specified in its description.\n"
                    + "10. echo [input] - Echoes whatever your input is.\n"
                    + "11. help - View this menu again.\n"
                    + "12. bye - Stop talking to me and exit the program.";

    /** Constructed when the command word of the user input is "help". */
    public HelpCommand() {
        super();
    }

    @Override
    public CommandResult executeCommand() {
        CommandResult result = new CommandResult(HELP_MESSAGE);
        return result;
    }
}
