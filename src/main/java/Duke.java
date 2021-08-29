import java.util.Scanner;

public class Duke {
    private static final String COMMAND_EXIT_WORD = "bye";
    private static final Scanner INPUT_COMMAND = new Scanner(System.in);

    public static void main(String[] args) {
        showWelcomeMessage();
        executeCommand();
    }

    private static void executeCommand() {
        String inputLine = INPUT_COMMAND.nextLine();
        while(!inputLine.equals(COMMAND_EXIT_WORD)) {
            System.out.println("  Task: " + inputLine);
            System.out.println("  Anything else?");
            inputLine = INPUT_COMMAND.nextLine();
        }
        exitProgram();
    }

    private static void showWelcomeMessage() {
        String logo = "▒▒▒▒▒▒▒█▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█\n"
                + "▒▒▒▒▒▒▒█░▒▒▒▒▒▒▒▓▒▒▓▒▒▒▒▒▒▒░█\n"
                + "▒▒▒▒▒▒▒█░▒▒▓▒▒▒▒▒▒▒▒▒▄▄▒▓▒▒░█░▄▄\n"
                + "▒▒▄▀▀▄▄█░▒▒▒▒▒▒▓▒▒▒▒█░░▀▄▄▄▄▄▀░░█\n"
                + "▒▒█░░░░█░▒▒▒▒▒▒▒▒▒▒▒█░░░░░░░░░░░█\n"
                + "▒▒▒▀▀▄▄█░▒▒▒▒▓▒▒▒▓▒█░░░█▒░░░░█▒░░█\n"
                + "▒▒▒▒▒▒▒█░▒▓▒▒▒▒▓▒▒▒█░░░░░░░▀░░░░░█\n"
                + "▒▒▒▒▒▄▄█░▒▒▒▓▒▒▒▒▒▒▒█░░█▄▄█▄▄█░░█\n"
                + "▒▒▒▒█░░░█▄▄▄▄▄▄▄▄▄▄█░█▄▄▄▄▄▄▄▄▄█\n"
                + "▒▒▒▒█▄▄█░░█▄▄█░░░░░░█▄▄█░░█▄▄█\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("  ____________________________________________________________");
        System.out.println("           This is your chatbot assistant, Neko Duke! :)");
        System.out.println("                What can I do for you today?");
        System.out.println("  ____________________________________________________________\n");
    }

    private static void exitProgram() {
        System.out.println("  Bye! Have a nice day and hope to see you again!");
        System.exit(0);
    }
}
