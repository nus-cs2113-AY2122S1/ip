public class TaskManager {
    private static int numberOfTasks;
    private static int numberOfTasksUndone;
    private static Task[] taskList;

    public static int getNumberOfTasksUndone() {
        return numberOfTasksUndone;
    }

    public TaskManager(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
        this.numberOfTasksUndone = 0;
        this.taskList = new Task[numberOfTasks];
    }

    /**
     * Prints the task list
     *
     **/
    public static void printTaskList() {
        for (int i = 0; taskList[i] != null; i++) {
            System.out.print((i + 1) + ". ");
            System.out.print("[" + taskList[i].getTaskType() + "]");
            System.out.print("[" + taskList[i].getStatusIcon() + "] ");
            System.out.print(taskList[i].getDescription());

            // print deadline if task type is deadline or event
            if (taskList[i].getTaskType().equals("D")) {
                System.out.println("(by: " + taskList[i].getDeadline() + ")");
            } else if (taskList[i].getTaskType().equals("E")) {
                System.out.println("(at: " + taskList[i].getDeadline() + ")");
            } else {
                System.out.print("\n");
            }
        }
        System.out.println("Total tasks undone: " + numberOfTasksUndone);
        System.out.println("_____________________________");
    }

    /**
     * Store to do tasks in a list.
     *
     * @param args  the item after the command the user inputs
     * @param taskNumber the ith number of task the user entered
     **/
    public static void addToDoTaskToList(String args, int taskNumber) {

        Task t = new Task(args, null, "T");
        taskList[taskNumber] = t;
        numberOfTasksUndone++;
        System.out.println("added: " + args);
        System.out.println("Now you have " + numberOfTasksUndone + " tasks in the list");
        System.out.println("_____________________________");
    }

    /**
     * Store deadline tasks in a list.
     *
     * @param args  the item after the command the user inputs
     * @param taskNumber the ith number of task the user entered
     **/
    public static void addDeadlineTaskToList(String args, int taskNumber) {
        String description = args.substring(0, args.indexOf("/")).trim();
        String time = args.substring(args.indexOf("/") + 4);
        Task t = new Task(description, time, "D");
        taskList[taskNumber] = t;
        numberOfTasksUndone++;
        System.out.println("added: " + description);
        System.out.println("Now you have " + numberOfTasksUndone + " tasks in the list");
        System.out.println("_____________________________");
    }

    /**
     * Store event tasks in a list.
     *
     * @param args  the item after the command the user inputs
     * @param taskNumber the ith number of task the user entered
     **/
    public static void addEventTaskToList(String args, int taskNumber) {
        String description = args.substring(0, args.indexOf("/")).trim();
        String time = args.substring(args.indexOf("/") + 4);
        Task t = new Task(description, time, "E");
        taskList[taskNumber] = t;
        numberOfTasksUndone++;
        System.out.println("added: " + description);
        System.out.println("Now you have " + numberOfTasksUndone + " tasks in the list");
        System.out.println("_____________________________");
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

        System.out.println("Good job! This task is marked as done:");
        System.out.print("[" + taskList[doneTaskNumber - 1].getTaskType() + "]");
        System.out.println("[" + taskList[doneTaskNumber - 1].getStatusIcon() + "] " + taskList[doneTaskNumber - 1].getDescription());
        System.out.println("Now you have " + numberOfTasksUndone + " tasks in the list");
        System.out.println("_____________________________");
    }
}
