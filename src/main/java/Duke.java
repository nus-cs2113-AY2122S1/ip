import java.util.Scanner;

public class Duke {
    private static final String INDENTED_HORIZONTAL_LINE = "    ____________________________________________________________";
    private static final String[] items = new String[100];
    private static int itemCount = 0;

    private static String indent(String text) {
        String[] lines = text.split(System.lineSeparator());
        for (int i = 0; i < lines.length; i++) {
            lines[i] = " ".repeat(5) + lines[i];
        }
        return String.join(System.lineSeparator(), lines);
    }

    private static void printTextWithHorizontalLineAndIndentation(String text) {
        System.out.println(INDENTED_HORIZONTAL_LINE);
        System.out.println(indent(text));
        System.out.println(INDENTED_HORIZONTAL_LINE);
        System.out.println();
    }

    private static void printGreeting() {
        String greeting = "Hello! I'm Duke" + System.lineSeparator()
                + "What can I do for you?";
        printTextWithHorizontalLineAndIndentation(greeting);
    }

    private static void printItems() {
        String[] itemsWithNumbers = new String[itemCount];
        for (int i = 0; i < itemCount; i++) {
            itemsWithNumbers[i] = (i + 1) + ". " + items[i];
        }
        String formattedItems = String.join(System.lineSeparator(), itemsWithNumbers);
        printTextWithHorizontalLineAndIndentation(formattedItems);
    }

    private static void addItem(String item) {
        items[itemCount] = item;
        itemCount++;
        printTextWithHorizontalLineAndIndentation("added: " + item);
    }

    private static void handleCommands() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String command = in.nextLine();
            switch (command) {
            case "list":
                printItems();
                break;
            case "bye":
                return;
            default:
                addItem(command);
                break;
            }
        }
    }

    private static void printFarewell() {
        String farewell = "Bye. Hope to see you again soon!";
        printTextWithHorizontalLineAndIndentation(farewell);
    }

    public static void main(String[] args) {
        printGreeting();
        handleCommands();
        printFarewell();
    }
}
