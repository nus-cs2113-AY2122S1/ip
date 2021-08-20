public class TaskManager {
    private Task[] taskList = new Task[100];
    private int taskCount;

    public void addTask(String taskName) {
        taskList[taskCount] = new Task(taskName);
        taskCount++;
    }

    public void markTaskAsCompleted(int taskNumber) {
        taskList[taskNumber].setTaskCompleted();
        System.out.println("Nice! Marking " + taskList[taskNumber].getTask() + " as done!");
        System.out.println(createCheckboxDisplay(taskList[taskNumber]) + " " + taskList[taskNumber].getTask());
    }

    public String createCheckboxDisplay(Task task) {
        String checkboxDisplay = "[ ]";
        if (task.getIsCompleted()) {
            checkboxDisplay = checkboxDisplay.replace(" ", "X");
        }
        return checkboxDisplay;
    }

    public void listTask() {
        Display.printListTaskLine();
        for (int i = 0; i < taskCount; i++) {
            System.out.println(i + 1 + "." + createCheckboxDisplay(taskList[i]) + " " + taskList[i].getTask());
        }
        Display.printListTaskLine();
    }
}
