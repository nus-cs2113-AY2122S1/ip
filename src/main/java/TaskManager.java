public class TaskManager {

    private Task[] tasksList;

    private int totalNumberOfTasks = 0;

    public TaskManager() {
        this.tasksList = new Task[totalNumberOfTasks];
    }

    public void createToDoTask(String description) {
        Task newTask = new ToDo(description);
        addTask(newTask);
    }

    public void createEventTask(String description, String date) {
        Task newTask = new Event(description, date);
        addTask(newTask);
    }

    public void createDeadlineTask(String description, String date) {
        Task newTask = new Deadline(description, date);
        addTask(newTask);
    }

    public void addTask(Task task) {
        increaseTaskListSize();
        tasksList[totalNumberOfTasks] = task;
        totalNumberOfTasks++;
        System.out.println("Got it. I've added this task:");
        printTask(totalNumberOfTasks - 1);
        System.out.printf("Now you have %d tasks in the list" + System.lineSeparator(), totalNumberOfTasks);
    }

    public void increaseTaskListSize() {
        Task[] newItemsList = new Task[totalNumberOfTasks + 1];
        System.arraycopy(tasksList, 0, newItemsList, 0, totalNumberOfTasks);
        tasksList = newItemsList;
    }

    public void printAllTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < totalNumberOfTasks; i++) {
            System.out.printf("%s.", i + 1);
            printTask(i);
        }
        System.out.printf("There are currently %d tasks in your list.\n", totalNumberOfTasks);
    }

    public void printTask(int taskIndex) {
        System.out.printf("%s %s" + System.lineSeparator(),
                tasksList[taskIndex].getStatusIcon(),
                tasksList[taskIndex].getTaskInfo()
        );
    }

    public void setTaskToDone(int taskNumber) {
        if (taskNumber < 1 || taskNumber > totalNumberOfTasks) {
            System.out.println("Error: task not found.");
            return;
        }
        int taskIndex = taskNumber - 1;
        tasksList[taskIndex].setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        printTask(taskIndex);
    }

}
