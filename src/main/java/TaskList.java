import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String request) throws Exception {
        Task newTask = Request.parseTask(request);
        tasks.add(newTask);
        System.out.printf("Got it. I've added this task:\n" +
                "  %s\nNow you have %d task in the list\n"
                ,newTask, tasks.size());
    }

    public void doneTask(String request) {
        int taskIndex = Request.parseTaskIndex(request.trim());
        Task task = tasks.get(taskIndex);
        if(task.isDone()) {
            System.out.println("This task is already done!");
        } else {
            tasks.get(taskIndex).setDone();
            System.out.printf("Nice! I've marked this task as done:\n" +
                    "  %s\n", tasks.get(taskIndex));
        }
    }

    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Take a chill pill! Your todo list is empty");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, tasks.get(i));
            }
        }
    }
}
