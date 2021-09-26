package duke.DukeExceptions;

public class EmptyCommand extends Exception {

    public String printMessage() {
        return ("\t____________________________________________________________\n" +
                "\tI'm Sorry, what did you say? It seems command was Empty\n" +
                "\t____________________________________________________________\n");
    }
}
