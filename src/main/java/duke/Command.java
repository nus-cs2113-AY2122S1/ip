package duke;

/**
 * Commands that can be used
 */
public enum Command {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DONE("done"),
    DELETE("delete"),
    LIST("list"),
    BYE("bye");

    public final String label;

    Command(String label) {
        this.label = label;
    }

}
