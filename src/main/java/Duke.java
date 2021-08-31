import java.security.Key;
import java.util.Scanner;

public class Duke {

    private static int taskCount = 0;
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

    public static void echoMessage(String arg) {
        if (!arg.equals("bye")) {
            System.out.println("_____________________________________________________");
            System.out.println(arg);
            System.out.println("_____________________________________________________");
        }
    }

    public static void printDone(Task arg) {
        System.out.println("You have marked item " + arg.description + " as done:");
        System.out.println(arg.getStatusIcon() + " " + arg.description);
    }

    public static int findTaskNum(String arg) {
        String[] words = arg.trim().split("[\\s]+");
        return Integer.parseInt(words[1]);
    }

    public static void printList(Task[] args) {
        int count = 0;
        System.out.println("Here is your list:");
        for (Task item : args) {
            if (args[count] != null) {
                count++;
                System.out.println(count + ". " + item.getStatusIcon() + " " + item.description);
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
        return arg.trim().toLowerCase().matches("^todo+\\s+[A-Za-z]+$");
    }

    public static boolean hasDeadlineKeyword(String arg) {
        return arg.trim().toLowerCase().matches("^deadline+\\s+[A-Za-z]+$");
    }

    public static boolean hasEventKeyword(String arg) {
        return arg.trim().toLowerCase().matches("^event+\\s+[A-Za-z]+$");
    }

    public static Keyword getKeywordStatus(String query) {
        Keyword keyword = null;
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
        } else {
            System.out.println("There is no keyword...");
        }
        return keyword;
    }

    public static void waitForQuery() {
        String query = "";
        Keyword keyword = getKeywordStatus(query);
        Scanner userInput = new Scanner(System.in);

        while (!query.equals("bye")) {
            System.out.print("=>");
            if (userInput.hasNextLine()) {
                query = userInput.nextLine();
            }
            addTask(query);
        }
        goodbyeMessage();
    }

    public static void addTask(String query) {
        //        tasks[taskCount] = task;
        //        taskCount++;

        Task[] tasks = new Task[100];
        int taskCount = 0;
        Keyword keyword = getKeywordStatus(query);
        switch (keyword) {
        case DONE_TASK:
            int taskNumber = findTaskNum(query);
            tasks[taskNumber - 1].markAsDone();
            printDone(tasks[taskNumber - 1]);
            break;
        case TODO_TASK:
            tasks[taskCount] = new Todo(query);
            taskCount++;
            break;
        case EVENT_TASK:
            tasks[taskCount] = new Event(query);
            taskCount++;
            break;
        case DEADLINE_TASK:
            tasks[taskCount] = new Deadline(query);
            taskCount++;
            break;
        case LIST_ITEMS:
            printList(tasks);
        default:
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

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
        //        int index = 0;

        //        while (!query.equals("bye")) {
        //            System.out.print("=>");
        //            if (userInput.hasNextLine()) {
        //                query = userInput.nextLine();
        //            }
        //            // prints out the same message that was given by user
        //            // echoMessage(query);
        //
        //            if (query.equals("list")) {
        //                // print out current list
        //                printList(listOfStuff);
        //            }
        //            else if (hasDoneKeyword(query)) {
        //                int taskNumber = findTaskNum(query);
        //                listOfStuff[taskNumber - 1].markAsDone();
        //                printDone(listOfStuff[taskNumber - 1]);
        //            } else {
        //            // stores the user's args in a list
        //            listOfStuff[index] = new Task(query);
        //            index++;
        //            }

    }
    // print end message
    //        goodbyeMessage();
}

