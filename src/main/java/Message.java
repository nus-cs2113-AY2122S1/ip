import java.util.Scanner;

public class Message {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___| inc.\n";
    private static final String INTRO = "Hello! I'm Duke\nWhat can I do for you?\n";
    private static final String END = "Bye. Hope to see you again soon!\n";
    private static final String SPACER = "____________________________________________________________\n";

    public static final String TERMINATE_CONSOLE = "bye";

    private static final Scanner in = new Scanner(System.in);

    public static void begin() {
        System.out.print(LOGO + SPACER + INTRO + SPACER);
    }

    public static void end() {
        System.out.print(SPACER + END + SPACER);
    }

    public static String getUserInput() {
        return in.nextLine();
    }

    public static void printUserInput(String userInput) {
        System.out.println(SPACER + userInput + '\n' + SPACER);
    }

}
