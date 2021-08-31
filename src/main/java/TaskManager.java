import java.util.ArrayList;

public class TaskManager {
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void addTask(String userInput) {
        Task newTask = new Task(userInput);
        tasks.add(newTask);
    }

    public static void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            boolean isDone = tasks.get(i).getIsDone();
            System.out.print(i + 1);
            if (isDone == true) {
                System.out.print(".[X] ");
            } else {
                System.out.print(".[ ] ");
            }
            System.out.println(tasks.get(i).getName());
        }
        System.out.println(FormatLines.divider);
    }

    public static void markDone(int taskNumber) {
        if (taskNumber >= tasks.size()) {
            System.out.println("No such task");
            System.out.println(FormatLines.divider);
            return;
        }
        tasks.get(taskNumber).setIsDone();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t[X] " + tasks.get(taskNumber).getName());
        System.out.println(FormatLines.divider);
    }
}
