import java.util.Scanner;

public class DukeInterface {

    private final Scanner in;

    private final String LOGO = "+++++++++++++++++++++++++++++++++++++++++++++\n"
            + "  __         __\n"
            + " /  \\.-\"\"\"-./  \\     ____        _        \n"
            + " \\    -   -    /    |  _ \\ _   _| | _____ \n"
            + "  |   o   o   |     | | | | | | | |/ / _ \\\n"
            + "  \\  .-'''-.  /     | |_| | |_| |   <  __/\n"
            + "   '-\\__Y__/-'      |____/ \\__,_|_|\\_\\___|\n"
            + "      `---`\n"
            + "[A NUS CS2113T Project by: Peh Zhenhao, Amos]\n"
            + "+++++++++++++++++++++++++++++++++++++++++++++\n";

    private final String TEXT_BOUNDARY = "--------------------------------------------------------";

    private final String WELCOME_MSG = "[Duke]:\n"
            + TEXT_BOUNDARY + "\n-> Hello! I'm Duke \uD83D\uDE00\n"
            + "-> I'm so hungry, would you mind feeding me tasks?\n" + TEXT_BOUNDARY;

    private final String EXIT_MSG = "\n[Duke]:\n"
            + TEXT_BOUNDARY + "\n-> Come back soon, I'm still hungry \uD83D\uDE0B\n" + TEXT_BOUNDARY;

    DukeInterface() {
        in = new Scanner(System.in);
    }

    public String readInput() {
        printUserName();
        printTextBoundary();
        String input = in.nextLine();
        printTextBoundary();
        return input;
    }

    public void printWelcomeMsg() {
        System.out.println(LOGO);
        System.out.println(WELCOME_MSG);
    }

    public void printExitMsg() {
        System.out.println(EXIT_MSG);
    }

    public void printUserName() {
        System.out.println("\n[You]:");
    }

    public void printDukeName() {
        System.out.println("\n[Duke]:");
    }

    public void printTextBoundary() {
        System.out.println(TEXT_BOUNDARY);
    }

    public void printWithCursor(String message) {
        System.out.println("-> " + message);
    }

    public void printWithoutCursor(String message) {
        System.out.println(message);
    }

    public void printLoadingMsg(String message) {
        System.out.println("[\u23F3] " + message);
    }

    public void printSystemMsg(String message) {
        System.out.println("\n[\uD83D\uDD14] " + message);
    }


}
