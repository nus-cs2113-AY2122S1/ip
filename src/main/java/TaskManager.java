public class TaskManager {
    private Task[] tasks;
    private int numberOfTasks;

    public TaskManager() {
        this.tasks = new Task[100];
        this.numberOfTasks = 0;
    }

    public void processUserInput(String userInput) {
        if(userInput.equals("list")) {
            printAllTasks();
        } else if (userInput.startsWith("done ")) {
            String[] inputs = userInput.split(" ");
            int taskDoneNumber = Integer.parseInt(inputs[1]);
            setTaskDone(taskDoneNumber - 1);
        } else {
            addTask(userInput);
        }
    }

    private void addTask(String task) {
        tasks[numberOfTasks] = new Task(task);
        numberOfTasks++;
        printHorizontalLine();
        System.out.println("     added: " + task);
        printHorizontalLine();
    }

    private void printAllTasks() {
        printHorizontalLine();
        for(int i = 0; i < numberOfTasks; i++) {
            System.out.println("     " + (i + 1) + ".["
                    + tasks[i].getStatusIcon() + "] "
                    + tasks[i].getTaskName());
        }
        printHorizontalLine();
    }


    private void setTaskDone(int taskIndex) {
        if(taskIndex < 0 || taskIndex > numberOfTasks - 1) {
            printHorizontalLine();
            if(taskIndex < 0) {
                System.out.println("     Please enter a valid task number!");
            } else {
                System.out.println("     There is only " + numberOfTasks
                        + " item(s) in the list!");
            }
            printHorizontalLine();
            return;
        }

        tasks[taskIndex].markAsDone();
        printHorizontalLine();
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println("       " + "[" + tasks[taskIndex].getStatusIcon() + "] "
                            + tasks[taskIndex].getTaskName() );
        printHorizontalLine();
    }

    private void printHorizontalLine() {
        System.out.println("    _____________________________________"
                +"_______________________");
    }
}
