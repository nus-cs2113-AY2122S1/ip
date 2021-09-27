package duke;

public class Ui {

    //prints program output
    public static void printMessage(String line) {
        if (line.isEmpty()) {
            return;
        }
        System.out.println(line);
    }

    public static void printGreeting() {
        printMessage("Hello! I'm Duke.");
    }

    public static void printPrompt() {
        printMessage("What can I do for you?");
    }

    public static void printExit() {
        printMessage("Bye. Hope to see you again soon!");
    }
}
