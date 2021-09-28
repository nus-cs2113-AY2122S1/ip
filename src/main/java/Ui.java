import java.io.IOException;

public class Ui {
    private static final String HOR_LINE = "_".repeat(60);
    /** mode is 1 if in ECHO mode and 2 if in TASK mode. */
    protected static int mode = 0;
    /** Saves latest tasks list when switching to ECHO mode. */
    protected static TaskList previousTL;
    /** Storage object created at startup. */
    protected static Storage localStorage;

    public final int ECHO_MODE = 1;
    public final int TASK_MODE = 2;

    private static final String TASK_WELCOME_MSG = "\tTASK MODE - Enter items to include in to-do list.\n";
    private static final String ECHO_WELCOME_MSG = "\tECHO MODE - Commands entered will be echoed back.\n";

    /**
     * Prints welcome message for mode entered.
     *
     * @param modeSelected ECHO_MODE or TASK_MODE.
     */
    public void printModeEntered(int modeSelected) {
        if (modeSelected == ECHO_MODE) {
            System.out.println(ECHO_WELCOME_MSG);
        }
        else if (modeSelected == TASK_MODE) {
            System.out.println(TASK_WELCOME_MSG);
        }
    }

    /**
     * Introduces chatbot and asks user for preferred mode and enters that mode.
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
     * Prints Lennox chatbot logo.
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

    /**
     * Executes ECHO mode, where commands of user are echoed back.
     * When "switch" is typed in by user, switches program to Task mode.
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

    /**
     * Prints acknowledgement message that item has been added to list
     * and also mentions the total no. of tasks in list currently.
     *
     * @param currTask Task object that was just created after user input.
     * @param tasksList TaskList object in which task are stored in.
     */
    public static void printAddedResponse(Task currTask, TaskList tasksList) {
        System.out.println("\t" + HOR_LINE);
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t" + currTask.getTypeIcon() + currTask.getStatusIcon() + currTask.description);
        System.out.println("\tNow there are " + tasksList.getLength() + " tasks in the list.");
        System.out.println("\t" + HOR_LINE + System.lineSeparator());
    }

    /**
     * Prints confirmation message that Task has been marked complete.
     *
     * @param taskNo Index at which task appears in tasksList.
     * @param tasksList TaskList object in which task are stored in.
     */
    public void printTaskCompleteResponse(int taskNo, TaskList tasksList) {
        System.out.println("\t" + HOR_LINE);
        System.out.printf("\tThat's great! %s has been checked as completed!\n", tasksList.atIndex(taskNo).description);
        System.out.println("\t" + HOR_LINE + System.lineSeparator());
    }

    /**
     * Executes Task mode. Parses user command to detemine appropriate
     * action.
     *
     * @param tasks TaskList object in which task are stored in.
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
            // Update mode to ECHO mode.
            mode = ((SwitchCommand)c).toEcho;
        }

        try {
            // Save current task objects in list to local file.
            localStorage.saveToFile(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (mode == TASK_MODE & !(c instanceof ExitCommand)) {
            // Continue to run TASK mode if not exiting.
            runTaskMode(localStorage.getUpdatedTasks());
        } else if (!(c instanceof ExitCommand)){
            // Store current TaskList object so as to load back when switched back to TASK mode.
            previousTL = tasks;
            // Switch to ECHO mode.
            switchMode(mode);
        }
    }

    /**
     * Prints goodbye message if user confirms to exit.
     * Else, prints out message for user to continue with program.
     *
     * @param isExit true if user confirms to exit, false otherwise.
     */
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
     * Clarifies if user wants to exit and exits program.
     * If no, gets user back to Task/Echo mode user was in.
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
            // Clarify and re-enter exit scope.
            System.out.println("\tSorry, is it a y(es) or a n(o)?");
            exit();
        }
    }

    /**
     * Returns mode in which program is currently operating.
     *
     * @return mode 1 for ECHO mode or 2 for TASK mode.
     */
    public int getMode() {
        return mode;
    }

    public Ui(Storage tasksStorage) {
        localStorage = tasksStorage;
    }
}
