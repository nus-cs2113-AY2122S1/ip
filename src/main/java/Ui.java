import java.io.IOException;

public class Ui {
    private static final String HOR_LINE = "_".repeat(60);
    protected static int mode = 0;
    protected static TaskList previousTL;
    protected static Storage localStorage;

    public final int ECHO_MODE = 1;
    public final int TASK_MODE = 2;

    private static final String TASK_WELCOME_MSG = "\tTASK MODE - Enter items to include in to-do list.\n";
    private static final String ECHO_WELCOME_MSG = "\tECHO MODE - Commands entered will be echoed back.\n";

    public void printModeEntered(int modeSelected) {
        if (modeSelected == 1) {
            System.out.println(ECHO_WELCOME_MSG);
        }
        else if (modeSelected == 2) {
            System.out.println(TASK_WELCOME_MSG);
        }
    }

    /**
     * Function introduces chatbot and asks user for preferred mode and enters that mode.
     * Mode 1 - Echo; 2 - Task; Otherwise - Returns Error Message and stops program.
     */
    public void greet() {
        System.out.println("\t" + HOR_LINE);
        System.out.println("\tHi there! I'm Lennox - your personal chatbot\n" +
                "\tWhat can I do for you today?");

        // Mode Selection
        System.out.println("\t* Enter 1 for Echo mode.\n " +
                "\t* Enter 2 for Task mode.");
        try {
            mode = Parser.readModeSelection();
        } catch (DukeException e) {
            e.printErrorMessage();
        }
        System.out.println("\t" + HOR_LINE);
    }

    /**
     * Function prints Lennox chatbot logo.
     */
    public void printLogo() {
        String logo = "\t,--.\n" +
                "\t|  |   ,---.,--,--,,--,--, ,---,--.  ,--\n" +
                "\t|  |  | .-. |      |      | .-. \\  `'  /\n" +
                "\t|  '--\\   --|  ||  |  ||  ' '-' /  /.  \\\n" +
                "\t`-----'`----`--''--`--''--'`---'--'  '--";
        System.out.println("#".repeat(57));
        System.out.println("\tHello from \t\t\t\t\t");
        System.out.println(logo);
        System.out.println("\t\t +\"\"\"\"\"+ " + "\t +\"\"\"\"\"+ " + "\t +\"\"\"\"\"+ ");
        System.out.println("\t\t[| o o |]" + "\t[| o o |]" + "\t[| o o |]");
        System.out.println("\t\t |  ^  | " + "\t |  ^  | " + "\t |  ^  | ");
        System.out.println("\t\t | '-' | " + "\t | '-' | " + "\t | '-' | ");
        System.out.println("\t\t +-----+ " + "\t +-----+ " + "\t +-----+ ");
        System.out.println("#".repeat(57));
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
//
    /**
     * Function switches between Echo and Task modes.
     *
     * @param newMode Code for new mode (1 - Echo; 2 - Task)
     */
    public void switchMode(int newMode) {
        mode = newMode;
        System.out.println("\t" + HOR_LINE);
        if (newMode == ECHO_MODE) {
            printModeEntered(ECHO_MODE);
            runEchoMode();
        } else {
            printModeEntered(TASK_MODE);
            runTaskMode(previousTL);
        }
    }
//
    /**
     * Executes Echo mode, where commands of user are echoed back.
     * When "change" is typed in by user, switches program to Task mode.
     */
    public void runEchoMode() {
        String echoString = null;
        try {
            echoString = Parser.parseEchoCommand();
        } catch (DukeException e) {
            e.printErrorMessage();
        }

        if (echoString.equalsIgnoreCase("switch")) {
            mode = TASK_MODE;
            switchMode(mode);
        } else if (!echoString.equalsIgnoreCase("bye") & !echoString.equalsIgnoreCase("exit")) {
            echoCommand(echoString);
            runEchoMode();
        }
    }
//
    /**
     * Prints acknowledgement message that item has been added to list
     * and also mentions the total no. of tasks in list currently.
     *
     * @param currTask Task object that was just created after user input.
     */
    public static void printAddedResponse(Task currTask, TaskList tasksList) {
        System.out.println("\t" + HOR_LINE);
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t" + currTask.getTypeIcon() + currTask.getStatusIcon() + currTask.description);
        System.out.println("\tNow there are " + tasksList.getLength() + " tasks in the list.");
        System.out.println("\t" + HOR_LINE + System.lineSeparator());
    }

    public void printTaskCompleteResponse(int taskNo, TaskList tasksList) {
        System.out.println("\t" + HOR_LINE);
        System.out.printf("\tThat's great! %s has been checked as completed!\n", tasksList.atIndex(taskNo).description);
        System.out.println("\t" + HOR_LINE + System.lineSeparator());
    }
//
    /**
     * Executes Task mode, where user's commands are added into to-do list.
     * When "list" entered, prints out current to-do list.
     * When "completed <task id>" or "done <task id>" entered, updates that task status.
     * When "remove <task id>" or "delete <task id>" entered, removes that task from list.
     * When "change" is typed in by user, switches program to Echo mode.
     */
    public void runTaskMode(TaskList tasks) {
        Command c = Parser.parseTaskCommand();
        c.execute(tasks);

        if (c instanceof AddCommand) {
            if (((AddCommand)c).newTask != null) {
                printAddedResponse(((AddCommand) c).newTask, tasks);
            }
        } else if (c instanceof DoneCommand) {
            int idx = ((DoneCommand) c).taskIndex;
            if (idx >= 0 & idx < tasks.getLength()) {
                printTaskCompleteResponse(((DoneCommand) c).taskIndex, tasks);
            }
        } else if (c instanceof SwitchCommand) {
            mode = ((SwitchCommand)c).toEcho;
        }

        try {
            localStorage.saveToFile(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (mode == TASK_MODE & !(c instanceof ExitCommand)) {
            runTaskMode(localStorage.getUpdatedTasks());
        } else if (!(c instanceof ExitCommand)){
            previousTL = tasks;
            switchMode(mode);
        }
    }

    public void printExitResponse(boolean isExit) {
        if (isExit) {
            // The end.
            System.out.println("\t" + HOR_LINE);
            System.out.println("\tBye. Hope to see you again soon!");
            System.out.println("\t" + HOR_LINE + System.lineSeparator());
            System.out.println("===== PROGRAM ENDED =====");
        } else {
            System.out.println("\n\tOk that's great! Continue keying in commands. :)");
            System.out.println("\t" + HOR_LINE + System.lineSeparator());
        }
    }

    /**
     * Double confirms if user wants to exit.
     * If no, gets user back to Task/Echo mode user was in.
     * If yes, ends program with farewell message.
     */
    public void exit() {
        String preference = Parser.parseExitPref();
        if (preference.equalsIgnoreCase("n")) {
            printExitResponse(false);
            // Return back to previous mode since user is not exiting.
            if (mode == ECHO_MODE) {
                runEchoMode();
            } else if (mode == TASK_MODE) {
                runTaskMode(previousTL);
            }
            exit();
        } else if (!preference.equalsIgnoreCase("y")){
            System.out.println("\tSorry, is it a y(es) or a n(o)?");
            exit();
        }
    }

    public int getMode() {
        return mode;
    }

    public Ui(Storage tasksStorage) {
        localStorage = tasksStorage;
    }
}
