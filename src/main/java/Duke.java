import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";

    private static String indent(String text) {
        String[] lines = text.split(System.lineSeparator());
        for (int i = 0; i < lines.length; i++) {
            lines[i] = " ".repeat(5) + lines[i];
        }
        return String.join(System.lineSeparator(), lines);
    }

    private static void printTextWithHorizontalLineAndIndentation(String text) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(indent(text));
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }

    private static void greet() {
        String greeting = "Hello! I'm Duke" + System.lineSeparator()
                + "What can I do for you?";
        printTextWithHorizontalLineAndIndentation(greeting);
    }

    private static void exit() {
        String farewell = "Bye. Hope to see you again soon!";
        printTextWithHorizontalLineAndIndentation(farewell);
    }

    private static void echoCommands() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String command = in.nextLine();
            if (command.equals("bye")) {
                break;
            }
            printTextWithHorizontalLineAndIndentation(command);
        }
    }

    public static void main(String[] args) {
        greet();
        echoCommands();
        exit();
    }
}
