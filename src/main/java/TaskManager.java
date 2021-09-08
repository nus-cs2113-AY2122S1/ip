import java.util.ArrayList;

public class TaskManager {
    private static ArrayList<Todo> tasks = new ArrayList<Todo>();

    public static void addTodo(String userInput) {
        int startIndexOfTask = userInput.indexOf(' ');
        String taskName = userInput.substring(startIndexOfTask);
        Todo newTask = new Todo(userInput, tasks.size() + 1);
        tasks.add(newTask);
        printTaskAddedConfirmation(newTask);
    }

    public static void addDeadline(String userInput) {
        int startIndexOfTask = userInput.indexOf(' ');
        int endIndexOfTask = userInput.indexOf('/') - 1;
        int startIndexOfDeadline = userInput.indexOf('/') + 4;
        String taskName = userInput.substring(startIndexOfTask, endIndexOfTask);
        String deadline = userInput.substring(startIndexOfDeadline);
        Deadline newTask = new Deadline(taskName, tasks.size() + 1, deadline);
        tasks.add(newTask);
        printTaskAddedConfirmation(newTask);
    }

    public static void addEvent(String userInput) {
        int startIndexOfTask = userInput.indexOf(' ');
        int endIndexOfTask = userInput.indexOf('/') - 1;
        int startIndexOfDate = userInput.indexOf('/') + 4;
        String taskName = userInput.substring(startIndexOfTask, endIndexOfTask);
        String deadline = userInput.substring(startIndexOfDate);
        Event newTask = new Event(taskName, tasks.size() + 1, deadline);
        tasks.add(newTask);
        printTaskAddedConfirmation(newTask);
    }

    public static void printTasks() {
        if (tasks.size() == 0) {
            System.out.println("\tNo tasks added");
            return;
        }
        for (Todo task : tasks) {
            System.out.println("\tHere are the tasks in your list");
            System.out.println("\t" + task);
        }
    }

    public static void markDone(String userInput) {
        int indexOfTaskNumber = userInput.indexOf(' ') + 1;
        int taskNumber = Integer.parseInt(userInput.substring(indexOfTaskNumber));
        if (taskNumber > tasks.size() || taskNumber <= 0) {
            System.out.println("\tNo such task");
            return;
        }
        Todo task = tasks.get(taskNumber - 1);
        task.setIsDone();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + task.toString().substring(2));
    }

    private static void printTaskAddedConfirmation(Todo task) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task.toString().substring(2));
        System.out.format("\tNow you have %d tasks in the list.\n", tasks.size());
    }
}
