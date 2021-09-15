package duke.command;

public class EmptyCommand extends Exception {

    public static void printMessage() {
        System.out.println("\t____________________________________________________________\n" +
                "\tI'm Sorry, what did you say?\n" +
                "\t____________________________________________________________\n");
    }
}
