package duke.tasks;

public class TaskManager {
    public static final String DIVIDER = "/";
    public static final String LINE_SEPARATOR = "_____________________________";
    private static int numberOfTasksUndone;
    private static int numberOfTasksAdded;
    private static Task[] taskList;

    public static int getNumberOfTasksUndone() {
        return numberOfTasksUndone;
    }

    public TaskManager(int maxNumberOfTasks) {
        this.numberOfTasksUndone = 0;
        this.numberOfTasksAdded = 0;
        this.taskList = new Task[maxNumberOfTasks];
    }

    /**
     * Prints the task list
     *
     **/
    public static void printTaskList() {
        System.out.println(LINE_SEPARATOR);
        for (int i = 0; i < numberOfTasksAdded; i++) {
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
     **/
    public static void addToDoTaskToList(String args) {

        Task t = new ToDo(args);
        taskList[numberOfTasksAdded] = t;
        numberOfTasksAdded++;
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
     **/
    public static void addDeadlineTaskToList(String args) {
        String description = args.substring(0, args.indexOf(DIVIDER)).trim();
        String time = args.substring(args.indexOf(DIVIDER) + 4);
        Task t = new Deadline(description, time);
        taskList[numberOfTasksAdded] = t;
        numberOfTasksAdded++;
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
     **/
    public static void addEventTaskToList(String args) {
        String description = args.substring(0, args.indexOf(DIVIDER)).trim();
        String time = args.substring(args.indexOf(DIVIDER) + 4);
        Task t = new Event(description, time);
        taskList[numberOfTasksAdded] = t;
        numberOfTasksAdded++;
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
