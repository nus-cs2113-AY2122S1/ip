public class Ui {

    private final static String LINES = "    ____________________________________________________________";
    private final static String TAB = "    ";
    private final static String MESSAGE_GREETING = TAB + "Hello... I'm Sadge Duke\n    What can I do for you? :(";
    private final static String MESSAGE_GOODBYE = TAB + "Please don't go... I'll miss you...";

    private final static String MESSAGE_INVALID_GENERAL = TAB + "I'm really sorry... This is an invalid input...";
    private final static String MESSAGE_INVALID_DONE = TAB + "I'm sorry... This is an incorrect done input...\n"
            + TAB + "Please use 'list' to see what number can be used...";
    private final static String MESSAGE_INVALID_DELETE = TAB + "Why would you delete a task... Your delete statement "
            + "is an "
            + "incorrect input too...";
    private final static String MESSAGE_INVALID_DATE = TAB + "Your date is invalid... Please use this format: "
            + "YYYY-MM-DD...";
    private final static String MESSAGE_INVALID_TASK = TAB + "Your task is really weird... I don't think I like it...";

    private final static String MESSAGE_CANNOT_FIND = TAB + "Unfortunately... There aren't tasks that "
            + "have what you are looking for...";
    private final static String MESSAGE_CAN_FIND = TAB + "Yay... These tasks match your find...";

    private final static String MESSAGE_FILE_CORRUPTED = TAB + "Oh no... File is corrupted, Please go fix it...";

    public Ui() {
    }

    /**
     * Prints the welcome banner at the start of the program.
     */
    public static void printWelcomeBanner() {
        //String sadge = ":( :( :( :( :( :( :( :( :( :( :( :(\n"
        //        + "D: D: D: D: D: D: D: D: D: D: D: D:\n"
        //        + ":( :( :( :( :( :( :( :( :( :( :( :(\n"
        //        + "D: D: D: D: D: D: D: D: D: D: D: D:\n"
        //        + ":( :( :( :( :( :( :( :( :( :( :( :(\n"
        //        + "D: D: D: D: D: D: D: D: D: D: D: D:\n";
        String sadge = "     .-\"\"\"\"\"\"-.\n"
                + "   .'          '.\n"
                + "  /   O      O   \\\n"
                + " :    '      '    :\n"
                + " |    '      '    |   SADGE DUKE\n"
                + " :    .------.    :\n"
                + "  \\  '--------'  /\n"
                + "   '.          .'\n"
                + "     '-......-'";

        System.out.println("Hello from\n" + sadge);
        System.out.println(LINES);
        System.out.println(MESSAGE_GREETING);
        System.out.println(LINES);
    }

    /**
     * Pads output with a fixed set of lines.
     */
    public static void padLines() {
        System.out.println(LINES);
    }

    /**
     * Prints this message when a general invalid input is given.
     */
    public static void printGeneralInvalidInput() {
        System.out.println(MESSAGE_INVALID_GENERAL);
    }

    /**
     * Prints all tasks given in a TaskList with numerical ordering.
     *
     * @param tasks the TaskList of all the tasks to be printed out.
     */
    public static void printList(TaskList tasks) {
        for (int index = 0; index < tasks.getLength(); index++) {
            System.out.format(TAB + "% 3d.", index + 1);
            System.out.println(tasks.getItemFromIndex(index));
        }
    }

    /**
     * Prints all tasks given in a TaskList with numerical ordering.
     *
     * @param task the task that has been added.
     * @param size the total number of tasks currently.
     */
    public static void printAddedMessage(Task task, int size) {
        System.out.println(TAB + "Okay... I guess I'll add this task... ");
        System.out.println(TAB + TAB + task);
        System.out.println(TAB + String.format("Now you have %d tasks in the list...", size));
    }

    /**
     * Prints the goodbye message when user inputs bye.
     */
    public static void sayGoodbye() {
        System.out.println(MESSAGE_GOODBYE);
    }

    /**
     * Prints the done message together with the task that has been marked as done by the user.
     *
     * @param task the task that has been marked done by the user.
     */
    public static void printDoneTask(Task task) {
        System.out.print(TAB + "Nice... I guess I will mark this task as done...:"
                + System.lineSeparator() + TAB + TAB);
        System.out.println(task);
    }

    /**
     * Prints that the task has been deleted, and shows the total tasks left.
     *
     * @param task   the task that has been deleted by the user.
     * @param length the remaining number of tasks.
     */
    public static void printDeleteTask(Task task, int length) {
        System.out.print(TAB + "Are you sure about this? I'll just delete this anyway..."
                + System.lineSeparator() + TAB + TAB);
        System.out.println(task);
        System.out.println(TAB + String.format("Now you have %d tasks in the list...", length));
    }

    /**
     * Prints this statement when the done statement is invalid.
     */
    public static void printInvalidDoneStatement() {
        System.out.println(MESSAGE_INVALID_DONE);
    }

    /**
     * Prints this statement when the delete statement is invalid.
     */
    public static void printInvalidDeleteStatement() {
        System.out.println(MESSAGE_INVALID_DELETE);
    }

    /**
     * Prints this statement when the task input statement is invalid.
     */
    public static void printInvalidTaskStatement() {
        System.out.println(MESSAGE_INVALID_TASK);
    }

    /**
     * Prints this statement when the date input is invalid for deadline/event tasks.
     */
    public static void printInvalidDateStatement() {
        System.out.println(MESSAGE_INVALID_DATE);
    }

    /**
     * Prints this statement when the find statement is invalid.
     */
    public static void printCannotFind() {
        System.out.println(MESSAGE_CANNOT_FIND);
    }

    /**
     * Prints all tasks that have been found with words from user input, with numerical ordering.
     *
     * @param tempTasks the TaskList of all the tasks that have been found.
     */
    public static void printFind(TaskList tempTasks) {
        System.out.println(MESSAGE_CAN_FIND);
        for (int index = 0; index < tempTasks.getLength(); index++) {
            System.out.format(TAB + "% 3d.", index + 1);
            System.out.println(tempTasks.getItemFromIndex(index));
        }
    }

    /**
     * Prints this statement when the initial file of tasks being read is corrupted.
     */
    public static void printFileCorrupted() {
        System.out.println(MESSAGE_FILE_CORRUPTED);
    }
}
