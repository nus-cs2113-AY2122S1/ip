import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    final static String HOR_LINE = "_".repeat(30);
    private static Task[] storedTasks = new Task[100];
    private static int additions = 0;
    private static int mode = 0;

    public static void greet() {
        System.out.println("\t" + HOR_LINE);
        System.out.println("\tHi there! I'm Duke\n" +
                "\tWhat can I do for you today?");

        // Mode Selection
        System.out.println("\t* Enter 1 for Echo mode.\n " +
                "\t* Enter 2 for Task mode.");
        Scanner inp = new Scanner(System.in);
        mode = inp.nextInt();
        System.out.println("\t" + HOR_LINE + System.lineSeparator());
        if (mode == 1) {
            System.out.println("ECHO MODE - Commands entered will be echoed back.");
        }
        else if (mode == 2) {
            System.out.println("TASK MODE - Enter items to include in to-do list.");
        }
        else {
            System.out.println("ERROR. PLEASE RUN AGAIN AND SELECT RIGHT MODE.\n");
            mode = 0;
        }
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

            // Switch to Task mode if user types in "change"
            if (cmdLowerC.equals("change")) {
                System.out.println("\t" + HOR_LINE);
                mode = 2;
                System.out.println("TASK MODE ENTERED.");
                System.out.println("\t" + HOR_LINE + System.lineSeparator());
                addText();
                return;
            }

            echoCommand(command);
            command = in.nextLine();
            cmdLowerC = command.toLowerCase();
        }
    }

    public static void printList() {
            System.out.println("\t" + HOR_LINE);
            System.out.println("\tCURRENT ADDED LIST");
            for (int i = 0; i < additions; i++) {
                System.out.println("\t" + storedTasks[i].getStatusIcon() + storedTasks[i].description);
            }
            System.out.println("\t" + HOR_LINE + System.lineSeparator());
    }

    public static void addText() {
        Scanner in = new Scanner(System.in);
        String toAdd = in.nextLine();
        String textLowerC = toAdd.toLowerCase();

        while (!textLowerC.equals("bye") & !textLowerC.equals("list") & !textLowerC.equals("change")) {
            if (textLowerC.startsWith("completed ") | textLowerC.startsWith("done ")) {
                int taskNo = Integer.parseInt(toAdd.replaceAll("[^0-9]", "")) - 1;
                storedTasks[taskNo].markAsDone();
                // Print response when task marked done.
                System.out.println("\t" + HOR_LINE);
                System.out.printf("\tThat's great! %s has been checked as completed!\n", storedTasks[taskNo].description);
                System.out.println("\t" + HOR_LINE + System.lineSeparator());
            }
            else if (textLowerC.startsWith("clear ") | textLowerC.startsWith("remove ")) {
                int taskNo = Integer.parseInt(toAdd.replaceAll("[^0-9]", "")) - 1;
                System.out.println("\t" + HOR_LINE);
                System.out.printf("\t%s removed from list!\n", storedTasks[taskNo].description);
                System.out.println("\t" + HOR_LINE + System.lineSeparator());
                System.arraycopy(storedTasks,taskNo + 1, storedTasks, taskNo, additions - taskNo);
                additions--;
                printList();
            }
            else {
                storedTasks[additions] = new Task(toAdd);
                System.out.println("\t" + HOR_LINE);
                System.out.println("\tAdded: " + toAdd);
                System.out.println("\t" + HOR_LINE + System.lineSeparator());
                additions++;
            }

            toAdd = in.nextLine();
            textLowerC = toAdd.toLowerCase();
        }


        if (textLowerC.equals("list")) {
            printList();
            addText();
        }

        // Switch to Echo mode if user types in "change"
        if (textLowerC.equals("change")) {
            System.out.println("\t" + HOR_LINE);
            mode = 1;
            System.out.println("ECHO MODE ENTERED.");
            System.out.println("\t" + HOR_LINE + System.lineSeparator());
            echo();
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
            // Return back to previous mode since user is not exiting.
            if (mode == 1) {
                echo();
            }
            else if (mode == 2) {
                addText();
            }
            exit();
        }
    }

    public static void main(String[] args) {
        String logo = "#\t\t ____        _        \t\t\t\t#\n"
                + "#\t\t|  _ \\ _   _| | _____ \t\t\t\t#\n"
                + "#\t\t| | | | | | | |/ / _ \\\t\t\t\t#\n"
                + "#\t\t| |_| | |_| |   <  __/\t\t\t\t#\n"
                + "#\t\t|____/ \\__,_|_|\\_\\___|\t\t\t\t#";
        System.out.println("#".repeat(45));
        System.out.println("#\tHello from \t\t\t\t\t\t\t\t#");
        System.out.println(logo);
        System.out.println("# \t\t\t\t\t\t\t\t\t\t\t#");
        System.out.println("#\t +\"\"\"\"\"+ " + "\t +\"\"\"\"\"+ " + "\t +\"\"\"\"\"+ \t\t#");
        System.out.println("#\t[| o o |]" + "\t[| o o |]" + "\t[| o o |]\t\t#");
        System.out.println("#\t |  ^  | " + "\t |  ^  | " + "\t |  ^  | \t\t#");
        System.out.println("#\t | '-' | " + "\t | '-' | " + "\t | '-' | \t\t#");
        System.out.println("#\t +-----+ " + "\t +-----+ " + "\t +-----+ \t\t#");
        System.out.println("#".repeat(45));

        // Actions
        greet();
        if (mode == 1) {
            echo();
        }
        else if (mode == 2) {
            addText();
        }

        if (mode != 0) {
            exit();
        }

        // The end.
        System.out.println("===== PROGRAM ENDED =====");
    }
}
