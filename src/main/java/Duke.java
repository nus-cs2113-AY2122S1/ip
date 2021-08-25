import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static void printTasks(Task[] list, int totalTasks) {
        Task[] printList = Arrays.copyOf(list, totalTasks);
        int i = 1;
        for (Task task : printList) {
            System.out.println(i + "." + "[" + task.getStatusIcon() + "] " + task.description);
            i += 1;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int taskCount = 0;

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Task t = new Task(in.nextLine());
        System.out.println(t.description);
        Task[] taskList = new Task[100];

        while (!t.description.equals("bye")) {
            if (t.description.equals("list")) {
                System.out.println("Here are the tasks on your list:");
                printTasks(taskList, taskCount);
            } else if (t.description.contains("done")) {
                String number = t.description.substring(5);
                int doneIndex = Integer.parseInt(number) - 1;
                taskList[doneIndex].isDone = true;
                System.out.println("Good job! I've marked these tasks as done:");
                printTasks(taskList, taskCount);
            } else {
                System.out.println("added: " + t.description);
                taskList[taskCount] = t;
                taskCount += 1;
            }
            t = new Task(in.nextLine());
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
