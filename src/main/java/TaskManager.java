public class TaskManager {
    private int numberOfTasks;
    private Task[] tasks;

    public TaskManager() {
        numberOfTasks = 0;
        tasks = new Task[100];
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public void addTask(String input) {
        if (numberOfTasks >= 100) {
            System.out.println("Error: Too many Tasks!!!");
            return;
        }
        Task task = new Task(input);
        tasks[numberOfTasks] = task;
        numberOfTasks++;
    }

    public void printTasks() {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println(i + ". " + tasks[i].getTask());
        }
        System.out.println(horizontalLine);
    }

}
