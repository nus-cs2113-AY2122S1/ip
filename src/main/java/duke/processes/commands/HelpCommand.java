package duke.processes.commands;

public class HelpCommand extends Command {
    public HelpCommand() {
    }

    public CommandResult execute() {
        return new CommandResult("Below are the list of acceptable commands"
                + System.lineSeparator() + System.lineSeparator()
                + "add todo -> add a todo task to the list [add todo todo_name]"
                + System.lineSeparator()
                + "add event -> adds an event task to the list [add event event_name " +
                "/at-event_date"
                + System.lineSeparator()
                + "add deadline -> adds a deadline task to the list " +
                "[add deadline deadline_name /by-deadline_date"
                + System.lineSeparator()
                + "remove -> removes a task [remove task_number]"
                + System.lineSeparator()
                + "done -> marks a task as complete [done task_number]"
                + System.lineSeparator()
                + "undo -> marks a task as undone [undo task_number]"
                + System.lineSeparator()
                + "list -> Display the list of tasks"
                + System.lineSeparator()
                + "echo -> echo mode activated, where system echos your input, " +
                "type [exit] to leave mode"
                + System.lineSeparator()
                + "bye -> kill ikaros");
    }
}
