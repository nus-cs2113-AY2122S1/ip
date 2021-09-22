public class Ui {
    private static final int INDEX_FIX = 1;
    public static final String DASH_LINE = "    ____________________________________________________________";
    public static final String WELCOME_MESSAGE = "     Hello! I'm Duke\n" +
            "     What can I do for you?";
    public static final String BYE_MESSAGE = "     Bye. Hope to see you again soon!";
    public static final String LIST_HEADER = "     Here are the tasks in your list:";
    public static final String LIST_NO_TASK = "     You have no tasks in the list at the moment.\n" +
            "     Please add a new task to begin.";
    public static final String TASK_MESSAGE_START = "     Now you have ";
    public static final String TASK_MESSAGE_END = " tasks in the list.";
    public static final String DELETE_MESSAGE = "     Noted. I've removed this task:";
    public static final String TASK_CHECK_DONE = "     Nice! I've marked this task as done:";
    public static void printLineOnConsole() {
        System.out.println(DASH_LINE);
    }



    /**
     * Prints welcome message
     */
    public static void printWelcomeMessage() {
        printLineOnConsole();
        System.out.println(WELCOME_MESSAGE);
        printLineOnConsole();
    }


    /**
     * Prints goodbye message upon exit
     */
    public static void printGoodbyeMessage() {
        printLineOnConsole();
        System.out.println(BYE_MESSAGE);
        printLineOnConsole();
    }

    /**
     * Print all the tasks in the list array
     */
    public static void printList() {

        printLineOnConsole();
        int size = TaskList.getArraySize();
        if (size != 0) {
            System.out.println(LIST_HEADER);
            for (int i = 0; i < size; i++) {
                int numbering = i + INDEX_FIX;
                System.out.println("     " + numbering + ". " + TaskList.getTask(i));
            }
        } else {
            System.out.println(LIST_NO_TASK);
        }
        printLineOnConsole();
    }

    public static void printAddNewTask(Task newTask) {
        printLineOnConsole();
        System.out.println("     " + newTask);
        System.out.println(TASK_MESSAGE_START +
                TaskList.getArraySize() + TASK_MESSAGE_END);
        printLineOnConsole();

    }

    public static void printDeleteTask(int size, Task task) {
        printLineOnConsole();
        System.out.println(DELETE_MESSAGE);
        System.out.println("     " + task);
        System.out.println(TASK_MESSAGE_START + size
                + TASK_MESSAGE_END);
        printLineOnConsole();
    }

    public static void printSortedDateTimedTask() {
        boolean isEmpty = true;
        printLineOnConsole();
        for(TimedTask task: TimedTaskList.getSortedList()) {
            System.out.println("     " + task);
            isEmpty = false;
        }
        if(isEmpty){
            System.out.println("     There are no deadlines or events to sort");
        }
        printLineOnConsole();
    }
    public static void printFilteredDateTimedTask(String input) {
        boolean isEmpty = true;
        printLineOnConsole();
        for(Task task: TaskList.findTask(input)) {
            System.out.println("     " + task);
            isEmpty = false;
        }
        if (isEmpty) {
            System.out.println("     There are no results with the substring \"" + input + "\"");
        }
        printLineOnConsole();
    }

}
