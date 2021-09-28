package duke.DukeExceptions;

public class EmptyCommand extends DukeException {

    /**
     * @return Empty input Message.
     */
    public String toString() {
        return ("\t______________________________________________________________________\n" +
                "\tNo command detected. Command was empty :)\n" +
                "\t______________________________________________________________________\n" +
                "\tKey in [help] to list available commands\n" +
                "\t______________________________________________________________________\n");
    }
}
