import java.util.Scanner;

public class Duke {
    final static String HOR_LINE = "_".repeat(30);
    private static Task[] storedTasks = new Task[100];
    private static int additions = 0;
    private static int mode = 0;

    private final static int ECHO_MODE = 1;
    private final static int TASK_MODE = 2;

    /**
     * Function introduces chatbot and asks user for preferred mode and enters that mode.
     * Mode 1 - Echo; 2 - Task; Otherwise - Returns Error Message and stops program.
     */
    public static void greet() {
        System.out.println("\t" + HOR_LINE);
        System.out.println("\tHi there! I'm Lennox - your personal chatbot\n" +
                "\tWhat can I do for you today?");

        // Mode Selection
        System.out.println("\t* Enter 1 for Echo mode.\n " +
                "\t* Enter 2 for Task mode.");
        Scanner inp = new Scanner(System.in);
        mode = inp.nextInt();
        System.out.println("\t" + HOR_LINE + System.lineSeparator());
        if (mode == ECHO_MODE) {
            System.out.println("\tECHO MODE - Commands entered will be echoed back.");
            startEcho();
        }
        else if (mode == TASK_MODE) {
            System.out.println("\tTASK MODE - Enter items to include in to-do list.");
            startTask();
        }
        else {
            System.out.println("\tERROR. PLEASE RUN AGAIN AND SELECT RIGHT MODE.\n");
            mode = 0;
        }
    }

    /**
     * Function prints Lennox chatbot logo.
     */
    private static void printLogo() {
        String logo = "#\t,--.                                      \t#\n" +
                "#\t|  |   ,---.,--,--,,--,--, ,---,--.  ,--. \t#\n" +
                "#\t|  |  | .-. |      |      | .-. \\  `'  /  \t#\n" +
                "#\t|  '--\\   --|  ||  |  ||  ' '-' /  /.  \\  \t#\n" +
                "#\t`-----'`----`--''--`--''--'`---'--'  '--' \t#";
        System.out.println("#".repeat(49));
        System.out.println("#\tHello from \t\t\t\t\t\t\t\t\t#");
        System.out.println(logo);
        System.out.println("# \t\t\t\t\t\t\t\t\t\t\t\t#");
        System.out.println("#\t\t +\"\"\"\"\"+ " + "\t +\"\"\"\"\"+ " + "\t +\"\"\"\"\"+ \t\t#");
        System.out.println("#\t\t[| o o |]" + "\t[| o o |]" + "\t[| o o |]\t\t#");
        System.out.println("#\t\t |  ^  | " + "\t |  ^  | " + "\t |  ^  | \t\t#");
        System.out.println("#\t\t | '-' | " + "\t | '-' | " + "\t | '-' | \t\t#");
        System.out.println("#\t\t +-----+ " + "\t +-----+ " + "\t +-----+ \t\t#");
        System.out.println("#".repeat(49));
    }

    /**
     * Prints out entered command by user in Echo mode.
     *
     * @param cmd Command entered by user.
     */
    public static void echoCommand(String cmd) {
        System.out.println("\t" + HOR_LINE);
        System.out.println("\tYou have entered: " + cmd);
        System.out.println("\t" + HOR_LINE + System.lineSeparator());
    }

    /**
     * Function switches between Echo and Task modes.
     *
     * @param newMode Code for new mode (1 - Echo; 2 - Task)
     */
    public static void switchMode(int newMode) {
        mode = newMode;
        System.out.println("\t" + HOR_LINE);
        if (newMode == ECHO_MODE) {
            System.out.println("\tECHO MODE ENTERED.");
            System.out.println("\t" + HOR_LINE + System.lineSeparator());
            startEcho();
        }
        else {
            System.out.println("\tTASK MODE ENTERED.");
            System.out.println("\t" + HOR_LINE + System.lineSeparator());
            startTask();
        }
    }

    /**
     * Executes Echo mode, where commands of user are echoed back.
     * When "change" is typed in by user, switches program to Task mode.
     */
    public static void startEcho() {
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
                switchMode(TASK_MODE);
                return;
            }

            echoCommand(command);
            command = in.nextLine();
            cmdLowerC = command.toLowerCase();
        }
    }

    // Prints task list.
    public static void printList() {
            System.out.println("\t" + HOR_LINE);
            System.out.println("\tCURRENT ADDED LIST");
            for (int i = 0; i < additions; i++) {
                System.out.println("\t" + (i + 1) + ". " + storedTasks[i].getTypeIcon() +
                        storedTasks[i].getStatusIcon() + storedTasks[i].description);
            }
            System.out.println("\t" + HOR_LINE + System.lineSeparator());
    }

    /**
     * Prints acknowledgement message that item has been added to list
     * and also mentions the total no. of tasks in list currently.
     *
     * @param currTask Task object that was just created after user input.
     */
    private static void printAddedResponse(Task currTask) {
        System.out.println("\t" + HOR_LINE);
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t" + currTask.getTypeIcon() + currTask.getStatusIcon() + currTask.description);
        System.out.println("\tNow there are " + (additions + 1) + " tasks in the list.");
        System.out.println("\t" + HOR_LINE + System.lineSeparator());
    }

    /**
     * Depending on the starting keyword present in user input, creates respective
     * Task subclass object and stores it inside the storedTasks array.
     *
     * @param userInput String command input by user.
     */
    public static void createTask(String userInput) {
        if (userInput.startsWith("event ")) {
            storedTasks[additions] = new Event(userInput);
        }
        else if (userInput.startsWith("deadline")) {
            storedTasks[additions] = new Deadline(userInput);
        }
        else if (userInput.startsWith("todo ")) {
            storedTasks[additions] = new Todo(userInput);
        }

        printAddedResponse(storedTasks[additions]);
        additions++;
    }

    /**
     * Executes Task mode, where user's commands are added into to-do list.
     * When "list" entered, prints out current to-do list.
     * When "completed <task id>" or "done <task id>" entered, updates that task status.
     * When "remove <task id>" or "clear <task id>" entered, removes that task from list.
     * When "change" is typed in by user, switches program to Echo mode.
     */
    public static void startTask() {
        Scanner in = new Scanner(System.in);
        String toAdd = in.nextLine();
        String textLowerC = toAdd.toLowerCase();

        while (!textLowerC.equals("bye") & !textLowerC.equals("list") & !textLowerC.equals("change")) {
            defaultTaskModeBehavior(toAdd, textLowerC);
            toAdd = in.nextLine();
            textLowerC = toAdd.toLowerCase();
        }

        if (textLowerC.equals("list")) {
            printList();
            startTask();
        }

        // Switch to Echo mode if user types in "change"
        if (textLowerC.equals("change")) {
            switchMode(ECHO_MODE);
        }
    }

    /**
     * Checks different scenarios for input commands of user in Task mode and
     * does the necessary action.
     *
     * @param toAdd String input by user.
     * @param textLowerC String input by user converted to lower cases for checking.
     */
    private static void defaultTaskModeBehavior(String toAdd, String textLowerC) {
        // Mark task as complete with an X.
        if (textLowerC.startsWith("completed ") | textLowerC.startsWith("done ")) {
            int taskNo = Integer.parseInt(toAdd.replaceAll("[^0-9]", "")) - 1;
            storedTasks[taskNo].setDone();
            // Print response when task marked done.
            System.out.println("\t" + HOR_LINE);
            System.out.printf("\tThat's great! %s has been checked as completed!\n", storedTasks[taskNo].description);
            System.out.println("\t" + HOR_LINE + System.lineSeparator());
        }
        // Remove task from list.
        else if (textLowerC.startsWith("clear ") | textLowerC.startsWith("remove ")) {
            int taskNo = Integer.parseInt(toAdd.replaceAll("[^0-9]", "")) - 1;
            System.out.println("\t" + HOR_LINE);
            System.out.printf("\t%s removed from list!\n", storedTasks[taskNo].description);
            System.out.println("\t" + HOR_LINE + System.lineSeparator());
            System.arraycopy(storedTasks,taskNo + 1, storedTasks, taskNo, additions - taskNo);
            additions--;
            printList();
        }
        // Create a new task if it does not exist in list
        else {
            createTask(toAdd);
        }
    }

    /**
     * Double confirms if user wants to exit.
     * If no, gets user back to Task/Echo mode user was in.
     * If yes, ends program with farewell message.
     */
    public static void exit() {
        System.out.print("\tDo you really want to exit chatbot (type y or n)? ");
        Scanner input = new Scanner(System.in);
        String exit_pref = input.nextLine();

        // User wishes to leave program.
        if (exit_pref.equals("y")) {
            System.out.println("\t" + HOR_LINE);
            System.out.println("\tBye. Hope to see you again soon!");
            System.out.println("\t" + HOR_LINE + System.lineSeparator());
        }
        else {
            System.out.println("\n\tOk that's great! Continue keying in commands. :)");
            System.out.println("\t" + HOR_LINE + System.lineSeparator());
            // Return back to previous mode since user is not exiting.
            if (mode == ECHO_MODE) {
                startEcho();
            }
            else if (mode == TASK_MODE) {
                startTask();
            }
            exit();
        }
    }

    public static void main(String[] args) {
        // Actions
        printLogo();
        greet();
        if (mode != 0) {
            exit();
        }

        // The end.
        System.out.println("===== PROGRAM ENDED =====");
    }

}
