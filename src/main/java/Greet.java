public class Greet {

    protected static Task[] list = new Task[100];

    private static int tasksAdded = 0;
    /**
     * Add new Task in list
     *
     * @param newTask Task object created based on command.
     */
    //might move to main/duke class
    public static void addTask(Task newTask) {
        list[tasksAdded] = newTask;
        tasksAdded += 1;
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
        if (tasksAdded != 0) {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < tasksAdded; i++) {
                int numbering = i + 1;
                stringTaskAdded = Integer.toString(numbering);
                // need to replace this with to string
                System.out.println("     " + stringTaskAdded + ". " + list[i]);
            }
            System.out.println("    ____________________________________________________________");
        } else {
            System.out.println("    ____________________________________________________________");
            System.out.println("     You have no tasks in the list at the moment. Please add a new task to begin.");
            System.out.println("    ____________________________________________________________");
        }
    }
    public static void printAddNewTask(Task newTask){
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + newTask);
        System.out.println("     Now you have " + tasksAdded + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }


}
