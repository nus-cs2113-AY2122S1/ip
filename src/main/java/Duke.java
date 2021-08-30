import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static final String HORIZONTAL_LINE = "_____________________________________________";
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void main(String[] args) {
        printHello();
        task();
        printBye();
    }

    private static void printHello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void printBye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void task() {
        Scanner in = new Scanner(System.in);
        String inputCommand = in.nextLine();
        while (!(inputCommand.equals("bye"))) {
            if (inputCommand.equals("list")) {
                showList();
            } else if (inputCommand.contains("done")) {
                MarkAsDone(inputCommand);
            } else if (inputCommand.contains("todo")) {
                addToDo(inputCommand);
            } else if (inputCommand.contains("deadline")) {
                addDeadline(inputCommand);
            } else if (inputCommand.contains("event")) {
                addEvent(inputCommand);
            }
            inputCommand = in.nextLine();
        }
    }

    private static void addEvent(String inputCommand) {
        System.out.println(HORIZONTAL_LINE);
        String description = inputCommand.substring(5);
        String[] parts = description.split("/at");
        String eventDescription = parts[0];
        String timingDescription = parts[1];
        tasks.add(new Event(eventDescription, timingDescription));
        GotItMessage();
        System.out.println(HORIZONTAL_LINE);
    }

    private static void addDeadline(String inputCommand) {
        System.out.println(HORIZONTAL_LINE);
        String description = inputCommand.substring(9);
        String[] parts = description.split("/by");
        String taskDescription = parts[0];
        String deadlineDescription = parts[1];
        System.out.println(taskDescription);
        System.out.println(deadlineDescription);
        tasks.add(new Deadline(taskDescription, deadlineDescription));
        GotItMessage();
        System.out.println(HORIZONTAL_LINE);
    }

    private static void addToDo(String inputCommand) {
        System.out.println(HORIZONTAL_LINE);
        String taskToDo = inputCommand.substring(5);
        tasks.add(new ToDo(taskToDo));
        GotItMessage();
        System.out.println(HORIZONTAL_LINE);
    }

    private static void GotItMessage() {
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void MarkAsDone(String inputCommand) {
        int position = inputCommand.indexOf(" ");
        int taskNum = Integer.parseInt(inputCommand.trim().substring(position + 1));
        System.out.println(taskNum);
        tasks.get(taskNum - 1).setDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskNum - 1).getDescription());
        System.out.println(HORIZONTAL_LINE);
    }

    private static void showList() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println(HORIZONTAL_LINE);
    }
}
