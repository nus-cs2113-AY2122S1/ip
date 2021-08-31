import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static void printTask(Task newTask, int taskCount) {
        String toPrint = newTask.toString();
        System.out.println("Ok! I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + taskCount + " tasks in the list uwu");
    }

    public static void printTaskList(Task[] list, int totalTasks) {
        Task[] printList = Arrays.copyOf(list, totalTasks); // only copy until the part you want
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : printList) {
            System.out.println(i + "." + task);
            i += 1;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int taskCount = 0;

        System.out.println("Hello bbygirl! I'm Your Boyfriend <3");
        System.out.println("How can I help you today? ;)");
        Task t = new Task(in.nextLine());
        Task[] taskList = new Task[100];

        while (!t.description.equals("bye")) {
            if (t.description.equals("list")) {
                printTaskList(taskList, taskCount);
            } else if (t.description.contains("done")) {
                // marking task as done
                String number = t.description.substring(5);
                int doneIndex = Integer.parseInt(number) - 1;
                taskList[doneIndex].isDone = true;
                System.out.println("Good job! I've marked these tasks as done:");
                printTaskList(taskList, taskCount);
            } else {
                // adding a new task
                Task newTask = new Task("not initialised");
                if (t.description.contains("todo")) { // create a new todo
                    newTask = new Todo(t.description.substring(4));
                } else if (t.description.contains("deadline")) {
                    // create a new deadline
                    int start = t.description.indexOf('/');
                    // find the date
                    String task = t.description.substring(9, start - 1);
                    String date = t.description.substring(start + 3);
                    newTask = new Deadline(task, date);
                } else if (t.description.contains("event")) {
                    // find the date
                    int start = t.description.indexOf('/');
                    String task = t.description.substring(6, start - 1);
                    String date = t.description.substring(start + 3);
                    newTask = new Event(task, date);
                }
                taskList[taskCount] = newTask;
                taskCount += 1;
                printTask(newTask, taskCount);
            }
            t = new Task(in.nextLine());
        }
        System.out.println("Goodbye. I will miss you sooo much :(");
    }
}
