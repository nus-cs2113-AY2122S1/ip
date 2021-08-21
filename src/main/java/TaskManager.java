public class TaskManager {
    private Task[] tasks = new Task[100];
    private int taskCount = 0;

    public void addTask(String description){
        tasks[taskCount] = new Task(description);
        taskCount += 1;
        Duke.printLine();
        System.out.println("\tadded: " + description);
        Duke.printLine();
    }

    public void listTasks() {
        Duke.printLine();
        for (int i = 0; i < taskCount; i += 1) {
            System.out.print('\t');
            System.out.println((i+1) + ". " + tasks[i].getDescription());
        }
        Duke.printLine();
    }
}
