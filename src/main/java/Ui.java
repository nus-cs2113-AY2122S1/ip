public class Ui {

    private final static String LINES = "    ____________________________________________________________";
    private final static String TAB = "    ";
    private final static String GREETING = TAB + "Hello... I'm Sadge Duke\n    What can I do for you? :(";
    private final static String GOODBYE = TAB + "Please don't go... I'll miss you...";
    private final static String INVALID_GENERAL = TAB + "I'm really sorry... This is an invalid input...";
    private final static String INVALID_DONE = TAB + "I'm sorry... This is an incorrect done input...\n"
            + TAB + "Please use 'list' to see what number can be used...";
    private final static String INVALID_DELETE = TAB + "Why would you delete a task... Your delete statement is an "
            + "incorrect input too...";
    private final static String INVALID_DATE = TAB + "Your date is invalid... Please use this format: YYYY-MM-DD...";
    private final static String INVALID_TASK = TAB + "Your task is really weird... I don't think I like it...";
    private final static String CANNOT_FIND = TAB + "Unfortunately... There aren't tasks that "
            + "have what you are looking for...";
    private final static String CAN_FIND = TAB + "Yay... These tasks match your find...";
    private final static String FILE_CORRUPTED = TAB + "Oh no... File is corrupted, Please go fix it...";

    public Ui() {
    }

    public static void printWelcomeBanner() {
        String sadge = ":( :( :( :( :( :( :( :( :( :( :( :(\n"
                + "D: D: D: D: D: D: D: D: D: D: D: D:\n"
                + ":( :( :( :( :( :( :( :( :( :( :( :(\n"
                + "D: D: D: D: D: D: D: D: D: D: D: D:\n"
                + ":( :( :( :( :( :( :( :( :( :( :( :(\n"
                + "D: D: D: D: D: D: D: D: D: D: D: D:\n";

        String text = "SADGE DUKE";
        System.out.println("Hello from\n" + sadge + text);
        System.out.println(LINES);
        System.out.println(GREETING);
        System.out.println(LINES);
    }

    public static void padLines() {
        System.out.println(LINES);
    }

    public static void printGeneralInvalidInput() {
        System.out.println(INVALID_GENERAL);
    }


    public static void printList(TaskList list) {
        for (int index = 0; index < list.getLength(); index++) {
            System.out.format(TAB + "% 3d.", index + 1);
            System.out.println(list.getItemFromIndex(index));
        }
    }

    public static void printAddedMessage(Task item, int size) {
        System.out.println(TAB + "Okay... I guess I'll add this task... ");
        System.out.println(TAB + TAB + item);
        System.out.println(TAB + String.format("Now you have %d tasks in the list...", size));
    }

    public static void sayGoodbye() {
        System.out.println(GOODBYE);
    }

    public static void printDoneTask(Task item) {
        System.out.print(TAB + "Nice... I guess I will mark this task as done...:"
                + System.lineSeparator() + TAB + TAB);
        System.out.println(item);
    }

    public static void printDeleteTask(Task item, int length) {
        System.out.print(TAB + "Are you sure about this? I'll just delete this anyway..."
                + System.lineSeparator() + TAB + TAB);
        System.out.println(item);
        System.out.println(TAB + String.format("Now you have %d tasks in the list...", length));
    }

    public static void printInvalidDoneStatement() {
        System.out.println(INVALID_DONE);
    }

    public static void printInvalidDeleteStatement() {
        System.out.println(INVALID_DELETE);
    }

    public static void printInvalidTaskStatement() {
        System.out.println(INVALID_TASK);
    }

    public static void printInvalidDateStatement() { System.out.println(INVALID_DATE); }

    public static void printCannotFind() { System.out.println(CANNOT_FIND); }


    public static void printFind(TaskList tempTasks) {
        System.out.println(CAN_FIND);
        for (int index = 0; index < tempTasks.getLength(); index++) {
            System.out.format(TAB + "% 3d.", index + 1);
            System.out.println(tempTasks.getItemFromIndex(index));
        }
    }

    public static void printFileCorrupted() {
        System.out.println(FILE_CORRUPTED);
    }
}
