public class TaskList {
    private Task[] tasks;
    private int taskCount;

    public TaskList() {
        this.tasks = new Task[100];
        this.taskCount = 0;
    }

    public void addTask(String description) {
         this.tasks[taskCount] = new Task(description);
         this.taskCount += 1;
         System.out.printf("added: %s\n", description);
    }

    public void doneTask(String request) throws Exception {
        int taskNumber = Integer.parseInt(request.substring(5, request.length()));
        this.tasks[taskNumber - 1].setDone();
        System.out.printf("Nice! I've marked this task as done:\n\t[X] %s\n", tasks[taskNumber - 1].getDescription());
    }

    public void printTasks() {
        if (taskCount == 0) {
            System.out.println("Take a chill pill! Your todo list is empty");
        } else {
            for (int i = 0; i < taskCount; i++) {
                String done = tasks[i].status() ? "X" : " ";
                System.out.printf("%d.[%s] %s\n", i + 1,done, tasks[i].getDescription());
            }
        }
    }
}
