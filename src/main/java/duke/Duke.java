package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE_SEPARATOR = "------------------------------------";
    private static final String HELLO_MESSAGE = "Hello! I'm duke.Duke\nWhat can I do for you?";
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String ERROR_MESSAGE = "You need to specify the task type!";

    //Store tasks in ArrayList instead of array
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printHelloMessage();
        handleCommand();
        printByeMessage();
    }

    private static void handleCommand() {
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        while (!input.equals("bye")) {
            try {
                String keyword = input.split(" ")[0].toLowerCase();
                switch (keyword) {
                case "bye":
                    continue;
                case "list":
                    listTasks();
                    break;
                case "todo":
                    addTodo(input);
                    break;
                case "deadline":
                    addDeadline(input);
                    break;
                case "event":
                    addEvent(input);
                    break;
                case "done":
                    finishTask(input);
                    break;
                case "delete":
                    deleteTask(input);
                    break;
                default:
                    promptInvalidInput();
                    break;
                }
            } catch (DukeException e) {
                System.out.println(DukeException.getErrorMessage());
            } catch (NumberFormatException e) {
                System.out.println("You are supposed to enter a number!");
            }
            input = in.nextLine();
        }
    }

    private static void promptInvalidInput() throws DukeException {
        throw new DukeException(ERROR_MESSAGE);
    }

    private static void deleteTask(String input) throws DukeException {
        int index = Integer.parseInt(input.split(" ", 2)[1].trim());
        if (index > tasks.size()) {
            throw new DukeException("You don't have so many tasks yet!");
        }
        System.out.println("Noted. I've removed this task: ");
        System.out.println(tasks.get(index - 1).getTaskInfo());
        tasks.remove(index - 1);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");

    }

    private static void finishTask(String input) throws DukeException {
        int index = Integer.parseInt(input.split(" ", 2)[1].trim());
        if (index > tasks.size()) {
            throw new DukeException("You don't have so many tasks yet!");
        }
        tasks.get(index - 1).completeTask();
        System.out.println("Nice! I have marked this task as done: ");
        System.out.println(tasks.get(index - 1).getTaskInfo());
    }

    private static void addEvent(String input) throws DukeException {
        String at;
        String description;
        String[] inputSplit = input.split(" ");
        boolean noInput = inputSplit.length == 1;
        if (noInput) {
            throw new DukeException("You have to specify the task!");
        }
        boolean noSeparator = !input.contains("/");
        if (noSeparator) {
            throw new DukeException("Your task is not in the right format");
        }
        String[] inputWords = input.split("/");
        boolean noTask = inputWords[0].trim().equals("event");
        boolean noDate = inputWords.length == 1;
        if (noTask || noDate) {
            throw new DukeException("Your task is not in the right format");
        }
        description = inputWords[0];
        at = inputWords[1];
        System.out.println("Got it. I've added this task:");
        tasks.add(new Event(description, at));
        System.out.println(tasks.get(tasks.size() - 1).getTaskInfo());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    private static void addDeadline(String input) throws DukeException {
        String by;
        String description;
        String[] inputSplit = input.split(" ");
        boolean noInput = inputSplit.length == 1;
        if (noInput) {
            throw new DukeException("You have to specify the task!");
        }
        boolean noSeparator = !input.contains("/");
        if (noSeparator) {
            throw new DukeException("Your task is not in the right format");
        }
        String[] inputWords = input.split("/");
        boolean noTask = inputWords[0].trim().equals("deadline");
        boolean noDate = inputWords.length == 1;
        if (noTask || noDate) {
            throw new DukeException("Your task is not in the right format");
        }
        description = inputWords[0];
        by = inputWords[1];
        System.out.println("Got it. I've added this task:");
        tasks.add(new Deadline(description, by));
        System.out.println(tasks.get(tasks.size() - 1).getTaskInfo());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    private static void addTodo(String input) throws DukeException {
        String[] inputSplit = input.split(" ");
        boolean noInput = inputSplit.length == 1;
        if (noInput) {
            throw new DukeException("You have to specify the task!");
        }
        System.out.println("Got it. I've added this task:");
        tasks.add(new Todo(input));
        System.out.println(tasks.get(tasks.size() - 1).getTaskInfo());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    private static void listTasks() throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("You do not have any tasks in your list!");
        }
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).printTask(i + 1);
        }
    }

    private static void printHelloMessage() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(LINE_SEPARATOR);
        System.out.println(HELLO_MESSAGE);
        System.out.println(LINE_SEPARATOR);
    }

    private static void printByeMessage() {
        System.out.println(BYE_MESSAGE);
        System.out.println(LINE_SEPARATOR);
    }
}
