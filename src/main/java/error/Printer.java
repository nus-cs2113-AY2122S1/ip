package error;

public class Printer {
    public static void printLineSeparator() {
        String line = "________________________________________________________";
        System.out.println(line);
    }

    public static void printWelcomeMessage() {
        printLineSeparator();
        System.out.println("What can I do for you?");
        printLineSeparator();
    }

    public static void printByeMessage() {
        printLineSeparator();
        System.out.println("Farewell. Come back when you need more help");
        printLineSeparator();
    }
}
