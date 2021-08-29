import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void displayGreetingMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        String greet = "____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(greet);
    }

    public static void printOutput(String output) {
        String niceOutput = "____________________________________________________________\n"
                + output
                + "____________________________________________________________\n";
        System.out.println(niceOutput);
    }

    public static void addTask(String taskType, String arguments) {
        String delimiter;
        String description;
        String output;

        switch (taskType) {
        case "todo":
            Todo newTodo = new Todo(arguments);
            tasks.add(newTodo);

            output = " Got it. I've added this task:\n"
                    + "   " + newTodo.toString() + "\n"
                    + " Now you have " + tasks.size() + " tasks in the list.\n";
            printOutput(output);
            break;
        case "deadline":
            delimiter = "/by";
            int indexOfBy = arguments.indexOf(delimiter);
            description = arguments.substring(0, indexOfBy).trim();
            String by = arguments.substring(indexOfBy + delimiter.length(), arguments.length()).trim();

            Deadline newDeadline = new Deadline(description, by);
            tasks.add(newDeadline);

            output = " Got it. I've added this task:\n"
                    + "   " + newDeadline.toString() + "\n"
                    + " Now you have " + tasks.size() + " tasks in the list.\n";
            printOutput(output);
            break;
        case "event":
            delimiter = "/at";
            int indexOfAt = arguments.indexOf(delimiter);
            description = arguments.substring(0, indexOfAt).trim();
            String at = arguments.substring(indexOfAt + delimiter.length(), arguments.length()).trim();

            Event newEvent = new Event(description, at);
            tasks.add(newEvent);

            output = " Got it. I've added this task:\n"
                    + "   " + newEvent.toString() + "\n"
                    + " Now you have " + tasks.size() + " tasks in the list.\n";
            printOutput(output);
            break;
        default:
            break;
        }
    }

    public static void listTasks() {
        String output = "Here are the tasks in your list:\n";
        for (int i = 1; i < tasks.size() + 1; i++) {
            output = output + " " + i + "." + tasks.get(i - 1).toString() + "\n";
        }
        printOutput(output);
    }

    public static void acknowledgeDone(String arguments) {
        int taskNumber = Integer.parseInt(arguments) - 1;
        tasks.get(taskNumber).markAsDone();
        String output = " Nice! I've marked this task as done:\n"
                + "   " + tasks.get(taskNumber).toString() + "\n";
        printOutput(output);
    }

    public static void displayByeMessage() {
        String output = " Bye. Hope to see you again soon!\n";
        printOutput(output);
    }

    public static void main(String[] args) {
        displayGreetingMessage();

        String userInput = "";
        String command = "";
        String arguments = "";
        String output = "";
        boolean shouldBreak = false;

        Scanner sc = new Scanner(System.in);

        while (true) {
            userInput = sc.nextLine();
            String[] splitUserInput = userInput.trim().split("\\s+", 2);
            command = splitUserInput[0];
            if (splitUserInput.length > 1) {
                arguments = splitUserInput[1];
            }

            switch (command) {
            case "list":
                listTasks();
                break;
            case "bye":
                displayByeMessage();
                shouldBreak = true;
                break;
            case "done":
                acknowledgeDone(arguments);
                break;
            case "todo":
                addTask("todo", arguments);
                break;
            case "deadline":
                addTask("deadline", arguments);
                break;
            case "event":
                addTask("event", arguments);
                break;
            default:
                Task newTask = new Task(userInput);
                tasks.add(newTask);
                output = " added: " + userInput + "\n";
                printOutput(output);
                break;
            }
            if (shouldBreak == true) {
                break;
            }
        }

    }
}
