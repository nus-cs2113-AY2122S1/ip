import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printStartMessage();
        interactWithUser();
    }

    public static void printAppLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void printLine() {
        System.out.println("  ──────────────────────────────");
    }

    public static void printStartMessage() {
        printLine();
        System.out.println("  Hello! I'm Duke\n  How may I assist you");
        printLine();
    }

    public static void printExitMessage() {
        printLine();
        System.out.println("  Goodbye! Hope to see you soon!");
        printLine();
    }

    public static String getUserInput() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }

    public static void interactWithUser() {
        boolean isInteracting = true;
        String[] entryList = new String[100];
        int entryListLength = 0;

        while (isInteracting) {
            String userInput = getUserInput().strip();
            String[] words = userInput.split(" ");

            switch (words[0]) {
            case "bye":
                printExitMessage();
                isInteracting = false;
                break;
            case "list":
                printLine();
                for (int i = 0; i < entryListLength; i++) {
                    System.out.println("  " + i + ". " + entryList[i]);
                }
                printLine();
                break;
            default:
                printLine();
                System.out.println("  added: " + userInput);
                printLine();
                entryList[entryListLength] = userInput;
                entryListLength++;
                break;
            }
        }
    }
}
