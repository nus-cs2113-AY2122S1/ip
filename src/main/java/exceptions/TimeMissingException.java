package exceptions;

public class TimeMissingException extends DukeException {


    @Override
    public String toString() {
        return String.format("     ☹ OOPS!!! The time of this task cannot be empty!\n");

    }
}
