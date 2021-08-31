public class TaskList {
    private Task[] tasks;

    private static final int MAX_LIST_SIZE = 100;
    public static final int TASK_INDEX = 5;

    public TaskList() {
        this.tasks = new Task[MAX_LIST_SIZE];
    }

    public void addTask(String request) {
        Task newTask = Request.parseTask(request);
        int taskIndex = Task.getCount() - 1;
        this.tasks[taskIndex] = newTask;
        System.out.printf("Got it. I've added this task:\n" +
                "  %s\nNow you have %d task in the list\n"
                ,newTask, Task.getCount());
    }

    public void doneTask(String request) {
        int taskIndex = Request.parseTaskIndex(request);
        this.tasks[taskIndex].setDone();
        System.out.printf("Nice! I've marked this task as done:\n" +
                "  %s\n", tasks[taskIndex]);
    }

    public void printTasks() {
        if (Task.isEmpty()) {
            System.out.println("Take a chill pill! Your todo list is empty");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < Task.getCount(); i++) {
                System.out.printf("%d. %s\n", i + 1, tasks[i]);
            }
        }
    }
}
