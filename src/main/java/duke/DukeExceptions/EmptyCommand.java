package duke.DukeExceptions;

public class EmptyCommand extends DukeException {

    public String printMessage() {
        return ("\t____________________________________________________________\n" +
                "\tNo command detected. Command was empty :)\n" +
                "\t____________________________________________________________\n" +
                "\tKey in [help] to list available commands\n" +
                "\t____________________________________________________________\n");
    }
}
