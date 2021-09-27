package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

// Command Manager
public class Parser {
    private static final int MAX_TASKS = 100;
    private static final int TODO_MIN_LENGTH = 6;
    private static final int TODO_DESCRIPTION_START = 5;
    private static final String TODO_DESCRIPTION_ERROR = "The todo description cannot be empty";
    private static final int EVENT_MIN_LENGTH = 7;
    private static final int EVENT_DESCRIPTION_START = 6;
    private static final String EVENT_EMPTY_ERROR = "The event must contain an '/at'";
    private static final String EVENT_DESCRIPTION_ERROR = "The event description cannot be empty";
    private static final int DEADLINE_MIN_LENGTH = 10;
    private static final int DEADLINE_DESCRIPTION_START = 9;
    private static final String DEADLINE_EMPTY_ERROR = "The deadline must contain a '/by'";
    private static final String DEADLINE_DESCRIPTION_ERROR = "The deadline description cannot be empty";
    private static final int EVENT_DEADLINE_TIME = 3;
    private static final int DONE_DELETE_FIND_MIN_LENGTH = 2;
    private static final String DONE_DESCRIPTION_ERROR = "The task to be marked as done cannot be empty";
    private static final String DONE_EXCEED_ERROR = "The task to be marked as done does not exist in the list of tasks";
    private static final String DELETE_DESCRIPTION_ERROR = "The task to be deleted cannot be empty";
    private static final String DELETE_EXCEED_ERROR = "The task to be deleted does not exist in the list of tasks";
    private static final String FIND_DESCRIPTION_ERROR = "The task to find cannot be empty";
    private static final String COMMAND_ERROR = "Sorry, I don't understand what you are saying";
    private static ArrayList<Task> tasks = new ArrayList<>(MAX_TASKS);
    private static int currCount;

    public Parser(ArrayList<Task> tasks, int currCount) {
        Parser.tasks = tasks;
        Parser.currCount = currCount;
    }

    public static void markAsDone(String line) throws DukeException {
        String[] input = line.split(" ");
        if (input.length < DONE_DELETE_FIND_MIN_LENGTH) {
            throw new DukeException(DONE_DESCRIPTION_ERROR);
        }
        int index = Integer.parseInt(input[1]) - 1;
        if (index >= currCount || index < 0) {
            throw new DukeException(DONE_EXCEED_ERROR);
        }
        tasks.get(index).markAsDone();
        Ui.printHorizontalLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(" " + tasks.get(index).toString());
        Ui.printHorizontalLine();
        Storage.writeToFile(tasks);
    }

    public static void deleteTask(String line) throws DukeException {
        String[] input = line.split(" ");
        if (input.length < DONE_DELETE_FIND_MIN_LENGTH) {
            throw new DukeException(DELETE_DESCRIPTION_ERROR);
        }
        int index = Integer.parseInt(input[1]) - 1;
        if (index >= currCount || index < 0) {
            throw new DukeException(DELETE_EXCEED_ERROR);
        }
        Ui.printHorizontalLine();
        System.out.println(" Noted. I've removed this task:");
        System.out.println(" " + tasks.get(index).toString());
        tasks.remove(index);
        int numTasks = currCount - 1;
        System.out.println(" Now you have " + numTasks + " tasks in the list.");
        Ui.printHorizontalLine();
        currCount -= 1;
        Storage.writeToFile(tasks);
    }

    public static void findTask(String line) throws DukeException {
        String[] input = line.split(" ");
        if (input.length < DONE_DELETE_FIND_MIN_LENGTH) {
            throw new DukeException(FIND_DESCRIPTION_ERROR);
        }
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task elem: tasks) {
            if (elem.getDescription().contains(input[1])) {
                matchingTasks.add(elem);
            }
        }
        if (matchingTasks.isEmpty()) {
            Ui.printHorizontalLine();
            System.out.println(" There are no matching tasks in your list");
            Ui.printHorizontalLine();
        } else {
            Ui.printHorizontalLine();
            System.out.println(" Here are the matching tasks in your list:");
            printList(matchingTasks);
            Ui.printHorizontalLine();
        }
    }

    public static void printList(ArrayList<Task> list) {
        int taskCount = 1;
        for (Task elem : list) {
            System.out.println(" " + taskCount + ". " + elem.toString());
            taskCount += 1;
        }
    }

    public static void printTaskList() {
        Ui.printHorizontalLine();
        System.out.println(" Here are the tasks in your list:");
        printList(tasks);
        Ui.printHorizontalLine();
    }

    public static void completeAddTask() {
        Ui.printHorizontalLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println(" " + tasks.get(currCount).toString());
        int numTasks = currCount + 1;
        System.out.println(" Now you have " + numTasks + " tasks in the list.");
        Ui.printHorizontalLine();
        currCount += 1;
        Storage.writeToFile(tasks);
    }

    public static void processEventCommand(String line) throws DukeException {
        if (line.length() < EVENT_MIN_LENGTH) {
            throw new DukeException(EVENT_DESCRIPTION_ERROR);
        }
        if (!line.contains("/at")) {
            throw new DukeException(EVENT_EMPTY_ERROR);
        }
        String[] input = line.substring(EVENT_DESCRIPTION_START).split("/");
        tasks.add(currCount, TaskList.addEvent(input[0], input[1].substring(EVENT_DEADLINE_TIME)));
    }

    public static void processDeadlineCommand(String line) throws DukeException {
        if (line.length() < DEADLINE_MIN_LENGTH) {
            throw new DukeException(DEADLINE_DESCRIPTION_ERROR);
        }
        if (!line.contains("/by")) {
            throw new DukeException(DEADLINE_EMPTY_ERROR);
        }
        String[] input = line.substring(DEADLINE_DESCRIPTION_START).split("/");
        // first elem: description, second elem: deadline
        tasks.add(currCount, TaskList.addDeadline(input[0], input[1].substring(EVENT_DEADLINE_TIME)));
    }

    public static void processTodoCommand(String line) throws DukeException {
        if (line.length() < TODO_MIN_LENGTH) {
            throw new DukeException(TODO_DESCRIPTION_ERROR);
        }
        // take the description part of the input string
        tasks.add(currCount, TaskList.addTodo(line.substring(TODO_DESCRIPTION_START)));
    }

    public static void executeCommands() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equals("bye")) {
            String[] lineArray = line.split(" ");
            try {
                switch (lineArray[0]) {
                case "done":
                    markAsDone(line);
                    break;
                case "delete":
                    deleteTask(line);
                    break;
                case "todo":
                    processTodoCommand(line);
                    completeAddTask();
                    break;
                case "deadline":
                    processDeadlineCommand(line);
                    completeAddTask();
                    break;
                case "event":
                    processEventCommand(line);
                    completeAddTask();
                    break;
                case "find":
                    findTask(line);
                    break;
                case "list":
                    printTaskList();
                    break;
                default:
                    throw new DukeException(COMMAND_ERROR);
                }
            } catch (DukeException e) {
                Ui.printHorizontalLine();
                System.out.println(e.getMessage());
                Ui.printHorizontalLine();
            }
            line = in.nextLine();
        }
    }
}
