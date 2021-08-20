public class DukeInterface {

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
            + "-> What can I do for you?\n";

    private final String exitMsg = "[Duke]:\n-> Bye. Hope to see you again soon!\n";

    public void printWelcomeMsg() {
        System.out.println(logo);
        System.out.println(welcomeMsg);
    }

    public void printExitMsg() {
        System.out.println(exitMsg);
    }



}
