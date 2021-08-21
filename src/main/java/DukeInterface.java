import java.util.Scanner;

public class DukeInterface {

    private final Scanner in;

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
            + "-> I'm so hungry, would you mind feeding me tasks?";

    private final String exitMsg = "\n[Duke]:\n-> Come back soon, I'm still hungry!";

    /**
     * Default Constructor
     */
    public DukeInterface() {
        in = new Scanner(System.in);
    }

    public void printWelcomeMsg() {
        System.out.println(logo);
        System.out.println(welcomeMsg);
    }

    public void printExitMsg() {
        System.out.println(exitMsg);
    }

    public String readInput() {
        System.out.println("\n[You]:");
        String input = in.nextLine();
        return input;
    }

}
