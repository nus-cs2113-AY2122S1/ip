package Duke.DukeException;

public class IndexMissingException extends Exception {
    public static final String line = "____________________________________________________________\n";
    public static final String message = line
            + "Please tell me the index for the operation.:-(\n"
            + line;

    public final void printMessage() {
        System.out.println(this.message);
    }
}
