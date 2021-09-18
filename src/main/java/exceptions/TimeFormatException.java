package exceptions;

public class TimeFormatException extends DukeException{
    @Override
    public String toString() {
        return String.format("     ☹ OOPS!!! The time format seems incorrect! It should be: \n" +
                "         YYYY-MM-DD (for deadline type)     such as: 2021-09-28\n" +
                "         YYYY-MM-DDTHH:SS (for event type)  such as: 2021-09-28T18:00\n");
    }
}
