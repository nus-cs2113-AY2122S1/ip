public class TaskList {

    //Output messages
    private static final String MESSAGE_TASK_ADDED_SUCCESSFULLY = "The following task has been added:";
    private static final String MESSAGE_MARK_TASK_FAIL = "Sorry, but the task does not exist, unable to mark as done.\nYou can view a list of your tasks using the 'list' command";
    private static final String MESSAGE_MARK_TASK_SUCCESS = "The following task has been marked as done:";
    private static final String MESSAGE_NO_TASK_AVAILABLE = "You have no tasks yet";
    private static final String MESSAGE_PRINT_ALL_TASK_SUCCESS = "Here are all your tasks:";
    private static final String MESSAGE_NO_TASK_NUMBER_TO_MARK_ERROR = "Please provide a task number e.g 'done 2'";

    private int taskNumber;
    private Task[] taskList;

    public TaskList() {
        taskNumber = 0;
        taskList = new Task[100];
    }


    public void addTask(Task task) throws DukeException{
        if (task.getDescription() == "") {
            throw new DukeException(ExceptionMessages.EXCEPTION_NO_DESCRIPTION);
        }
        PrintUtils.printHorizontalLine();
        taskList[taskNumber] = task;
        printAddTaskSuccessMessage();
        taskNumber++;
        PrintUtils.printHorizontalLine();
    }

    private void printAddTaskSuccessMessage() {
        System.out.println(MESSAGE_TASK_ADDED_SUCCESSFULLY);
        PrintUtils.printSpacing();
        System.out.println(taskList[taskNumber]);
        System.out.println("You now have " + (taskNumber + 1) + " task(s)");
    }

    public void markTaskAsDone(String input) {
        if (input.equals("")) {
            PrintUtils.printErrorMessage(MESSAGE_NO_TASK_NUMBER_TO_MARK_ERROR);
            return;
        }
        int taskNumber = Integer.parseInt(input.trim());
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
        PrintUtils.printHorizontalLine();
        System.out.println(MESSAGE_MARK_TASK_FAIL);
        PrintUtils.printHorizontalLine();
    }

    private void markExistingTaskAsDone(Task task) {
        task.markTaskAsDone();
        printMarkTaskSuccessMessage(task);
    }

    private void printMarkTaskSuccessMessage(Task task) {
        PrintUtils.printHorizontalLine();
        System.out.println(MESSAGE_MARK_TASK_SUCCESS);
        PrintUtils.printSpacing();
        System.out.println(task);
        PrintUtils.printHorizontalLine();
    }

    public void printAllTasks() {
        PrintUtils.printHorizontalLine();
        if (taskNumber == 0) {
            System.out.println(MESSAGE_NO_TASK_AVAILABLE);
            PrintUtils.printHorizontalLine();
            return;
        }
        System.out.println(MESSAGE_PRINT_ALL_TASK_SUCCESS);
        for (int i = 0; i < taskNumber; i++) {
            PrintUtils.printSpacing();
            System.out.println((i + 1) + ". " + taskList[i]);
        }
        PrintUtils.printHorizontalLine();
    }
}
