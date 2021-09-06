public class TaskManager {
    public static final String LINE = "____________________________________________________________\n";
    private static final int MAXIMUM_TASKS = 100;
    Task[] tasks = new Task[MAXIMUM_TASKS];
    int noOfTasks = 0;

    public void addTasks(Task task) throws DukeException {
        if (noOfTasks >= MAXIMUM_TASKS) {
            throw new DukeException("☹ OOPS!!! Task list capacity reached!");
        }
        tasks[noOfTasks] = task;
        noOfTasks++;
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + (noOfTasks) + " tasks in the list.");
        System.out.println(LINE);
    }

    public void printTasks() {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < noOfTasks; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
        System.out.println(LINE);
    }

    public void markTaskAsDone(int taskIndex) throws DukeException {
        if (taskIndex < 0 || taskIndex >= noOfTasks) {
            throw new DukeException("☹ OOPS!!! Please select a task in the task list!");
        }
        tasks[taskIndex].markAsDone();
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[taskIndex]);
        System.out.println(LINE);
    }
}
