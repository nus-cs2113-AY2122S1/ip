public class DukeException extends Exception{
    public static final String LINE = "____________________________________________________________";

    public static void emptyTodoException() {
        System.out.println(LINE);
        System.out.println("Your todo command does not have an argument!");
        System.out.println("To input a valid todo command, type \"todo (argument)\"!");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }
}
