package duke;

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

    public static String[] splitByDelimiter(String delimiter, String arguments) {
        String[] splitValues = new String[2];
        int indexOfDelimiter = arguments.indexOf(delimiter);
        splitValues[0] = arguments.substring(0, indexOfDelimiter).trim();
        splitValues[1] = arguments.substring(indexOfDelimiter + delimiter.length(), arguments.length()).trim();
        return splitValues;
    }

    public static void addTodoTask(String arguments) {
        if (arguments.equals("")) {
            String output = " ☹ OOPS!!! The description of a todo cannot be empty.\n";
            printOutput(output);
        } else {
            Todo newTodo = new Todo(arguments);
            tasks.add(newTodo);

            String output = " Got it. I've added this task:\n"
                    + "   " + newTodo + "\n"
                    + " Now you have " + tasks.size() + " tasks in the list.\n";
            printOutput(output);
        }
    }

    public static void addDeadlineTask(String arguments) {
        if (arguments.equals("")) {
            String output = " ☹ OOPS!!! The description of a deadline cannot be empty.\n";
            printOutput(output);
        } else {
            String delimiter = "/by";
            try {
                String[] splitArguments = splitByDelimiter(delimiter, arguments);
                String description = splitArguments[0];
                String by = splitArguments[1];

                Deadline newDeadline = new Deadline(description, by);
                tasks.add(newDeadline);

                String output = " Got it. I've added this task:\n"
                        + "   " + newDeadline + "\n"
                        + " Now you have " + tasks.size() + " tasks in the list.\n";
                printOutput(output);
            } catch (StringIndexOutOfBoundsException e) {
                String output = " ☹ OOPS!!! Could not find delimiter\n";
                printOutput(output);
            }
        }
    }

    public static void addEventTask(String arguments) {
        if (arguments.equals("")) {
            String output = " ☹ OOPS!!! The description of an event cannot be empty.\n";
            printOutput(output);
        } else {
            String delimiter = "/at";
            try {
                String[] splitArguments = splitByDelimiter(delimiter, arguments);
                String description = splitArguments[0];
                String at = splitArguments[1];

                Event newEvent = new Event(description, at);
                tasks.add(newEvent);

                String output = " Got it. I've added this task:\n"
                        + "   " + newEvent + "\n"
                        + " Now you have " + tasks.size() + " tasks in the list.\n";
                printOutput(output);
            } catch (StringIndexOutOfBoundsException e) {
                String output = " ☹ OOPS!!! Could not find delimiter\n";
                printOutput(output);
            }
        }
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

    public static void displayUnknownCommandResponse() {
        String unknownCommandResponse = " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        printOutput(unknownCommandResponse);
    }

    public static void executeCommand(String command, String arguments) throws DukeException {
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
        case "bye":
            displayByeMessage();
            System.exit(0);
        default:
            throw new DukeException();
        }
    }

    public static void main(String[] args) {
        String userInput = "";
        String command = "";
        String arguments = "";
        Scanner sc = new Scanner(System.in);

        displayGreetingMessage();
        while (true) {
            userInput = sc.nextLine();
            String[] splitUserInput = userInput.trim().split("\\s+", 2);
            command = splitUserInput[0];
            if (splitUserInput.length > 1) {
                arguments = splitUserInput[1];
            }
            try {
                executeCommand(command, arguments);
            } catch (DukeException e) {
                displayUnknownCommandResponse();
            }
        }

    }
}
