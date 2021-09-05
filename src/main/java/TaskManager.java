public class TaskManager {
    public static final String DIVIDER = "/";
    public static final String LINE_SEPARATOR = "_____________________________";
    private static int numberOfTasksUndone;
    private static Task[] taskList;

    public static int getNumberOfTasksUndone() {
        return numberOfTasksUndone;
    }

    public TaskManager(int numberOfTasks) {
        this.numberOfTasksUndone = 0;
        this.taskList = new Task[numberOfTasks];
    }

    /**
     * Prints the task list
     *
     **/
    public static void printTaskList() {
        System.out.println(LINE_SEPARATOR);
        for (int i = 0; taskList[i] != null; i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(taskList[i].toString());
        }
        System.out.println("Total tasks undone: " + numberOfTasksUndone);
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Store to do tasks in a list.
     *
     * @param args  the item after the command the user inputs
     * @param taskNumber the ith number of task the user entered
     **/
    public static void addToDoTaskToList(String args, int taskNumber) {

        Task t = new ToDo(args);
        taskList[taskNumber] = t;
        numberOfTasksUndone++;
        System.out.println(LINE_SEPARATOR);
        System.out.println("added: " + args);
        System.out.println("Now you have " + numberOfTasksUndone + " tasks in the list");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Store deadline tasks in a list.
     *
     * @param args  the item after the command the user inputs
     * @param taskNumber the ith number of task the user entered
     **/
    public static void addDeadlineTaskToList(String args, int taskNumber) {
        String description = args.substring(0, args.indexOf(DIVIDER)).trim();
        String time = args.substring(args.indexOf(DIVIDER) + 4);
        Task t = new Deadline(description, time);
        taskList[taskNumber] = t;
        numberOfTasksUndone++;
        System.out.println(LINE_SEPARATOR);
        System.out.println("added: " + description);
        System.out.println("Now you have " + numberOfTasksUndone + " tasks in the list");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Store event tasks in a list.
     *
     * @param args  the item after the command the user inputs
     * @param taskNumber the ith number of task the user entered
     **/
    public static void addEventTaskToList(String args, int taskNumber) {
        String description = args.substring(0, args.indexOf(DIVIDER)).trim();
        String time = args.substring(args.indexOf(DIVIDER) + 4);
        Task t = new Event(description, time);
        taskList[taskNumber] = t;
        numberOfTasksUndone++;
        System.out.println(LINE_SEPARATOR);
        System.out.println("added: " + description);
        System.out.println("Now you have " + numberOfTasksUndone + " tasks in the list");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Mark a task as done and prints done message
     *
     * @param args the string the user inputs
     **/
    public static void markTaskAsDone(String args) {
        int stringLength = args.length();
        int doneTaskNumber = Integer.parseInt(args.substring(stringLength - 1));
        taskList[doneTaskNumber - 1].markAsDone();
        numberOfTasksUndone--;

        System.out.println(LINE_SEPARATOR);
        System.out.println("Good job! This task is marked as done:");
        System.out.println(taskList[doneTaskNumber - 1].toString());
        System.out.println("Now you have " + numberOfTasksUndone + " tasks in the list");
        System.out.println(LINE_SEPARATOR);
    }
}
