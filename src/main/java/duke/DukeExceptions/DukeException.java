package duke.DukeExceptions;

public class DukeException extends Exception{
    /**
     * convert to new default Exception message.
     *
     * @return error Message.
     */
    public String toString() {
        return "Something Went Wrong";
    }
}
