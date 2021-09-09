import tasks.Task;

public abstract class MessagePrinter {
    // constants storing printing messages
    private static final String FRIDAY = "  __      _     _             \n"
            + " / _|    (_)   | |            \n"
            + "| |_ _ __ _  __| | __ _ _   _ \n"
            + "|  _| '__| |/ _` |/ _` | | | |\n"
            + "| | | |  | | (_| | (_| | |_| |\n"
            + "|_| |_|  |_|\\__,_|\\__,_|\\__, |\n"
            + "                         __/ |\n"
            + "                        |___/ \n";
    private static final String DASHES = "____________________________________________________________\n";

    // methods to print constants
    private static void friday() {
        System.out.println(FRIDAY);
    }

    private static void dashes() {
        System.out.println(DASHES);
    }

    // methods to print messages

    // greet User upon entry
    public static void greetUser() {
        dashes();
        friday();
        System.out.println("Initiating FRIDAY");
        System.out.println("Hello Mr Stark, how may I be of assistance to you today");
        dashes();
    }

    // Messages for null command (invalid command) enum
    public static void invalidCommand() {
        dashes();
        System.out.println("Apologies sir, I do not understand your command.");
        dashes();
    }

    // Messages for printing out list of tasks

    // if list is empty
    public static void emptyListMessage() {
        dashes();
        System.out.println("You appear to have no tasks for today. How wonderful Sir.");
        dashes();
    }

    // otherwise
    public static void printList(Task[] tasks, int tasksLength) {
        dashes();
        System.out.println("You have a total of " + tasksLength +" tasks today sir, listing them out now.");
        for (Task task : tasks) {
            if (task == null) {
                break;
            }
            System.out.println(task);
        }
        dashes();
    }

    // Messages for marking task as done

    // if taskIndex is out of bounds
    public static void outOfBoundsTaskIndex() {
        dashes();
        System.out.println("Apologies sir, there is no such task in your list.");
        dashes();
    }

    // if no task is at taskIndex specified by user
    public static void invalidTaskIndex() {
        dashes();
        System.out.println("Hmm.. How strange, there appears to be no task at this location... Perhaps you could try another number sir?");
        dashes();
    }

    // after marking task as done
    public static void taskMarkedAsDone(Task currTask) {
        dashes();
        System.out.println("Your task \"" + currTask.getTaskName() + "\" is indicated as complete.");
        System.out.println("[X]" + currTask.getTaskName());
        dashes();
    }

    // Message for adding tasks
    public static void addedTask(String taskName) {
        dashes();
        System.out.println("Very well, adding task \"" + taskName + "\"");
        dashes();
    }

    // message printed upon exiting
    public static void exitMessage() {
        dashes();
        System.out.println("Powering Off now. Good Bye Mr Stark.\n");
        dashes();
    }
}
