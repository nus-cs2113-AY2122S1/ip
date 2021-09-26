package duke.DukeExceptions;

public class IllegalToDoException extends Exception {
    //@Override
    public String printMessage() {
        return ("\t____________________________________________________________\n" +
                "\tOOOPS!!! The description of a todo cannot be empty. BIG SADS\n" +
                "\t____________________________________________________________\n\t");
    }
}
