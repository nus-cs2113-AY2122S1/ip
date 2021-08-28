public class TaskManager {
    private Task[] tasks = new Task[100];
    private int taskCount;

    public TaskManager() {
        taskCount = 0;
    }

    public void displayTaskList() {
        if (taskCount == 0) {
            System.out.println("No task added yet!");
        } else {
            System.out.println("Here is your list at the moment:");
            for (int i = 0; i < taskCount; i++) {
                System.out.printf("%d.[%s] %s %n", i + 1, tasks[i].getStatusIcon(), tasks[i].getDescription());
            }
        }
    }

    public void addTask(String input){
        tasks[taskCount] = new Task(input);
        taskCount++;
        System.out.printf("I have added \"%s\" into your to-do list. %n", input);
    }

    public void markTaskDone(String command){
        int taskNumber = Integer.parseInt(command.split(" ")[1]) - 1;
        tasks[taskNumber].setDone();
        System.out.printf("I have marked \"%s\" as done %n", tasks[taskNumber].getDescription());
    }
    public void setTasks(Task[] tasks) {
        this.tasks = tasks;
    }
}
