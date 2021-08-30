public class TaskList {
    private int taskNumber;
    private Task[] taskList;

    public TaskList() {
        taskNumber = 0;
        taskList = new Task[100];
    }


    public Task[] getTaskList() {
        return taskList;
    }

    public void addTask(Task task) {
        PrintUtils.printHorizontalLine(100);
        taskList[taskNumber] = task;
        System.out.println("The following task has been added:");
        PrintUtils.printSpacing();
        System.out.println(taskList[taskNumber]);
        System.out.println("You now have " + (taskNumber + 1) + " task(s)");
        taskNumber++;
        PrintUtils.printHorizontalLine(100);
    }

    public void markTaskAsDone(int taskNumber) {
        if (!isExistingTask(taskNumber)) {
            printMissingTaskError();
            return;
        }
        markExistingTaskAsDone(taskList[taskNumber - 1]);
    }

    private boolean isExistingTask(int taskNumber) {
        return taskNumber > 0 && taskNumber <= this.taskNumber;
    }

    private void markExistingTaskAsDone(Task task) {
        task.markTaskAsDone();
        PrintUtils.printHorizontalLine(100);
        System.out.println("The following task has been marked as done:");
        PrintUtils.printSpacing();
        System.out.println(task);
        PrintUtils.printHorizontalLine(100);
    }

    private void printMissingTaskError() {
        PrintUtils.printHorizontalLine(100);
        System.out.println("Sorry, but the task does not exist, unable to mark as done");
        PrintUtils.printHorizontalLine(100);
    }


    public void printTasks() {
        PrintUtils.printHorizontalLine(100);
        if (taskNumber == 0) {
            System.out.println("You have no tasks yet");
            PrintUtils.printHorizontalLine(100);
            return;
        }
        System.out.println("Here are all your tasks:");
        for (int i = 0; i < taskNumber; i++) {
            PrintUtils.printSpacing();
            System.out.println((i + 1) + ". " + taskList[i]);
        }
        PrintUtils.printHorizontalLine(100);
    }
}
