public class TaskList {
    private Task[] tasks;
    private int taskCount;

    public TaskList() {
        this.tasks = new Task[100];
        this.taskCount = 0;
    }

    public void addTask(String request) {
        Task newTask = parseRequest(request);
        this.tasks[taskCount] = newTask;
        System.out.printf("Got it. I've added this task:\n" +
                "  %s\nNow you have %d task in the list\n"
                , tasks[taskCount],taskCount + 1);
        this.taskCount += 1;
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
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.printf("%d. %s\n", i + 1, tasks[i]);
            }
        }
    }

    private Task parseRequest(String request){
        if (request.startsWith("todo")) {
            String description = request.substring(request.indexOf(" ") + 1, request.length());
            return new Task(description);
        } else {
            String description = request.substring(request.indexOf(" ") + 1, request.indexOf("/"));
            String date = request.substring(request.indexOf("/") + 1, request.length());
            if (request.startsWith("deadline")) {
                return new Deadline(description,date);
            }
            return new Event(description,date);
        }
    }
}
