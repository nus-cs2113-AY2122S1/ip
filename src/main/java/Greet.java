public class Greet {
    private static final int MAX_ARRAY_LIMIT = 100;
    protected static Task[] list = new Task[MAX_ARRAY_LIMIT];
    private static int tasksAdded = 0;

    public static void printLineOnConsole() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Add new Task in list
     *
     * @param newTask Task object created based on command.
     */
    //might move to main/duke class
    public static void addTask(Task newTask) {
        list[tasksAdded] = newTask;
        tasksAdded++;
        printAddNewTask(newTask);
    }

    /**
     * Calls Task.markAsDone().
     *
     * @param taskNumber Task number that is tagged to the task on console.
     */
    public static void checkDoneTask(int taskNumber) {
        int taskIndex = taskNumber - 1;
        list[taskIndex].markAsDone();
    }

    /**
     * Prints welcome message
     */
    public static void printWelcomeMessage() {
        printLineOnConsole();
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        printLineOnConsole();
    }


    /**
     * Prints goodbye message upon exit
     */
    public static void printGoodbyeMessage() {
        printLineOnConsole();
        System.out.println("     Bye. Hope to see you again soon!");
        printLineOnConsole();
    }

    /**
     * Print all the tasks in the list array
     */
    public static void printList() {

        printLineOnConsole();
        if (tasksAdded != 0) {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < tasksAdded; i++) {
                int numbering = i + 1;
                // need to replace this with to string
                System.out.println("     " + numbering + ". " + list[i]);
            }
        } else {
            System.out.println("     You have no tasks in the list at the moment. Please add a new task to begin.");
        }
        printLineOnConsole();
    }

    public static void printAddNewTask(Task newTask) {
        printLineOnConsole();
        System.out.println("     " + newTask);
        System.out.println("     Now you have " + tasksAdded + " tasks in the list.");
        printLineOnConsole();
    }


}
