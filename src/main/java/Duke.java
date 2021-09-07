import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void printHorizontalLine() {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
    }

    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printHorizontalLine();
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printHorizontalLine();
    }

    public static void markAsDone(Task[] tasks, String line) {
        String[] input = line.split(" ");
        int index = Integer.parseInt(input[1]) - 1;
        tasks[index].markAsDone();
        printHorizontalLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(" " + tasks[index].toString());
        printHorizontalLine();
    }

    public static void printList(Task[] tasks, int currCount) {
        printHorizontalLine();
        System.out.println(" Here are the tasks in your list:");
        Task[] taskList = Arrays.copyOf(tasks, currCount);
        int taskCount = 1;
        for (Task elem : taskList) {
            System.out.println(" " + taskCount + ". " + elem.toString());
            taskCount += 1;
        }
        printHorizontalLine();
    }

    public static int addTask(Task[] tasks, int currCount, String line) {
        if (line.contains("todo")) {
            // take the description part of the input string
            tasks[currCount] = new Todo(line.substring(5));
        } else if (line.contains("deadline")) {
            String[] input = line.substring(9).split("/");
            // first elem: description, second elem: deadline
            tasks[currCount] = new Deadline(input[0], input[1].substring(3));
        } else if (line.contains("event")) {
            String[] input = line.substring(6).split("/");
            tasks[currCount] = new Event(input[0], input[1].substring(3));
        }
        printHorizontalLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println(" " + tasks[currCount].toString());
        int numTasks = currCount + 1;
        System.out.println(" Now you have " + numTasks + " tasks in the list.");
        printHorizontalLine();
        currCount += 1;
        return currCount;
    }

    public static void printGoodBye() {
        printHorizontalLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        Task[] tasks = new Task[100]; // fixed size array for now, each contains a Task element
        int currCount = 0;
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.contains("done")) { // mark task as done
                markAsDone(tasks, line);
            } else if (line.equals("list")) { // print the list
                printList(tasks, currCount);
            } else { // user inputs a task
                currCount = addTask(tasks, currCount, line);
            }
            line = in.nextLine();
        }
        printGoodBye();
    }
}
