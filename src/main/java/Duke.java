import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void printHorizontalLine() {
        String horizontal_line = "____________________________________________________________";
        System.out.println(horizontal_line + "\n");
    }

    public static void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n");
        printHorizontalLine();
        System.out.println(" Hello! I'm Duke\n");
        System.out.println(" What can I do for you?\n");
        printHorizontalLine();
    }

    public static void markAsDone(Task[] tasks, String line) {
        String[] input = line.split(" ");
        int index = Integer.parseInt(input[1]) - 1;
        tasks[index].markAsDone();
        printHorizontalLine();
        System.out.println("Nice! I've marked this task as done:\n");
        System.out.println(tasks[index].toString());
        printHorizontalLine();
    }

    public static void printList(Task[] tasks, int currCount) {
        printHorizontalLine();
        System.out.println("Here are the tasks in your list:\n");
        Task[] taskList = Arrays.copyOf(tasks, currCount);
        int count = 1;
        for (Task elem : taskList) {
            System.out.println(count + ". " + elem.toString() + "\n");
            count += 1;
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
        System.out.println("Got it. I've added this task:\n");
        System.out.println(tasks[currCount].toString() + "\n");
        int numTasks = currCount + 1;
        System.out.println("Now you have " + numTasks + " tasks in the list.");
        printHorizontalLine();
        currCount += 1;
        return currCount;
    }

    public static void goodbye() {
        printHorizontalLine();
        System.out.println(" Bye. Hope to see you again soon!\n");
        printHorizontalLine();
    }

    public static void main(String[] args) {
        welcome();
        Task[] tasks = new Task[100]; // fixed size array for now, each contains a Task element
        int currCount = 0;
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.contains("done")) { // mark task as done
                markAsDone(tasks, line);
            } else if (!line.equals("list")) { // user inputs a task
                currCount = addTask(tasks, currCount, line);
            } else { // print the list
                printList(tasks, currCount);
            }
            line = in.nextLine();
        }
        goodbye();
    }
}
