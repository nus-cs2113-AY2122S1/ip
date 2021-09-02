import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    //declarations
    public static String line = "------------------------------------------------------------------------------------------\n";

    //declare task array and keep track of how many tasks stored
    public static Task[] t = new Task[100];
    public static int taskCount = 0;

    //Program starts with this greeting
    public static void start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line + "Hello! I'm Duke.\n" + logo + "What can i do for you?\n" + line);
    }

    //Program exits with this ending
    public static void sayBye() {
        System.out.println(line + "\n" + "Ciao! More tasks to do later!\n" + line);
        System.exit(0);
    }

    //Creates scanner and takes in input from user
    public static void userInput() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your wish: " + "\n" + line);
        String input = scan.nextLine();
        inputSort(input);
    }

    //Lists out all tasks stored and their statuses
    public static void sayList() {
        System.out.println(line);
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + t[i].toString()+ "\n");
        }
    }

    //Marks a stored task as done
    public static void sayDone(String input) {
        String taskNumber = input.substring(input.lastIndexOf(" ") + 1);
        int finalTaskNumber = Integer.parseInt(taskNumber) - 1;
        t[finalTaskNumber].markAsDone();
        System.out.println(line + "\n" + "Kudos! One less thing to stress about!\n");
        System.out.println("  " + t[finalTaskNumber].toString() + "\n" + line);
    }

    //Adds a new todo task and prints it                                       //todo borrow book
    public static void sayTodo(String input) {
        int endIndex = input.length();
        String taskName = input.substring(5, endIndex);
        t[taskCount] = new Todo(taskName);
        System.out.println(line + "\n");
        System.out.println("Got it. I've added this task:\n");
        System.out.println(t[taskCount].toString());
        taskCount++;
        System.out.println("\nNow you have " + taskCount + " tasks in the list.\n" + line);
    }

    //Adds a new deadline task and prints it
    public static void sayDeadline(String input) {                               //deadline return book /by Sunday
        int endIndex = input.lastIndexOf("/");
        String taskName = input.substring(9, endIndex);
        int endIndex2 = input.length();
        String by = input.substring(endIndex + 4, endIndex2);
        t[taskCount] = new Deadline(taskName, by);
        System.out.println(line + "\n");
        System.out.println("Got it. I've added this task:\n");
        System.out.println(t[taskCount].toString());
        taskCount++;
        System.out.println("\nNow you have " + taskCount + " tasks in the list.\n" + line);
    }

    //Adds a new event task and prints it
    public static void sayEvent(String input) {                                 //event project meeting /at Mon 2-4pm
        int endIndex = input.lastIndexOf("/");
        String taskName = input.substring(6, endIndex);
        int endIndex2 = input.length();
        String at = input.substring(endIndex + 4, endIndex2);
        t[taskCount] = new Event(taskName, at);
        System.out.println(line + "\n");
        System.out.println("Got it. I've added this task:\n");
        System.out.println(t[taskCount].toString());
        taskCount++;
        System.out.println("\nNow you have " + taskCount + " tasks in the list.\n" + line);
    }

    //Filters user inputs and pushes to different methods
    public static void inputSort(String input) {
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                sayList();
                System.out.println(line);
            } else if (input.contains("done")) {
                sayDone(input);
            } else if (input.contains("todo")) {
                sayTodo(input);
            } else if (input.contains("deadline")) {
                sayDeadline(input);
            } else if (input.contains("event")) {
                sayEvent(input);
            }
            userInput();
        }
        sayBye();
    }

    //Main
    public static void main(String[] args) {
        start();
        userInput();
    }
}

