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
                System.out.printf("%d. ", i + 1);
                printTask(i);
            }
        }
    }

    public void printTask(int taskNumber) {
        String additionalInfo;
        switch (tasks[taskNumber].getTaskType()) {
        case "E":
            additionalInfo = "(at: " + tasks[taskNumber].getAdditionalDescription() + ")";
            break;
        case "D":
            additionalInfo = "(by: " + tasks[taskNumber].getAdditionalDescription() + ")";
            break;
        default:
            additionalInfo = "";
        }

        System.out.printf("[%s][%s] %s %s %n", tasks[taskNumber].getTaskType(),
                tasks[taskNumber].getStatusIcon(), tasks[taskNumber].getDescription(), additionalInfo);
    }

    public void addTask(String input, Action taskType) {
        String[] parameters = Parser.parseTask(input, taskType);
        System.out.println("Got it. I've added this task:");
        switch (taskType) {
        case TO_DO:
            tasks[taskCount] = new ToDo(parameters[0]);
            break;
        case DEADLINE:
            tasks[taskCount] = new Deadline(parameters[0], parameters[1]);
            break;
        case EVENT:
            tasks[taskCount] = new Event(parameters[0], parameters[1]);
            break;
        }
        printTask(taskCount);
        System.out.printf("Now you have %d tasks in the list.%n", taskCount + 1);
        taskCount++;
    }

    public void markTaskDone(String command) {
        int taskNumber = Integer.parseInt(command.split(" ")[1]) - 1;
        tasks[taskNumber].setDone();
        System.out.printf("I have marked \"%s\" as done %n", tasks[taskNumber].getDescription());
    }

    public void setTasks(Task[] tasks) {
        this.tasks = tasks;
    }
}
