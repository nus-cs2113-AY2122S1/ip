package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class TaskManager {
    public static final int MAX_TASKS = 100;

    private static int taskCount = 0;
    private static Task[] tasks = new Task[MAX_TASKS];


    private static void printDivider() {
        System.out.println("____________________________________________________________");
    }
    
    private static void InvalidCommandMessage() {
        System.out.println("☹ OOPS!!! I do not understand what that means!");
        printDivider();
    }

    private static void TaskEmptyMessage(String userInput) {
        System.out.printf("☹ OOPS!!! The description of a %s cannot be empty" + System.lineSeparator(), userInput.split(" ")[0]);
        printDivider();
    }

    private static void markAsDoneMessage(int index) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks[index]);
    }


    private static void taskDone(String userInput) {
        String[] params = userInput.split(" ", 2);
        int index = Integer.parseInt(params[1]) - 1;
        tasks[index].markAsDone();
        markAsDoneMessage(index);
        printDivider();
    }


    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.printf("%d.%s" + System.lineSeparator(), i + 1, tasks[i]);
        }
        printDivider();
    }

    private static void echoTask(int index) {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[index]);
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

    private static void addTask(String userInput) throws DukeException {
        String[] params = userInput.split(" ", 2);
        String command = params[0];
        if (params.length < 2) {
            throw new DukeException();
        }
        String description = userInput.substring(command.length() + 1);

        switch (command.toUpperCase()) {
        case "TODO":
            addTodo(description);
            break;
        case "DEADLINE":
            addDeadline(description);
            break;
        case "EVENT":
            addEvent(description);
            break;
        }

    }

    private static void addTodo(String userInput) {
        tasks[taskCount] = new Todo(userInput);
        echoTask(taskCount++);
    }

    //TODO exceptions for empty time for Deadline, Event
    private static void addDeadline(String description) {
        String[] params = description.split("/", 2);
        tasks[taskCount] = new Deadline(params[0], params[1]);
        echoTask(taskCount++);
    }

    //TODO exceptions for empty time for Deadline, Event
    private static void addEvent(String description) {
        String[] params = description.split("/", 2);
        tasks[taskCount] = new Event(params[0], params[1]);
        echoTask(taskCount++);
    }

    public static void handleRequest(String userInput) {
        try {
            String command = userInput.split(" ")[0];

            switch (command.toUpperCase()) {
            case "TODO":
            case "DEADLINE":
            case "EVENT":
                addTask(userInput);
                break;
            case "DONE":
                taskDone(userInput);
                break;
            default:
                throw new InvalidCommandException();
            }
        } catch (InvalidCommandException e) {
           InvalidCommandMessage();
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! You've forgotten to write the task number");
            printDivider();
        } catch (DukeException e) {
            TaskEmptyMessage(userInput);
        }
    }
}
