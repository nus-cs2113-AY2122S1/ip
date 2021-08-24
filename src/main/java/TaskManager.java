public class TaskManager {
    private String[] tasks;
    private int numberOfTasks;

    public TaskManager() {
        this.tasks = new String[100];
        this.numberOfTasks = 0;
    }

    public void addTask(String task) {
        tasks[numberOfTasks] = task;
        numberOfTasks++;
        printHorizontalLine();
        System.out.println("     added: " + task);
        printHorizontalLine();
    }

    public void printTask() {
        printHorizontalLine();
        for(int i = 0; i < numberOfTasks; i++) {
            System.out.println("     " + (i + 1) + ". " + tasks[i]);
        }
        printHorizontalLine();
    }

    public void processUserInput(String userInput) {
        if(userInput.equals("list")) {
            printTask();
        } else {
            addTask(userInput);
        }
    }

    public void printHorizontalLine() {
        System.out.println("    ____________________________________________________________");
    }
}
