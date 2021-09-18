package duke.commands;

public class HelpCommand extends Command {

    private static final String HELP_MESSAGE =
            "Below is the list of commands and input formats I am currently able to understand:\n"
                    + "1. list - Lists all your current tasks.\n"
                    + "2. clear - Clears all your existing tasks in your list.\n"
                    + "3. todo [task description] - Adds a task to your list.\n"
                    + "4. deadline [task description] /by [due date] - Adds a task with a due date to your list.\n"
                    + "5. event [event description] /at [date and time] - Adds an upcoming event to your list.\n"
                    + "6. done [task number] - Marks the task as done. Use the list to check the task number!\n"
                    + "7. echo [input] - Echoes whatever your input is.\n"
                    + "8. bye - Stop talking to me and exit the program.";

    public HelpCommand() {
        super();
    }

    @Override
    public CommandResult executeCommand() {
        CommandResult result = new CommandResult(HELP_MESSAGE);
        return result;
    }
}
