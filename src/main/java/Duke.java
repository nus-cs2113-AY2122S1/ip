import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;

public class Duke {

    /**
     * Array of tasks
     */
    private static final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Long line separator
     */
    private static final String lineSeparator = ("_____________________________________________________");

    private static final String FILEPATH = "./list.txt";

    public static void printSeparator() {
        System.out.println(lineSeparator);
    }

    public static void printGreeting() {
        printSeparator();
        System.out.println("Hello! I'm Duke the Dancing Dragon.");
        System.out.println("Anything I can help you with, young Padawan?");
        printSeparator();
    }

    public static void printGoodbyeMessage() {
        System.out.println("It's over Anakin... I can finally eat my lun-");
        System.out.println(lineSeparator);
    }

    public static void printDone(Task task) {
        System.out.println("You have marked item " + task.description + " as done:");
        System.out.println(task.getStatusIcon() + " " + task.description);
    }

    public static void printDeletedMessage(Task task) {
        System.out.println("You have deleted the item: " + "\n" + task);
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
     * Returns the number or unchecked tasks.
     * @param tasks ArrayList<Task> for Tasks.
     * @return int for number of uncompleted tasks.
     */
    public static int getNumOfUncompletedTasks(ArrayList<Task> tasks) {
        int numOfUncompletedTasks = 0;
        for (Task task : tasks) {
            if (!task.isDone) {
                numOfUncompletedTasks++;
            }
        }
        return numOfUncompletedTasks;
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
     * Returns date value for tasks which need a date input field. If user does not have proper date formatting, (i.e.
     * '/by' or '/at') this function returns an empty string.
     *
     * @param query user raw data input.
     * @return date value.
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
     * Return void. Function is responsible for printing out the whole task list of the user.
     *
     * @param tasks list of tasks input by user.
     */
    public static void printList(ArrayList<Task> tasks) {
        int count = 0;
        System.out.println("Here is your list:");
        for (Task item : tasks) {
            if (tasks.get(count) != null) {
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

    public static boolean hasDeleteKeyword(String arg) {
        return arg.trim().toLowerCase().contains("delete");
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
        } else if (hasDeleteKeyword(query)) {
            keyword = Keyword.DELETE_KEYWORD;
        } else {
            keyword = Keyword.NO_KEYWORD;
        }
        return keyword;
    }

    /**
     * Function waits for user input, or takes input from ./list.txt.
     */
    public static void waitForQuery() {
        String query = "";
        try {
            File file = new File(FILEPATH);
            Scanner listInput = new Scanner(file);
            while (listInput.hasNext()) {
                query = listInput.nextLine();
                addTask(query);
            }
            Scanner userInput = new Scanner(System.in);
            while (!query.equals("bye")) {
                System.out.print("=>");
                if (userInput.hasNextLine()) {
                    query = userInput.nextLine();
                }
                try {
                    appendToFile(FILEPATH, query);
                } catch (IOException e) {
                    System.out.println("Something went wrong... ------>" + e.getMessage());
                }
                addTask(query);
            }
        } catch (IOException exception) {
            Scanner userInput = new Scanner(System.in);

            while (!query.equals("bye")) {
                System.out.print("=>");
                if (userInput.hasNextLine()) {
                    query = userInput.nextLine();
                    try {
                        appendToFile(FILEPATH, query);
                    } catch (IOException e) {
                        System.out.println("Something went wrong... ------>" + e.getMessage());
                    }
                }
                addTask(query);
            }
        }
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        if (!textToAppend.equals("bye")) {
            fw.write(textToAppend + "\n");
            fw.close();
        }
    }

    /**
     * Returns void. Function is responsible for adding different Tasks to the task list.
     *
     * @param query user raw data input
     * @throws NullPointerException if user keys in done [number] when there is no such task.
     */
    public static void addTask(String query) throws IndexOutOfBoundsException {
        Keyword keyword = getKeywordStatus(query);
        switch (keyword) {
        case DONE_TASK:
            try {
                int taskNumber = getTaskNum(query) - 1;
                Task referencedTask = tasks.get(taskNumber);
                referencedTask.markAsDone();
                printDone(referencedTask);
                System.out.println("Total unchecked items in your list: " + getNumOfUncompletedTasks(tasks));
            } catch (IndexOutOfBoundsException exception) {
                System.out.println("There is no such task number...");
            }
            break;
        case TODO_TASK:
            tasks.add(new Todo(getQueryDescription(query)));
            System.out.println("Added a Todo Task: " + getQueryDescription(query));
            System.out.println("Total unchecked items in your list: " + getNumOfUncompletedTasks(tasks));
            break;
        case EVENT_TASK:
            try {
                if (getDate(query).equals("")) {
                    throw new InvalidInputsException.InvalidDateFormatting("Wrong format of date "
                            + "has been entered");
                }
                tasks.add(new Event(getQueryDescription(query), getDate(query)));
                System.out.println("Added an Event Task: " + getQueryDescription(query));
                System.out.println("Total unchecked items in your list: " + getNumOfUncompletedTasks(tasks));
            } catch (InvalidInputsException.InvalidDateFormatting exception) {
                exception.printStackTrace();
                System.out.println("Did you forget to add in the '/by' again?");
            }
            break;
        case DEADLINE_TASK:
            try {
                if (getDate(query).equals("")) {
                    throw new InvalidInputsException.InvalidDateFormatting("Wrong format of date "
                            + "has been entered");
                }
                tasks.add(new Deadline(getQueryDescription(query), getDate(query)));
                System.out.println("Added a Deadline Task: " + getQueryDescription(query));
                System.out.println("Total unchecked items in your list: " + getNumOfUncompletedTasks(tasks));
            } catch (InvalidInputsException.InvalidDateFormatting exception) {
                exception.printStackTrace();
                System.out.println("Did you forget to add in the '/by' again?");
            }
            break;
        case LIST_ITEMS:
            printList(tasks);
            break;
        case DELETE_KEYWORD:
            try {
                int taskNumber = getTaskNum(query) - 1;
                Task referencedTask = tasks.get(taskNumber);
                tasks.remove(taskNumber);
                printDeletedMessage(referencedTask);
                System.out.println("Total unchecked items in your list: " + getNumOfUncompletedTasks(tasks));
            } catch (IndexOutOfBoundsException exception) {
                System.out.println("There is no such task number...");
            }
            break;
        case NO_KEYWORD:
            try {
                throw new InvalidInputsException.MissingKeyword("You have to input <todo>, <deadline> or"
                        + " <event> first!");
            } catch (InvalidInputsException.MissingKeyword exception) {
                exception.printStackTrace();
                System.out.println("Invalid keyword!!!");
            }
            break;
        case GOODBYE_KEYWORD:
            printGoodbyeMessage();
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
        printGreeting();
        waitForQuery();
    }
}
