public class Duke {

    private final String logo = "-------------------------------------------\n"
                            + "  __         __\n"
                            + " /  \\.-\"\"\"-./  \\     ____        _        \n"
                            + " \\    -   -    /    |  _ \\ _   _| | _____ \n"
                            + "  |   o   o   |     | | | | | | | |/ / _ \\\n"
                            + "  \\  .-'''-.  /     | |_| | |_| |   <  __/\n"
                            + "   '-\\__Y__/-'      |____/ \\__,_|_|\\_\\___|\n"
                            + "      `---`\n"
                            + "-------------------------------------------";

    private final String introMsg = "[Duke]:\n-> Hello! I'm Duke \uD83D\uDE00\n" + "-> What can I do for you?\n";

    private final String exitMsg = "[Duke]:\n-> Bye. Hope to see you again soon!\n";

    /* Default Constructor */
    public Duke() {
        displayUI();
    }

    /* Method to Display User Interface */
    public void displayUI() {
        System.out.println(logo);
        System.out.println(introMsg);
        System.out.println(exitMsg);
    }

    public static void main(String[] args) {

        Duke myObject = new Duke();

    }
}
