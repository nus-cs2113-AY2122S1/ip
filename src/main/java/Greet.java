import java.util.ArrayList;

public class Greet {
    private static final int indexFix = 1;
    protected static ArrayList<Task> list = new ArrayList<>();


    public static void printLineOnConsole() {
        System.out.println(GreetMessage.DASH_LINE);
    }

    /**
     * Add new Task in list
     *
     * @param newTask Task object created based on command.
     */
    //might move to main/duke class
    public static void addTask(Task newTask) {
        list.add(newTask);
        printAddNewTask(newTask);
    }

    /**
     * Add new Task in list
     *
     * @param newTask Task object created based on command.
     */
    //might move to main/duke class
    public static void reloadTask(Task newTask) {
        list.add(newTask);
    }

    /**
     * Calls Task.markAsDone().
     *
     * @param taskNumber Task number that is tagged to the task on console.
     */
    public static void checkDoneTask(int taskNumber) {
        int taskIndex = taskNumber - indexFix;
        list.get(taskIndex).markAsDone();
    }

    /**
     * Prints welcome message
     */
    public static void printWelcomeMessage() {
        printLineOnConsole();
        System.out.println(GreetMessage.WELCOME_MESSAGE);
        printLineOnConsole();
    }


    /**
     * Prints goodbye message upon exit
     */
    public static void printGoodbyeMessage() {
        printLineOnConsole();
        System.out.println(GreetMessage.BYE_MESSAGE);
        printLineOnConsole();
    }

    /**
     * Print all the tasks in the list array
     */
    public static void printList() {

        printLineOnConsole();
        if (list.size() != 0) {
            System.out.println(GreetMessage.LIST_HEADER);
            for (int i = 0; i < list.size(); i++) {
                int numbering = i + indexFix;
                // need to replace this with to string
                System.out.println("     " + numbering + ". " + list.get(i));
            }
        } else {
            System.out.println(GreetMessage.LIST_NO_TASK);
        }
        printLineOnConsole();
    }

    public static void printAddNewTask(Task newTask) {
        printLineOnConsole();
        System.out.println("     " + newTask);
        System.out.println(GreetMessage.TASK_MESSAGE_START +
                list.size() + GreetMessage.TASK_MESSAGE_END);
            printLineOnConsole();
    }

    public static ArrayList<Task> getList() {
        return list;
    }




}
