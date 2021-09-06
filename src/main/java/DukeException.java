public class DukeException extends Exception{
    public static final String LINE = "____________________________________________________________";

    public static void emptyTodoException() {
        System.out.println(LINE);
        System.out.println("Your todo command does not have an argument!");
        System.out.println("To input a valid todo command, type \"todo (argument)\"!");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    public static void invalidDeadlineException() {
        System.out.println(LINE);
        System.out.println("Your deadline command is invalid!");
        System.out.println("To input a valid deadline command, type \"deadline (description) /by (deadline)\"!");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    public static void invalidEventException() {
        System.out.println(LINE);
        System.out.println("Your event command is invalid!");
        System.out.println("To input a event command, type \"event (description) /at (when)\"!");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }
}
