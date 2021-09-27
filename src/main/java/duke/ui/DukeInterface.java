package duke.ui;

/**
 * Text UI of the Duke application.
 */
public class DukeInterface {

    private final String LOGO = "+++++++++++++++++++++++++++++++++++++++++++++\n"
            + "  __         __\n"
            + " /  \\.-\"\"\"-./  \\     ____        _        \n"
            + " \\    -   -    /    |  _ \\ _   _| | _____ \n"
            + "  |   o   o   |     | | | | | | | |/ / _ \\\n"
            + "  \\  .-'''-.  /     | |_| | |_| |   <  __/\n"
            + "   '-\\__Y__/-'      |____/ \\__,_|_|\\_\\___|\n"
            + "      `---`\n"
            + "[A NUS CS2113T Project by: Peh Zhenhao, Amos]\n"
            + "+++++++++++++++++++++++++++++++++++++++++++++";

    private final String WELCOME_MSG = "=> Hello! I'm Duke :)\n"
            + "=> I'm so hungry, would you mind feeding me tasks?";

    /**
     * Prints logo of Duke upon the start of the Duke application.
     */
    public void printLogo() {
        System.out.println(LOGO);
    }

    /**
     * Prints welcome message used upon the start of the Duke application.
     */
    public void printWelcomeMessage() {
        printDukeName();
        System.out.println(WELCOME_MSG);
    }

    /**
     * Prints user display name.
     */
    public void printUserName() {
        System.out.println("\n[You]:");
    }

    /**
     * Prints Duke's display name.
     */
    public void printDukeName() {
        System.out.println("\n[Duke]:");
    }

    /**
     * Prints the cursor used for user input and Duke UI messages.
     */
    public void printCursor() {
        System.out.print("=> ");
    }

    /**
     * Shows dukeMessage to user on the terminal.
     *
     * @param dukeMessage message to be displayed on the user's terminal.
     */
    public void printDukeMessage(String dukeMessage) {
        printDukeName();
        printCursor();
        System.out.println(dukeMessage);
    }

}
