package duke.command;

/**
 * Set of valid prefixes and descriptions associated with Duke commands.
 */
public enum CommandPrefix {
    ADD("add", "add tasks to list"),
    BYE("bye", "close Duke"),
    CLEAR("clear", "clear list"),
    DELETE("delete", "delete tasks from list"),
    DONE("done", "mark task as done"),
    ECHO("echo", "echo input"),
    LIST("list", "print current list"),
    MASCOT("mascot", "echo, but funnier"),
    OOPSIE("oopsie", ""),
    FIND("find", "find tasks with matching keyword"),
    DATE("date", "search tasks with matching date"),
    HELP("help", "prints feature list");

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
