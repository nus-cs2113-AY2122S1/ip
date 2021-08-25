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
        Lines.printHorizontalLine(100);
        taskList[taskNumber] = task;
        System.out.print("Added: ");
        taskList[taskNumber].printTask();
        taskNumber++;
        Lines.printHorizontalLine(100);
    }

    public void markTaskAsDone(int taskNumber) {
        Lines.printHorizontalLine(100);
        if (taskNumber <= 0 || taskNumber > this.taskNumber) {

            System.out.println("Sorry, but the task does not exist, unable to mark as done");
            Lines.printHorizontalLine(100);
            return;
        }
        taskList[taskNumber - 1].markDone();
        System.out.print("The following task has been marked as done :): ");
        taskList[taskNumber - 1].printTask();
        Lines.printHorizontalLine(100);
    }

    public void printTasks() {
        Lines.printHorizontalLine(100);
        for (int i = 0; i < taskNumber; i++) {
            System.out.print(i + 1 + ". ");
            taskList[i].printTask();
        }
        Lines.printHorizontalLine(100);
    }
}
