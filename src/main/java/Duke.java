import java.util.Scanner;
public class Duke {

    private static final String line = "____________________________________________________________";
    private static String[] commands = new String[100];
    private static int totalCommands = 0;

    public static void main(String[] args) {
        greetUser();
        addCommands();
    }

    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void printString(String response) {
        System.out.println(line);
        System.out.println(" " + response);
        System.out.println(line);
    }

    // greet the user and prints a line
    public static void greetUser() {
        printString("Hello! I'm Duke\n" +
                " What can I do for you?");
    }

    // exits the programme after printing the greeting and a line
    public static void exitChatbot() {
        printString("Bye. Hope to see you again soon!");
    }

    // echoes user commands until the user types bye
    public static void echo() {
        String command;
        Scanner in = new Scanner(System.in);
        while (true) {
            command = in.nextLine();
            if (command.equals("bye")) {
                exitChatbot();
                break;
            }
            printString(command);
        }
    }

    // lists all commands added to duke
    public static void listCommands() {
        if (commands.length == 0) {
            printString("no commands");
        } else {
            System.out.println(line);
            for (int i = 0; i < totalCommands; i++) {
                System.out.print(" ");
                System.out.print(i + 1);
                System.out.println(". " + commands[i]);
            }
            System.out.println(line);
        }
    }

    // adds commands to an array of commands
    public static void addCommands() {
        String command;
        Scanner in = new Scanner(System.in);
        while (true) {
            command = in.nextLine();
            if (command.equals("bye")) {
                exitChatbot();
                break;
            }
            if (command.equals("list")) {
                listCommands();
            } else {
                commands[totalCommands] = command;
                totalCommands++;
                printString("added: " + command);
            }
        }
    }
}
