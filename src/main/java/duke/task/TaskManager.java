package duke.task;

public class TaskManager {

    private Task[] tasksList;

    private int totalNumberOfTasks = 0;

    public TaskManager() {
        this.tasksList = new Task[totalNumberOfTasks];
    }

    /**
     * Create a ToDo task and add into tasks list
     *
     * @param description The description of the task.
     */
    public void createToDoTask(String description) {
        Task newTask = new ToDo(description);
        addTask(newTask);
    }

    /**
     * Create an Event task and add into tasks list
     *
     * @param description The description of the task.
     * @param date        Start date for the event.
     */
    public void createEventTask(String description, String date) {
        Task newTask = new Event(description, date);
        addTask(newTask);
    }

    /**
     * Create a deadline task and add into tasks list.
     *
     * @param description The description of the task.
     * @param date        Due date for the deadline task.
     */
    public void createDeadlineTask(String description, String date) {
        Task newTask = new Deadline(description, date);
        addTask(newTask);
    }

    /**
     * Add the given task into the tasks list.
     *
     * @param task Task to be added into tasks list.
     */
    public void addTask(Task task) {
        increaseTaskListSize();
        tasksList[totalNumberOfTasks] = task;
        totalNumberOfTasks++;
        System.out.println("Got it. I've added this task:");
        printTask(totalNumberOfTasks - 1);
        System.out.printf("Now you have %d tasks in the list" + System.lineSeparator(), totalNumberOfTasks);
    }

    /**
     * Increases the size of the tasks list as more task is added into it.
     */
    public void increaseTaskListSize() {
        Task[] newItemsList = new Task[totalNumberOfTasks + 1];
        System.arraycopy(tasksList, 0, newItemsList, 0, totalNumberOfTasks);
        tasksList = newItemsList;
    }

    /**
     * Print all task status in the tasks list.
     */
    public void printAllTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < totalNumberOfTasks; i++) {
            System.out.printf("%s.", i + 1);
            printTask(i);
        }
        System.out.printf("There are currently %d tasks in your list.\n", totalNumberOfTasks);
    }

    /**
     * Print an individual task status in the tasks list, with reference to its index number.
     *
     * @param taskIndex The task index number in the tasks list to be printed out.
     */
    public void printTask(int taskIndex) {
        System.out.printf("%s %s" + System.lineSeparator(),
                tasksList[taskIndex].getStatusIcon(),
                tasksList[taskIndex].getTaskInfo()
        );
    }

    /**
     * Set a given task to be marked as done.
     *
     * @param taskNumber The task index number in the tasks list to be set as done.
     */
    public void setTaskToDone(int taskNumber) {
        if (taskNumber < 1 || taskNumber > totalNumberOfTasks) {
            System.out.println("Error: task not found.");
            return;
        }
        int taskIndex = taskNumber - 1;
        tasksList[taskIndex].setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        printTask(taskIndex);
    }

}
