package Duke.DukeException;

public class InvalidCommandException extends Exception {
    public static final String line = "____________________________________________________________\n";
    public static final String message = line
            + "OOPS!!! I'm sorry, but I don't know what that means :-(\n"
            + "Please give me a valid command.\n" + line;

    public final void printMessage() {
        System.out.println(this.message);
    }
}
