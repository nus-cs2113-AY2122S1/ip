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
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i += 1) {
            System.out.print('\t');
            System.out.print(i+1 + ". ");
            if (tasks[i].isDone()){
                System.out.print("[X] ");
            } else {
                System.out.print("[ ] ");
            }
            System.out.println(tasks[i].getDescription());
        }
        Duke.printLine();
    }

    public void markAsDone(int index) {
        tasks[index].setAsDone();
        Duke.printLine();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  [X] " + tasks[index].getDescription());
        Duke.printLine();
    }
}
