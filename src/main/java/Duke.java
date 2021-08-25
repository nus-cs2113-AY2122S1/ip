import java.util.Scanner;

public class Duke {

    private static Task[] tasks = new Task[100];
    private static int tasksCount = 0;

    public static void main(String[] args) {
        greetUser();
        executeResponse();
    }

    /**
     * Prints the Duke application logo.
     */
    public static void printDukeLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints a logo of the application's mascot.
     */
    public static void printRimuruLogo() {
        String logo = "                                               *.                               \n"
                + "                     .                          *,,,,,,,,,                      \n"
                + "                   (           ..##%            **********,*,,,                 \n"
                + "               . #. . .........##%. ...... . ..///****************.             \n"
                + "             ...#.......##/...................(/////*****************           \n"
                + "           ..#%....%.........................((//////////////**********         \n"
                + "         ..%..&..%%%.......................(((///////////////////*//*/***       \n"
                + "       ...%......%#....%%&&...............(((///////////////////////////**      \n"
                + "      #%*....%,......&&.,..&&..........,#((///////////////////////////////**    \n"
                + "     ...%%#,.,,...&&#%&&,.......,.....#(((//////////////////////////////////*   \n"
                + "    ......,,,,,&%,,,,,,....,.,,.,,,/#(((//(//////////////////////////////////   \n"
                + "   ......,,,,,,&.&,,,,,,,.,,.,,,,##(((/((((///(//////////////////////////////%  \n"
                + "   ,,,,,,,,,,,&,,,&,,,,,.,,,,,%#(((((((((((///(////////////////////////////&(/  \n"
                + "   ,,,,,,,,,.,,,,,,%,,,,,,,%#((((((((((((((((((((//////////////////////(/&////  \n"
                + "    ,,,,,,,,,,,,,,,,,,,%%#(((((((((((((((((((((((/(///////////////////@&(/////  \n"
                + "      ,,,,,,,,,,,,%%###(((((((((#&@@@@@@@@@@@@@@&@&&&&%%%&#((&///////////////*  \n"
                + "         /(((######((((((((((((((((((((((((((((((((((/((/(((/(///(//////////*   \n"
                + "          ((((((((((((((((((((((((((((((((((((((((((((((////((/////////////*    \n"
                + "           (((((((((((((((((((((((((((((((((((((((((((((((((/(/((/////////*     \n"
                + "            /(((((((((((((((((((((((((((((((((((((((((((((((((/////////*/       \n"
                + "              (/((((((((((((((((((((((((((((((((((((((((((((((/(/////*          \n"
                + "                 (/((((((((((((((((((((((((((((((((((((((((((/////              \n"
                + "                     ./(((((((((((((((((((((((((((((((((////*                   \n"
                + "                             *//(((((((((((((((((//*                            \n";
        System.out.println(logo);
    }

    /**
     * Prints a horizontal separator line.
     */
    public static void printLine() {
        System.out.println("    ────────────────────────────────────────────────────────────");
    }

    /**
     * Prints a message that greets the user.
     */
    public static void greetUser() {
        printRimuruLogo();
        printLine();
        System.out.println("     Welcome to Jura Tempest!\n"
                + "     I'm Rimuru Tempest, pleased to make your acquaintance.\n"
                + "     How can I help you today?");
        printLine();
    }

    /**
     * Prints an exit message.
     */
    public static void exitDuke() {
        printLine();
        System.out.println("     Sayonara. Come visit our country again soon!");
        printLine();
    }

    /**
     * Adds a task to the list of existing tasks.
     *
     * @param item The name of the task to be added.
     */
    public static void addToList(String item) {
        tasks[tasksCount] = new Task(item);
        tasksCount++;
        printLine();
        System.out.println("     NOTICE: Added '" + item + "'");
        printLine();
    }

    /**
     * Marks the task associated with the itemNumber as completed.
     * Prints a message to confirm that the task has been marked as completed.
     *
     * @param itemNumber 1 index greater than the index of the task in the list.
     */
    public static void markAsCompleted(int itemNumber) {
        printLine();
        if (itemNumber > tasksCount || itemNumber < 1) {
            System.out.println("     NOTICE: Invalid task selected.");
        } else {
            tasks[itemNumber - 1].markTaskAsDone();
            System.out.println("     NOTICE: This task is marked as done... \n"
                    + "       [X] "
                    + tasks[itemNumber - 1].getDescription());
        }
        printLine();
    }

    /**
     * Prints all the tasks in the list.
     */
    public static void printList() {
        printLine();
        if (tasksCount == 0) {
            System.out.println("     NOTICE: There are no tasks in your list.");
        } else {
            System.out.println("     NOTICE: Here are the tasks in your list...");
            for (int i = 0; i < tasksCount; i++) {
                System.out.println("     "
                        + (i + 1)
                        + ".["
                        + tasks[i].getStatusIcon()
                        + "] "
                        + tasks[i].getDescription());
            }
        }
        printLine();
    }

    /**
     * Continually waits for input user commands,
     * and executes the corresponding actions,
     * until the exit command is typed.
     */
    public static void executeResponse() {
        String line;
        boolean isExit = false;
        Scanner in = new Scanner(System.in);
        do {
            line = in.nextLine();
            String[] words = line.split(" ");
            switch (words[0]) {
            case "bye":
                exitDuke();
                isExit = true;
                break;
            case "list":
                printList();
                break;
            case "done":
                markAsCompleted(Integer.parseInt(words[1]));
                break;
            default:
                addToList(line);
            }
        } while (!isExit);
    }

}
