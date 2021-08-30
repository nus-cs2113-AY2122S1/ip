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

    public static void addTodoTask(String arguments) {
        Todo newTodo = new Todo(arguments);
        tasks.add(newTodo);

        String output = " Got it. I've added this task:\n"
                + "   " + newTodo.toString() + "\n"
                + " Now you have " + tasks.size() + " tasks in the list.\n";
        printOutput(output);
    }

    public static void addDeadlineTask(String arguments) {
        String delimiter = "/by";
        int indexOfDelimiter = arguments.indexOf(delimiter);
        String description = arguments.substring(0, indexOfDelimiter).trim();
        String by = arguments.substring(indexOfDelimiter + delimiter.length(), arguments.length()).trim();

        Deadline newDeadline = new Deadline(description, by);
        tasks.add(newDeadline);

        String output = " Got it. I've added this task:\n"
                + "   " + newDeadline.toString() + "\n"
                + " Now you have " + tasks.size() + " tasks in the list.\n";
        printOutput(output);
    }

    public static void addEventTask(String arguments) {
        String delimiter = "/at";
        int indexOfDelimiter = arguments.indexOf(delimiter);
        String description = arguments.substring(0, indexOfDelimiter).trim();
        String at = arguments.substring(indexOfDelimiter + delimiter.length(), arguments.length()).trim();

        Event newEvent = new Event(description, at);
        tasks.add(newEvent);

        String output = " Got it. I've added this task:\n"
                + "   " + newEvent.toString() + "\n"
                + " Now you have " + tasks.size() + " tasks in the list.\n";
        printOutput(output);
    }

    public static void addNormalTask(String arguments) {
        Task newTask = new Task(arguments);
        tasks.add(newTask);
        String output = " added: " + arguments + "\n";
        printOutput(output);
    }

    public static void listTasks() {
        String output = " Here are the tasks in your list:\n";
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

    public static void executeCommand(String command, String arguments) {
        switch (command) {
        case "list":
            listTasks();
            break;
        case "done":
            acknowledgeDone(arguments);
            break;
        case "todo":
            addTodoTask(arguments);
            break;
        case "deadline":
            addDeadlineTask(arguments);
            break;
        case "event":
            addEventTask(arguments);
            break;
        default:
            arguments = command + " " + arguments;
            addNormalTask(arguments);
            break;
        }
    }

    public static boolean isBye(String command) {
        if (command.equals("bye")) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String userInput = "";
        String command = "";
        String arguments = "";
        boolean shouldBreak = false;
        Scanner sc = new Scanner(System.in);

        displayGreetingMessage();
        while (true) {
            userInput = sc.nextLine();
            String[] splitUserInput = userInput.trim().split("\\s+", 2);
            command = splitUserInput[0];
            if (splitUserInput.length > 1) {
                arguments = splitUserInput[1];
            }

            if (isBye(command)) {
                displayByeMessage();
                break;
            }
            executeCommand(command, arguments);
        }

    }
}
