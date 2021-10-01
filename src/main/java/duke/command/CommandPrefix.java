package duke.command;

/**
 * Set of valid prefixes and descriptions associated with Duke commands.
 * Descriptions are included for an easy-to-read view for <code>Help</code> command
 */
public enum CommandPrefix {
    ADD("add", "add tasks to list"),
    BYE("bye", "close Duke"),
    CLEAR("clear", "clear list"),
    DATE("date", "search tasks with matching date"),
    DEADLINE("deadline", "adds a deadline to a todo"),
    DELETE("delete", "delete tasks from list"),
    DONE("done", "mark task as done"),
    ECHO("echo", "echo input"),
    EVENT("event" , "adds events to list"),
    FIND("find", "find tasks with matching keyword"),
    HELP("help", "prints feature list"),
    LIST("list", "print current list"),
    MASCOT("mascot", "echo, but funnier"),
    OOPSIE("oopsie", ""),
    TODO("todo", "adds todos to list");

    public final String command;
    public final String description;

    CommandPrefix(String command, String description) {
        this.command = command;
        this.description = description;
    }

    public String getPrefix() {
        return this.command;
    }

    public String getDescription() {
        return this.description;
    }
}
