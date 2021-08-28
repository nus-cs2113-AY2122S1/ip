import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void printHorizontalLine() {
        String horizontal_line = "____________________________________________________________";
        System.out.println(horizontal_line + "\n");
    }

    public static void main(String[] args) {
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
        String line;
        Task[] tasks = new Task[100]; // fixed size array for now, each contains a Task element
        int currCount = 0;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.contains("done")) { // mark task as done
                String[] input = line.split(" ");
                int index = Integer.parseInt(input[1]) - 1;
                tasks[index].markAsDone();
                printHorizontalLine();
                System.out.println("Nice! I've marked this task as done:\n");
                System.out.println(tasks[index].toString());
                printHorizontalLine();
            } else if (!line.equals("list")) { // user inputs a task
                tasks[currCount] = new Task(line);
                printHorizontalLine();
                System.out.println("added: " + tasks[currCount].description + "\n");
                printHorizontalLine();
                currCount += 1;
            } else { // print the list
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
            line = in.nextLine();
        }
        printHorizontalLine();
        System.out.println(" Bye. Hope to see you again soon!\n");
        printHorizontalLine();
    }
}
