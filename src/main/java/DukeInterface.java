import java.util.Scanner;

public class DukeInterface {

    public boolean isRunning;

    private final Scanner in;

    private String input;

    private final String logo = "-------------------------------------------\n"
            + "  __         __\n"
            + " /  \\.-\"\"\"-./  \\     ____        _        \n"
            + " \\    -   -    /    |  _ \\ _   _| | _____ \n"
            + "  |   o   o   |     | | | | | | | |/ / _ \\\n"
            + "  \\  .-'''-.  /     | |_| | |_| |   <  __/\n"
            + "   '-\\__Y__/-'      |____/ \\__,_|_|\\_\\___|\n"
            + "      `---`\n"
            + "-------------------------------------------";

    private final String welcomeMsg = "[Duke]:\n-> Hello! I'm Duke \uD83D\uDE00\n"
            + "-> What can I do for you?";

    private final String exitMsg = "\n[Duke]:\n-> Bye. Hope to see you again soon!";

    /**
     * Default Constructor
     */
    public DukeInterface() {
        in = new Scanner(System.in);
        isRunning = true;
    }

    public void printWelcomeMsg() {
        System.out.println(logo);
        System.out.println(welcomeMsg);
    }

    public void printExitMsg() {
        System.out.println(exitMsg);
    }

    public void readInput() {
        System.out.println("\n[You]:");
        input = in.nextLine();

        if (input.equals("bye")) {
            isRunning = false;
        } else {
            echoInput();
        }
    }

    public void echoInput() {
        System.out.println("\n[Duke]:\n-> The input entered is \"" + input + "\"");
    }

}
