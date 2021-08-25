public class Tasks {
    private int taskNumber;
    private String[] taskList;

    public Tasks() {
        taskNumber = 0;
        taskList = new String[100];
    }

    public String[] getTaskList() {
        return taskList;
    }

    public void addTask(String task) {
        PrintUtils.printHorizontalLine(100);
        taskList[taskNumber] = task;
        System.out.println("Added: " + taskList[taskNumber]);
        taskNumber++;
        PrintUtils.printHorizontalLine(100);
    }

    public void printTasks() {
        PrintUtils.printHorizontalLine(100);
        for (int i = 0; i < taskNumber; i++) {
            System.out.print(i+1 + ". ");
            System.out.println(taskList[i]);
        }
        PrintUtils.printHorizontalLine(100);
    }
}
