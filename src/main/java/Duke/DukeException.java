package Duke;

public class DukeException extends Exception{
    public DukeException (String errorMsg) {
        super("\t" + errorMsg + System.lineSeparator()
        + "\t----------------------------------------------------------------------");
    }
}
