public class TaskManager {
    private Task[] taskList = new Task[100];
    private int taskCount;

    public void addTodoTask(String taskName) {
        taskList[taskCount] = new Todo(taskName);
        taskCount++;
        Display.printAddTaskLine();
        System.out.println("Noted! I've added a new TODO task");
        System.out.println(taskList[taskCount-1]);
        System.out.println("Now you have " + taskCount + " tasks in your list");
        Display.printAddTaskLine();
    }

    public void markTaskAsCompleted(int taskNumber) {
        taskList[taskNumber].setTaskCompleted();
        System.out.println("Nice! Marking " + taskList[taskNumber].getTask() + " as done!");
        System.out.println(Display.createCheckboxDisplay(Display.TASK_COMPLETE) + " " + taskList[taskNumber].getTask());
    }

    public void listTask() {
        Display.printListTaskLine();
        for (int i = 0; i < taskCount; i++) {
            System.out.println(i + 1 + ". " + taskList[i]);
        }
        Display.printListTaskLine();
    }
}
