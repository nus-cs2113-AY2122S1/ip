import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    /** count tracker for number of tasks in array */
    private static int taskCount = 0;

    /** count tracker for number of uncompleted tasks in array */
    private static int numOfUncompletedTasks = 0;

    /** Array of tasks */
    private static final Task[] tasks = new Task[100];

    /** Long line separator*/
    private static final String lineSeparator = ("_____________________________________________________");

    public static void printSeparator() {
        System.out.println(lineSeparator);
    }

    public static void greeting() {
        printSeparator();
        System.out.println("Hello! I'm Duke the Dancing Dragon.");
        System.out.println("Anything I can help you with, young Padawan?");
        printSeparator();
    }

    public static void goodbyeMessage() {
        System.out.println("It's over Anakin... I can finally eat my lun-");
        System.out.println(lineSeparator);
    }

    public static void printDone(Task arg) {
        System.out.println("You have marked item " + arg.description + " as done:");
        System.out.println(arg.getStatusIcon() + " " + arg.description);
    }

    /**
     * Returns the list number which is assigned to the task.
     *
     * @param arg user input that contains word [done].
     * @return task number.
     */
    public static int getTaskNum(String arg) {
        String[] words = arg.trim().split("[\\s]+");
        return Integer.parseInt(words[1]);
    }

    /**
     * Returns the description of the task only, without the date or the keyword.
     *
     * @param query user raw data input.
     * @return description of task.
     */
    public static String getQueryDescription(String query) {
        String[] words = query.trim().split("[\\s]+");
        String[] allButFirstWord = Arrays.copyOfRange(words, 1, words.length);
        StringBuilder sentenceAfterDeletion = new StringBuilder();
        for (String word : allButFirstWord) {
            if (word.contains("/")) {
                break;
            } else {
                sentenceAfterDeletion.append(word).append(" ");
            }
        }
        return sentenceAfterDeletion.toString();
    }

    /**
     * Returns date value for tasks which need a date input field.
     * If user does not have proper date formatting, (i.e. '/by' or '/at') this function returns an empty string.
     *
     * @param query user raw data input.
     * @return date value
     */
    public static String getDate(String query) {
        int slashPosition = query.indexOf("/");
        if (slashPosition == -1) {
            return "";
        } else {
            int datePosition = slashPosition + 3;
            return query.substring(datePosition).trim();
        }
    }

    /**
     * Return void.
     * Function is responsible for printing out the whole task list of the user.
     *
     * @param tasks list of tasks input by user
     */
    public static void printList(Task[] tasks) {
        int count = 0;
        System.out.println("Here is your list:");
        for (Task item : tasks) {
            if (tasks[count] != null) {
                count++;
                System.out.println(count + ". " + item);
            }
        }
    }

    public static boolean hasListKeyword(String arg) {
        return arg.trim().matches("^list$");
    }

    public static boolean hasDoneKeyword(String arg) {
        return arg.trim().matches("^[done]+\\s+[0-9]+$");
    }

    public static boolean hasTodoKeyword(String arg) {
        return arg.trim().toLowerCase().contains("todo");
    }

    public static boolean hasDeadlineKeyword(String arg) {
        return arg.trim().toLowerCase().contains("deadline");
    }

    public static boolean hasEventKeyword(String arg) {
        return arg.trim().toLowerCase().contains("event");
    }

    /**
     * Returns the required value for keyword which is the first word keyed in by user.
     *
     * @param query user raw data input.
     * @return keyword value from Keyword enum class.
     */
    public static Keyword getKeywordStatus(String query) {
        Keyword keyword;
        if (hasDoneKeyword(query)) {
            keyword = Keyword.DONE_TASK;
        } else if (hasTodoKeyword(query)) {
            keyword = Keyword.TODO_TASK;
        } else if (hasDeadlineKeyword(query)) {
            keyword = Keyword.DEADLINE_TASK;
        } else if (hasEventKeyword(query)) {
            keyword = Keyword.EVENT_TASK;
        } else if (hasListKeyword(query)) {
            keyword = Keyword.LIST_ITEMS;
        } else if (query.trim().equals("bye")) {
            keyword = Keyword.GOODBYE_KEYWORD;
        } else {
            keyword = Keyword.NO_KEYWORD;
        }
        return keyword;
    }

    public static void waitForQuery() {
        String query = "";
        Scanner userInput = new Scanner(System.in);

        while (!query.equals("bye")) {
            System.out.print("=>");
            if (userInput.hasNextLine()) {
                query = userInput.nextLine();
            }
            addTask(query);
        }
    }

    /**
     * Returns void.
     * Function is responsible for adding different Tasks to the task list.
     *
     * @param query user raw data input
     * @throws NullPointerException if user keys in done [number] when there is no such task.
     */
    public static void addTask(String query) throws NullPointerException {
        Keyword keyword = getKeywordStatus(query);
        switch (keyword) {
        case DONE_TASK:
            try {
                int taskNumber = getTaskNum(query);
                tasks[taskNumber - 1].markAsDone();
                printDone(tasks[taskNumber - 1]);
                numOfUncompletedTasks--;
                System.out.println("Total unchecked items in your list: " + numOfUncompletedTasks);
            } catch (NullPointerException exception) {
                System.out.println("There is no such task number...");
            }
            break;
        case TODO_TASK:
            tasks[taskCount] = new Todo(getQueryDescription(query));
            taskCount++;
            numOfUncompletedTasks++;
            System.out.println("Added a Todo Task: " + getQueryDescription(query));
            System.out.println("Total unchecked items in your list: " + numOfUncompletedTasks);
            break;
        case EVENT_TASK:
            if (getDate(query).equals("")) {
                System.out.println("You did not key in any date for your event or deadline.");
                System.out.println("Did you forget to add in the '/at' again?");
            } else {
                tasks[taskCount] = new Event(getQueryDescription(query), getDate(query));
                taskCount++;
                numOfUncompletedTasks++;
                System.out.println("Added an Event Task: " + getQueryDescription(query));
                System.out.println("Total unchecked items in your list: " + numOfUncompletedTasks);
            }
            break;
        case DEADLINE_TASK:
            if (getDate(query).equals("")) {
                System.out.println("You did not key in any date for your event or deadline.");
                System.out.println("Did you forget to add in the '/by' again?");
            } else {
                tasks[taskCount] = new Deadline(getQueryDescription(query), getDate(query));
                taskCount++;
                numOfUncompletedTasks++;
                System.out.println("Added a Todo Task: " + getQueryDescription(query));
                System.out.println("Total unchecked items in your list: " + numOfUncompletedTasks);
            }
            break;
        case LIST_ITEMS:
            printList(tasks);
            break;
        case NO_KEYWORD:
            System.out.println("⣿⣿⣿⣿⣿ You have to input <todo>, <deadline> or <event> first! ⣿⣿⣿⣿⣿");
            waitForQuery();
            break;
        case GOODBYE_KEYWORD:
            goodbyeMessage();
            break;
        }
    }

    /**
     * Main function
     */
    public static void main(String[] args) {
        String face = "⣿⣿⡇⠄⣼⣿⣿⠿⣿⣿⣿⣦⠘⣿⣿⣿⣿⣿⠏⣰⣿⡿⠟⢻⣿⣿⣷⡀⠸⣿\n"
                    + "⣿⣿⡇⠰⣿⣿⠁⠄⠄⠄⣿⣿⠆⢹⣿⣿⣿⣿⠄⣿⣿⠁⠄⠄⠄⣿⣿⡇⠄⣿\n"
                    + "⣿⣿⡇⠄⢿⣿⣷⣤⣤⣼⣿⡟⢀⣿⣿⣿⣿⣿⡄⠻⣿⣷⣤⣤⣾⣿⡿⠁⠄⣿\n"
                    + "⣿⣿⠃⢸⣦⡙⠛⠿⠟⠛⠉⣠⣾⣿⣿⣿⣿⣿⣿⣆⡈⠛⠻⠿⠛⢋⣴⡇⢸⣿\n"
                    + "⣿⣿⡀⠈⢿⣿⣷⣶⣶⣶⣿⣿⣿⣿⠛⣿⡋⣿⣿⣿⣿⣷⣶⣶⣾⣿⡿⠄⢸⣿\n"
                    + "⣿⣿⡇⠄⠈⢿⣿⣯⡻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⣽⣿⡟⠄⠄⣮⣿\n"
                    + "⣿⣿⣷⠄⠄⠄⠹⣿⣷⣌⠙⢿⣿⣿⣿⣿⣿⣿⣿⡿⠟⢁⣾⣿⠋⠄⠄⠄⢹⣿\n"
                    + "⣿⣿⣏⠄⠄⠄⠄⠘⢿⣿⣦⡀⠈⠛⢿⣿⡿⠟⠉⢀⣴⣿⠟⠁⠄⠄⠄⢠⢸⣿\n"
                    + "⣿⣿⣿⠄⠄⠄⠄⠄⠄⠙⢿⣿⣦⡀⠄⠄⠄⢀⣴⣿⠟⠃⠄⠄⠄⠄⠄⠄⣸⣿\n"
                    + "⣿⣿⣿⡄⠄⠄⠄⠄⠄⠄⢠⠉⠻⢿⣷⣶⣾⡿⠛⠁⡀⠄⠄⠄⠄⠄⠄⠄⣿⣿\n";
        System.out.println(face);

        // print greeting message after logo
        greeting();
        waitForQuery();
    }
}

