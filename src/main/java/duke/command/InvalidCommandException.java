package duke.command;

public class InvalidCommandException extends Exception{
    public static void printMessage() {
        System.out.println ("\t____________________________________________________________\n" +
                "\tOOOPS!!! I'm sorry, but I don't know what that means. EPIC SADS :-(\n" +
                "\t____________________________________________________________\n\t");
    }
}
