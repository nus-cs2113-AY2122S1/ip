public class Greet {
    protected static Task[] list = new Task[100];
    private static int tasksAdded = 0;

    /**
     * Prints welcome message
     */
    public static void printWelcomeMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints goodbye message upon exit
     */
    public static void printGoodbyeMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Print all the tasks in the list array
     */
    public static void printList() {
        String stringTaskAdded;
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasksAdded; i++) {
            int numbering = i + 1;
            stringTaskAdded = Integer.toString(numbering);
            String isDone = list[i].getStatusIcon();
            System.out.println(stringTaskAdded + ". " + "[" + isDone + "] " + list[i].getDescription());
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Add new Task in list
     *
     * @param taskDescription Name of task.
     */
    public static void addTask(String taskDescription) {
        Task newTask = new Task(taskDescription);
        list[tasksAdded] = newTask;
        tasksAdded += 1;
        printList();
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
}
