public class TaskList {

    //Output messages
    private static final String MESSAGE_TASK_ADDED_SUCCESSFULLY = "The following task has been added:";
    private static final String MESSAGE_MARK_TASK_FAIL = "Sorry, but the task does not exist, unable to mark as done";
    private static final String MESSAGE_MARK_TASK_SUCCESS = "The following task has been marked as done:";
    private static final String MESSAGE_NO_TASK_AVAILABLE = "You have no tasks yet";
    private static final String MESSAGE_PRINT_ALL_TASK_SUCCESS = "Here are all your tasks:";

    private int taskNumber;
    private Task[] taskList;

    public TaskList() {
        taskNumber = 0;
        taskList = new Task[100];
    }


    public void addTask(Task task) {
        PrintUtils.printHorizontalLine(100);
        taskList[taskNumber] = task;
        printAddTaskSuccessMessage();
        taskNumber++;
    }

    private void printAddTaskSuccessMessage() {
        System.out.println(MESSAGE_TASK_ADDED_SUCCESSFULLY);
        PrintUtils.printSpacing();
        System.out.println(taskList[taskNumber]);
        System.out.println("You now have " + (taskNumber + 1) + " task(s)");
        PrintUtils.printHorizontalLine(100);
    }

    public void markTaskAsDone(int taskNumber) {
        if (!isExistingTask(taskNumber)) {
            printMissingTaskErrorMessage();
            return;
        }
        markExistingTaskAsDone(taskList[taskNumber - 1]);
    }

    private boolean isExistingTask(int taskNumber) {
        return taskNumber > 0 && taskNumber <= this.taskNumber;
    }

    private void printMissingTaskErrorMessage() {
        PrintUtils.printHorizontalLine(100);
        System.out.println(MESSAGE_MARK_TASK_FAIL);
        PrintUtils.printHorizontalLine(100);
    }

    private void markExistingTaskAsDone(Task task) {
        task.markTaskAsDone();
        printMarkTaskSuccessMessage(task);
    }

    private void printMarkTaskSuccessMessage(Task task) {
        PrintUtils.printHorizontalLine(100);
        System.out.println(MESSAGE_MARK_TASK_SUCCESS);
        PrintUtils.printSpacing();
        System.out.println(task);
        PrintUtils.printHorizontalLine(100);
    }

    public void printAllTasks() {
        PrintUtils.printHorizontalLine(100);
        if (taskNumber == 0) {
            System.out.println(MESSAGE_NO_TASK_AVAILABLE);
            PrintUtils.printHorizontalLine(100);
            return;
        }
        System.out.println(MESSAGE_PRINT_ALL_TASK_SUCCESS);
        for (int i = 0; i < taskNumber; i++) {
            PrintUtils.printSpacing();
            System.out.println((i + 1) + ". " + taskList[i]);
        }
        PrintUtils.printHorizontalLine(100);
    }
}
