import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "________________________________________";
        ArrayList<Task> tasks = new ArrayList<>();
        String curCommand = "";

        System.out.println(logo);
        System.out.println(line);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(line);

        while (!curCommand.equals("bye")) {
            curCommand = input.nextLine();
            System.out.println(line);

            String[] commands = curCommand.split(" ", 2);

            // Command has multiple parameters
            if (commands.length > 1) {
                switch (commands[0]) {
                    case "done":
                        int doneIndex = getPositiveNumeric(commands[1]);
                        if (doneIndex > 0 && doneIndex <= tasks.size()) {
                            tasks.get(doneIndex - 1).setDone(true);
                        }
                        System.out.println(doneIndex > 0 && doneIndex <= tasks.size()
                                ? "Nice! I've marked this task as done:\n " + taskToString(tasks.get(doneIndex - 1))
                                : "Formatting error");
                        break;
                    case "todo":
                        tasks.add(new Task(commands[1], "", 'T', false));
                        printNew(tasks.get(tasks.size() - 1), tasks.size());
                        break;
                    case "deadline":
                        String[] deadin = commands[1].split(" /by ", 2);
                        if (deadin.length > 1) {
                            tasks.add(new Task(deadin[0], deadin[1], 'D', false));
                            printNew(tasks.get(tasks.size() - 1), tasks.size());
                        }
                        else {
                            System.out.println("Formatting error");
                        }
                        break;
                    case "event":
                        String[] evin = commands[1].split(" /at ", 2);
                        if (evin.length > 1) {
                            tasks.add(new Task(evin[0], evin[1], 'E', false));
                            printNew(tasks.get(tasks.size() - 1), tasks.size());
                        }
                        else {
                            System.out.println("Formatting error");
                        }
                        break;
                    case "help":
                        printHelp(commands[1]);
                        break;
                    default:
                        System.out.println("Input error");
                }
            } else {
                switch (curCommand) {
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + "." + taskToString(tasks.get(i)));
                        }
                        break;
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    case "help":
                        System.out.println("Type help (command) to show more.\nAvailable commands: done, todo, deadline, event, list, bye, help (displays this message)");
                        break;
                    default:
                        System.out.println("Input error");
                }
            }
            System.out.println(line);
        }
    }

    // Checks if a string is a positive numeric value
    public static int getPositiveNumeric(String str) {
        try {
            return Integer.parseInt(str);
        } catch(NumberFormatException e){
            return -1;
        }
    }

    // Converts task to string
    public static String taskToString(Task task) {
        return "[" + task.getType() + "][" + (task.isDone() ? "X" : " ") + "] "
                + task.getTask() + (task.getType() == 'T' ? "" : " (" + (task.getType() == 'D' ? "by: " : "at: ")+ task.getTrail() + ")");
    }

    // Prints newest task
    public static void printNew(Task task, int size) {
        System.out.println("Got it. I've added this task:\n " +
                taskToString(task) +
                "\nNow you have " + size + " tasks in the list.");
    }

    // Prints help message
    public static void printHelp(String command) {
        switch (command) {
            case "done":
                System.out.println("done:\n Marks a specific task as done.");
                System.out.println("Format:\n done (index)");
                break;
            case "todo":
                System.out.println("todo:\n Adds a task without any date/time attached to it.");
                System.out.println("Format:\n todo (task)");
                break;
            case "deadline":
                System.out.println("deadline:\n Adds a task that needs to be done before a specific date/time.");
                System.out.println("Format:\n deadline (task) /by (date/time)");
                break;
            case "event":
                System.out.println("event:\n Adds a task that starts at a specific time and ends at a specific time.");
                System.out.println(" Format:\n event (task) /at (date/time)");
                break;
            case "list":
                System.out.println("list:\n Shows all tasks.");
                break;
            case "bye":
                System.out.println("bye:\n Terminates the program.");
                break;
            default:
                System.out.println("Formatting error");
        }
    }
}
