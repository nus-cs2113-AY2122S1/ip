import java.util.Locale;
import java.util.Scanner;

public class Duke {
    final static String HOR_LINE = "_".repeat(30);

    public static void greet() {
        System.out.println("\t" + HOR_LINE);
        System.out.println("\tHi there! I'm Duke\n" +
                "\tWhat can I do for you today?");
        System.out.println("\t" + HOR_LINE + System.lineSeparator());
    }

    public static void echoCommand(String cmd) {
        System.out.println("\t" + HOR_LINE);
        System.out.println("\tYou have entered: " + cmd);
        System.out.println("\t" + HOR_LINE + System.lineSeparator());
    }

    public static void echo() {
        String command;
        Scanner in = new Scanner(System.in);
        command = in.nextLine();
        String cmdLowerC = command.toLowerCase();
        while (!cmdLowerC.equals("bye")) {
            while (command.equals("") | command.equals(" ")) {
                System.out.println("\t" + HOR_LINE);
                System.out.println("\tPlease enter a valid command!");
                System.out.println("\t" + HOR_LINE + System.lineSeparator());
                command = in.nextLine();
            }

            echoCommand(command);
            command = in.nextLine();
            cmdLowerC = command.toLowerCase();
        }
    }

    public static void exit() {
        System.out.print("\tDo you really want to exit chatbot (type y or n)? ");
        Scanner input = new Scanner(System.in);
        String exit_pref = input.nextLine();

        if (exit_pref.equals("y")) {
            System.out.println("\t" + HOR_LINE);
            System.out.println("\tBye. Hope to see you again soon!");
            System.out.println("\t" + HOR_LINE + System.lineSeparator());
        }
        else {
            System.out.println("\n\tOk that's great! Continue keying in commands. :)");
            System.out.println("\t" + HOR_LINE + System.lineSeparator());
            echo();
            exit();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Actions
        greet();
        echo();
        exit();
    }
}
