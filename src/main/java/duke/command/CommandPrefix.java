package duke.command;

/**
 * Set of valid prefixes associated with Duke commands.
 */
public enum CommandPrefix {
    ADD("add"), BYE("bye"), CLEAR("clear"), DELETE("delete"),
    DONE("done"), ECHO("echo"), LIST("list"),
    MASCOT("mascot"), OOPSIE("oopsie"), FIND("find"), DATE("date");

    public final String command;

    private CommandPrefix(String command) {
        this.command = command;
    }

    public String getPrefix() {
        return this.command;
    }
}
