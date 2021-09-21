package duke.ui;

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

    private final String WELCOME_MSG = "=> Hello! I'm Duke \uD83D\uDC3B\n"
            + "=> I'm so hungry, would you mind feeding me tasks?";

    public void printWelcomeMsg() {
        System.out.println(LOGO);
        printDukeName();
        System.out.println(WELCOME_MSG);
    }

    public void printUserName() {
        System.out.println("\n[You]:");
    }

    public void printDukeName() {
        System.out.println("\n[Duke]:");
    }

    public void printCursor() {
        System.out.print("=> ");
    }

    public void printDukeMessage(String dukeMessage) {
        printDukeName();
        printCursor();
        System.out.println(dukeMessage);
    }

}
