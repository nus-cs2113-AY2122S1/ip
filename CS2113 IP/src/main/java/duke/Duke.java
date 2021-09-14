package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class Duke {
    public static void Greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        String horizontalLine = "________________________";
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);
    }

    public static void listOperations() {
        Scanner sc = new Scanner(System.in);
        String horizontalLine = "________________________";
        final String GOODBYE_COMMENT = "Bye. Hope to see you again soon!";
        final String ERROR_MARK_TASK = "Please do not leave your task number empty :-(";
        final String ERROR_UNKNOWN_INPUT = ":-( OOPS!!! I'm sorry, but I don't know what that means :-(";
        final String ERROR_EMPTY_TODO_DESCRIPTION = "Please do not leave your todo description empty :-(";
        final String ERROR_EMPTY_DEADLINE_DESCRIPTION = "Please do not leave your deadline description empty :-(";
        final String ERROR_EMPTY_EVENT_DESCRIPTION = "Please do not leave your event description empty :-(";
        final String ERROR_EMPTY_DELETE_DESCRIPTION = "Please do not leave your delete task number empty :-(";
        final String ERROR_WRONG_HANDLE_EVENT_DESCRIPTION = "Include /at handler and insert date of event!";
        final String ERROR_WRONG_HANDLE_DEADLINE_DESCRIPTION = "Include /by handler and insert deadline!";

        boolean isBye;
        boolean isList;
        boolean isDone;
        boolean isTodo;
        boolean isDeadline;
        boolean isEvent;
        boolean isDelete;

        ArrayList<Task> tasks = new ArrayList<Task>();
        do {
            String userInput = sc.nextLine();

            isBye = userInput.equals("bye");
            isList = userInput.equals("list");
            isDone = userInput.startsWith("done");
            isTodo = userInput.startsWith("todo");
            isDeadline = userInput.startsWith("deadline");
            isEvent = userInput.startsWith("event");
            isDelete = userInput.startsWith("delete");
            System.out.println(horizontalLine);


            if (isBye) {
                System.out.println(GOODBYE_COMMENT);
            } else if (isList) {
                listTask(tasks, Task.taskCount);
            } else if (isDone) {
                try {
                    markTask(tasks, userInput);
                } catch (DukeException e) {
                    System.out.println(ERROR_MARK_TASK);
                }
            } else if (isDelete) {
                try {
                    deleteTask(tasks, userInput);
                } catch (DukeException e) {
                    System.out.println(ERROR_EMPTY_DELETE_DESCRIPTION);
                }
            } else if (isTodo) {
                try {
                    addTask(tasks, Task.taskCount, userInput, TaskType.TODO);
                } catch (DukeException e) {
                    System.out.println(ERROR_EMPTY_TODO_DESCRIPTION);
                } catch (FormatException e) {
                    System.out.println(ERROR_EMPTY_TODO_DESCRIPTION);
                }
            } else if (isDeadline) {
                try {
                    addTask(tasks, Task.taskCount, userInput, TaskType.DEADLINE);
                } catch (DukeException e) {
                    System.out.println(ERROR_EMPTY_DEADLINE_DESCRIPTION);
                } catch (FormatException e) {
                    System.out.println(ERROR_WRONG_HANDLE_DEADLINE_DESCRIPTION);
                }
            } else if (isEvent) {
                try {
                    addTask(tasks, Task.taskCount, userInput, TaskType.EVENT);
                } catch (DukeException e) {
                    System.out.println(ERROR_EMPTY_EVENT_DESCRIPTION);
                } catch (FormatException e) {
                    System.out.println(ERROR_WRONG_HANDLE_EVENT_DESCRIPTION);
                }
            } else {
                System.out.println(ERROR_UNKNOWN_INPUT);
            }
            System.out.println(horizontalLine);

        } while (!isBye);

    }

    private static void addTask(ArrayList<Task> tasks, int taskCount, String userInput, TaskType specificTask) throws DukeException, FormatException {
        if (isEmptyDescription(userInput)) {
            throw new DukeException();
        } else if (isIncorrectFormat(userInput, specificTask)) {
            throw new FormatException();
        }

        Task newTask;
        switch (specificTask) {
        case EVENT:
            newTask = new Event(userInput, taskCount);
            break;
        case TODO:
            newTask = new Todo(userInput, taskCount);
            break;
        case DEADLINE:
            newTask = new Deadline(userInput, taskCount);
            break;
        default:
            newTask = new Task(userInput, taskCount);
            break;
        }
        tasks.add(newTask);
        Task.taskCount++;

        final String ADDED_TASK_COMMENT = "Got it. I've added this task:";
        System.out.println(ADDED_TASK_COMMENT);
        String printTask = String.format(" [%s][ ] %s", newTask.taskType, newTask.description);
        String printTaskNumber = String.format("Now you have %d items in the list.", Task.taskCount);
        System.out.println(printTask);
        System.out.println(printTaskNumber);
    }

    private static void deleteTask(ArrayList<Task> tasks, String userInput) throws DukeException {
        if (isEmptyDescription(userInput)) {
            throw new DukeException();
        }
        String[] splitStringBySpace = userInput.trim().split("\\s+", 2);
        String userInputIntString = splitStringBySpace[1];
        int userInputInt = Integer.parseInt(userInputIntString);

        final String DELETE_TASK_COMMENT = "Noted. I've removed this task:";
        System.out.println(DELETE_TASK_COMMENT);

        String printTask = String.format(" [%s][ ] %s", tasks.get(userInputInt).taskType, tasks.get(userInputInt).description);
        tasks.remove(userInputInt);
        Task.taskCount--;
        String printTaskNumber = String.format("Now you have %d items in the list.", Task.taskCount);

        System.out.println(printTask);
        System.out.println(printTaskNumber);
    }

    private static void markTask(ArrayList<Task> tasks, String userInput) throws DukeException {
        if (isEmptyDescription(userInput)) {
            throw new DukeException();
        }
        String[] splitStringBySpace = userInput.trim().split("\\s+", 2);
        String userInputIntString = splitStringBySpace[1];
        int userInputInt = Integer.parseInt(userInputIntString);
        final String MARK_TASK_COMMENT = "Nice! I've marked this task as done:";
        tasks.get(userInputInt).markAsDone();
        String formatOutput = String.format("[%s][%s] %s", tasks.get(userInputInt).taskType, tasks.get(userInputInt).getStatusIcon(), tasks.get(userInputInt).description);

        System.out.println(MARK_TASK_COMMENT);
        System.out.println(formatOutput);
    }

    private static void listTask(ArrayList<Task> tasks, int taskCount) {
        final String LIST_TASK_COMMENT = "Here are the tasks in your list:";
        System.out.println(LIST_TASK_COMMENT);
        for (int i = 0; i < taskCount; i++) {
            int indexNumber = i + 1;
            String formatOutput = String.format("%d.[%s][%s] %s", indexNumber, tasks.get(i).taskType, tasks.get(i).getStatusIcon(), tasks.get(i).description);
            System.out.println(formatOutput);
        }
    }

    private static boolean isEmptyDescription(String userInput) {
        String[] trimDescription = userInput.trim().split("\\s+", 2);
        return trimDescription.length < 2;
    }

    private static boolean isIncorrectFormat(String userInput, TaskType specificTask) {
        switch (specificTask) {
        case EVENT:
            boolean hasIncorrectEventPlaceholder = !userInput.contains("/at");
            return hasIncorrectEventPlaceholder;

        case DEADLINE:
            boolean hasIncorrectDeadlinePlaceholder = !userInput.contains("/by");
            return hasIncorrectDeadlinePlaceholder;

        default:
            return true;
        }
    }

    public static void main(String[] args) {
        Greet();
        listOperations();
    }
}
