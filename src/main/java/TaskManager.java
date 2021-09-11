import java.util.ArrayList;

import exceptions.DukeException;
import exceptions.InvalidCommandException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class TaskManager {
    public static int taskCount = 0;
    public static ArrayList<Task> tasks = new ArrayList<>();

    private static void printDivider() {
        System.out.println("____________________________________________________________");
    }

    private static void InvalidCommandMessage() {
        System.out.println("☹ OOPS!!! I do not understand what that means!");
        printDivider();
    }

    private static void DukeExceptionMessage(String command) {
        if (command.equalsIgnoreCase("done")) {
            System.out.println("☹ OOPS!!! You've forgotten to write the task number");
        } else {
            System.out.printf("☹ OOPS!!! The description of a %s cannot be empty" + System.lineSeparator(), command);
        }
        printDivider();
    }

    private static void markAsDoneMessage(int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index));
    }

    private static void deleteTaskMessage(int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index));
        System.out.println("Now you have " + taskCount + " tasks in the list");
    }

    private static void taskDone(String userInput) throws DukeException {
        String[] params = userInput.split(" ", 2);
        if (params.length < 2) {
            throw new DukeException();
        }
        String position = params[1];
        int index = Integer.parseInt(position) - 1;
        tasks.get(index).markAsDone();
        markAsDoneMessage(index);
        printDivider();
    }

    private static void deleteTask(String userInput) throws DukeException {
        String[] params = userInput.split(" ", 2);
        if (params.length < 2) {
            throw new DukeException();
        }
        String position = params[1];
        int index = Integer.parseInt(position) - 1;
        taskCount--;
        deleteTaskMessage(index);
        tasks.remove(index);
        printDivider();
    }

    public static void taskDoneLatest() {
        tasks.get(taskCount - 1).markAsDone();
    }


    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.printf("%d.%s" + System.lineSeparator(), i + 1, tasks.get(i));
        }
        printDivider();
    }

    private static void echoTask(int index) {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(index));
        taskCountMessage(taskCount);
        printDivider();
    }

    private static void taskCountMessage(int count) {
        if (count == 0) {
            System.out.print("You have not added any tasks");
        } else if (count == 1) { //grammar
            System.out.print("Now you have 1 task in the list" + System.lineSeparator());
        } else {
            System.out.printf("Now you have %d tasks in the list" + System.lineSeparator(), count);
        }
    }

    public static void addTodo(String userInput) {
        tasks.add(new Todo(userInput));
        taskCount++;
    }

    public static void addDeadline(String description, String time) {
        tasks.add(new Deadline(description, time));
        taskCount++;
    }

    public static void addEvent(String description, String time) {
        tasks.add(new Event(description, time));
        taskCount++;
    }

    private static void addTask(String userInput) throws DukeException {
        String[] params = userInput.split(" ", 2);
        String command = params[0];
        if (params.length < 2) {
            throw new DukeException();
        }

        String description = params[1];
        String[] separateTime;
        String[] separatePreposition;
        String time;

        if (command.equals("todo")) {
            addTodo(description);
        }
        else { //timed tasks
            separateTime = params[1].split("/", 2);
            description = separateTime[0];
            separatePreposition = separateTime[1].split(" ", 2);
            time = separatePreposition[1];
            if (command.equals("deadline")){
                addDeadline(description, time);
            }
            else {
                addEvent(description, time);
            }
        }

    }

    public static void handleRequest(String userInput) {
        try {
            String[] params = userInput.split(" ", 2);
            String command = params[0];

            switch (command.toUpperCase()) {
            case "TODO":
            case "DEADLINE":
            case "EVENT":
                addTask(userInput);
                echoTask(taskCount - 1);
                break;
            //fallthrough
            case "DONE":
                taskDone(userInput);
                break;
            case "DELETE":
                deleteTask(userInput);
                break;
            default:
                throw new InvalidCommandException(); //if user input is not any of the commands
            }
        } catch (InvalidCommandException e) {
            InvalidCommandMessage();
        } catch (DukeException e) {
            DukeExceptionMessage(userInput);
        }
    }
}
