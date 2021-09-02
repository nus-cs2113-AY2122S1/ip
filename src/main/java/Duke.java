import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    private static int taskCount = 0;
    private static int numOfTasks = 0;
    private static Task[] tasks = new Task[100];

    public static void greeting() {
        System.out.println("_____________________________________________________");
        System.out.println("Hello! I'm Duke the Dancing Dragon.");
        System.out.println("Anything I can help you with, young Padawan?");
        System.out.println("_____________________________________________________");
    }

    public static void goodbyeMessage() {
        System.out.println("It's over Anakin... I can finally eat my lun-");
        System.out.println("_____________________________________________________");
    }

    public static void printDone(Task arg) {
        System.out.println("You have marked item " + arg.description + " as done:");
        System.out.println(arg.getStatusIcon() + " " + arg.description);
    }

    public static int getTaskNum(String arg) {
        String[] words = arg.trim().split("[\\s]+");
        return Integer.parseInt(words[1]);
    }

    public static String getQueryDescription(String query) {
        try {
            String[] words = query.trim().split("[\\s]+");
            String[] allButFirstWord = Arrays.copyOfRange(words, 1, words.length);
            StringBuilder sentenceAfterDeletion = new StringBuilder();
            for (String word : allButFirstWord) {
                sentenceAfterDeletion.append(word).append(" ");
            }
            return sentenceAfterDeletion.toString();
        } catch (Exception StringIndexOutOfBoundsException) {
            System.out.println("It's [deadline <taskname here> /by <date here>], sir.");
            return "";
        }
    }

    public static String getDate(String query) {
        try {
            int datePosition = query.indexOf("/") + 3;
            return query.substring(datePosition).trim();
        } catch (Exception StringIndexOutOfBoundsException) {
            System.out.println("You did not key in any date for your event or deadline.");
            return "";
        }
    }

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

    public static void addTask(String query) {
        Keyword keyword = getKeywordStatus(query);
        switch (keyword) {
        case DONE_TASK:
            int taskNumber = getTaskNum(query);
            tasks[taskNumber - 1].markAsDone();
            printDone(tasks[taskNumber - 1]);
            numOfTasks--;
            System.out.println("Total items in your list: " + numOfTasks);
            break;
        case TODO_TASK:
            tasks[taskCount] = new Todo(query);
            taskCount++;
            numOfTasks++;
            System.out.println("Added a Todo Task: " + getQueryDescription(query));
            System.out.println("Total items in your list: " + numOfTasks);
            break;
        case EVENT_TASK:
            tasks[taskCount] = new Event(getQueryDescription(query), getDate(query));
            taskCount++;
            numOfTasks++;
            System.out.println("Added an Event Task: " + getQueryDescription(query));
            System.out.println("Total items in your list: " + numOfTasks);
            break;
        case DEADLINE_TASK:
            tasks[taskCount] = new Deadline(getQueryDescription(query), getDate(query));
            taskCount++;
            numOfTasks++;
            System.out.println("Added a Todo Task: " + getQueryDescription(query));
            System.out.println("Total items in your list: " + numOfTasks);
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
        default:
        }
    }

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

