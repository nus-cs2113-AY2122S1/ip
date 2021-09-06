package duke;

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

    private final String EXIT_MSG = "=> Come back soon, I'm still hungry \uD83D\uDE0B";

    private final String HELP_MSG = "Types of Commands Available [\uD83D\uDCAC]:\n"
            + "1. Terminate Duke            -> bye \n"
            + "2. Print Tasklist            -> list \n"
            + "3. Print Available Commands  -> help \n"
            + "4. Add Todos                 -> {todo <task description>}\n"
            + "5. Add Deadlines             -> {deadline <task description> /by <task date&time>}\n"
            + "6. Add Events                -> {event <task description> /at <task date&time>}\n"
            + "7. Set Task as Done          -> {done <task ID>}";

    public void printUserName() {
        System.out.println("\n[You]:");
    }

    public void printDukeName() {
        System.out.println("\n[Duke]:");
    }

    public void printCursor() {
        System.out.print("=> ");
    }

    public void printMsgWithCursor(String message) {
        System.out.print("=> " + message + "\n");
    }

    public void printWithPadding(String message) {
        System.out.println("   <" + message + ">");
    }

    public void printWelcomeMsg() {
        System.out.println(LOGO);
        printDukeName();
        System.out.println(WELCOME_MSG);
    }

    public void printExitMsg() {
        printDukeName();
        System.out.println(EXIT_MSG);
    }

    public void printHelpMsg() {
        printDukeName();
        System.out.println(HELP_MSG);
    }

}
