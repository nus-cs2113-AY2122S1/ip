package exceptions;

public class TimeException extends DukeException{
    public String toString() {
        return String.format("     ☹ OOPS!!! The time is missing or incorrect! It should be: \n" +
                "         YYYY-MM-DD HH:SS     such as: 2021-09-28 18:00\n" +
                "         (Note: HH:SS can be omitted for deadline type task) \n");
    }
}
