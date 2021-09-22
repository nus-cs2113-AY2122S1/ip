package duke.command;

public class HelpCommand extends Command {

    private final String HELP_MSG = "Here are the types of commands available [\uD83D\uDCAC]:\n"
            + "1. Terminate Duke            -> bye \n"
            + "2. Print Available Commands  -> help \n"
            + "3. Print Tasklist            -> list \n"
            + "4. Add Todos                 -> {todo <task description>}\n"
            + "5. Add Deadlines             -> {deadline <task description> /by <task date&time>}\n"
            + "6. Add Events                -> {event <task description> /at <task date&time>}\n"
            + "7. Set Task as Done          -> {done <task ID>}\n"
            + "8. Delete Task               -> {delete <task ID>}\n"
            + "9. Find Task                 -> {find <keyword>}";

    @Override
    public CommandResult executeCommand() {
        return new CommandResult(HELP_MSG, false, false);
    }

}
