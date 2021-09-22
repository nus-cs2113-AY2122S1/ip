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
    public static final String HELP_OUTPUT = "Hi user. Great to meet you.\n" +
            "Let me show you all the commands we have.\n" +
            "1. help -- helps you to list down all the commands available like now\n" +
            "2. list -- shows you the list of all the task you have entered\n" +
            "3. bye -- exits the program\n" +
            "4. done <index> -- marks task with the input index as done\n" +
            "5. delete <index> -- delete task with the input index\n" +
            "6. sort time -- sort tasks with timeframe by date/time in ascending order\n" +
            "7. find <text> -- find all tasks with that contains the input text\n" +
            "8. todo <description> -- creates a todo task with description\n" +
            "9. deadline <description> /by <time> where <time> is YYYY-MM-DD or YYYY-MM-DD HHMM\n" +
            "-- creates a deadline task with description as task name and the date as deadline\n" +
            "10. event <description> /at <time1> to <time2> where <time1> could be YYYY-MM-DD \n" +
            "or YYYY-MM-DD HHMM while <time2> could be YYYY-MM-DD or YYYY-MM-DD HHMM or even HHMM\n" +
            ". If <time2> is HHMM, it will inherit YYYY-MM-DD from <time1>\n" +
            "-- creates an event task with the description given and store 2 time given by the input\n";

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

    /**
     * Print out the task status upon adding
     * @param newTask the task that was added to the task array list
     */
    public static void printAddNewTask(Task newTask) {
        printLineOnConsole();
        System.out.println("     " + newTask);
        System.out.println(TASK_MESSAGE_START +
                TaskList.getArraySize() + TASK_MESSAGE_END);
        printLineOnConsole();

    }

    /**
     * Print the deleted task and the number of tasks left
     * @param size is the size of the task array list after deletion
     * @param task is the task that was deleted
     */
    public static void printDeleteTask(int size, Task task) {
        printLineOnConsole();
        System.out.println(DELETE_MESSAGE);
        System.out.println("     " + task);
        System.out.println(TASK_MESSAGE_START + size
                + TASK_MESSAGE_END);
        printLineOnConsole();
    }

    /**
     * Prints all the deadline and event tasks sorted in ascending time order
     * if there is no event and deadline tasks, a message will be sent instead
     */
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

    /**
     * Print filtered task array list that has the input user wants on console
     * @param input is the input user requires to be in the task description
     */
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

    public static void printHelp() {
        System.out.println(HELP_OUTPUT);
    }

}
