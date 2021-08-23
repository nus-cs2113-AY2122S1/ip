import java.util.Scanner;

public class Duke {
    public static final String LOGO =
            "      ____        _        \n"     +
            "     |  _ \\ _   _| | _____ \n"    +
            "     | | | | | | | |/ / _ \\\n"    +
            "     | |_| | |_| |   <  __/\n"     +
            "     |____/ \\__,_|_|\\_\\___|\n";

    public static final String LINE =
            "    ____________________________________________________________" + "\n";

    public static final String PADDING = "     ";

    // Duke greeting
    public static void greet() {
        System.out.print(LINE);
        System.out.println(LOGO);
        System.out.println(PADDING + "Hello! I'm Duke");
        System.out.println(PADDING + "What can I do for you?");
        System.out.println(LINE);
    }

    // Duke exits
    public static void exit() {
        System.out.print(LINE);
        System.out.println(PADDING + "Bye. Hope to see you again soon!");
        System.out.print(LINE);
    }

    // Duke echo
    public static void echo() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            }
            System.out.print(LINE);
            System.out.println(PADDING + command);
            System.out.println(LINE);
        }
    }

    public static void main(String[] args) {
        greet();
        echo();
        exit();
    }
}
