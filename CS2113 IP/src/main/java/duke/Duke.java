package duke;

import java.util.Scanner;

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
        final String ERROR_UNKNOWN_INPUT = ":-( OOPS!!! I'm sorry, but I don't know what that means :-(";
        final String ERROR_EMPTY_TODO_DESCRIPTION = "Please do not leave your todo description empty :-(";
        final String ERROR_EMPTY_DEADLINE_DESCRIPTION = "Please do not leave your deadline description empty :-(";
        final String ERROR_EMPTY_EVENT_DESCRIPTION = "Please do not leave your event description empty :-(";

        boolean isBye;
        boolean isList;
        boolean isDone;
        boolean isTodo;
        boolean isDeadline;
        boolean isEvent;

        Task[] tasks = new Task[100];
        do {
            String userInput = sc.nextLine();

            isBye = userInput.equals("bye");
            isList = userInput.equals("list");
            isDone = userInput.startsWith("done");
            isTodo = userInput.startsWith("todo");
            isDeadline = userInput.startsWith("deadline");
            isEvent = userInput.startsWith("event");
            System.out.println(horizontalLine);


            if (isBye) {
                System.out.println(GOODBYE_COMMENT);
            } else if (isList) {
                listTask(tasks, Task.taskCount);
            } else if (isDone) {
                markTask(tasks, userInput);
            } else if (isTodo) {
                try {
                    if (isEmptyDescription(userInput)) {
                        throw new DukeException();
                    }
                    addTask(tasks, Task.taskCount, userInput, TaskType.TODO);
                } catch (DukeException e) {
                    System.out.println(ERROR_EMPTY_TODO_DESCRIPTION);
                }
            } else if (isDeadline) {
                try {
                    if (isEmptyDescription(userInput)) {
                        throw new DukeException();
                    }
                    addTask(tasks, Task.taskCount, userInput, TaskType.DEADLINE);
                } catch (DukeException e) {
                    System.out.println(ERROR_EMPTY_DEADLINE_DESCRIPTION);
                }
            } else if (isEvent) {
                try {
                    if (isEmptyDescription(userInput)) {
                        throw new DukeException();
                    }
                    addTask(tasks, Task.taskCount, userInput, TaskType.EVENT);
                } catch (DukeException e) {
                    System.out.println(ERROR_EMPTY_EVENT_DESCRIPTION);
                }
            } else {
                System.out.println(ERROR_UNKNOWN_INPUT);
            }
            System.out.println(horizontalLine);

        } while (!isBye);

    }

    private static void addTask(Task[] taskList, int taskCount, String userInput, TaskType specificTask) {
        final String ADDED_TASK_COMMENT = "Got it. I've added this task:";
        System.out.println(ADDED_TASK_COMMENT);
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
        taskList[taskCount] = newTask;
        Task.taskCount++;

        String printTask = String.format(" [%s][ ] %s", newTask.taskType, newTask.description);
        String printTaskNumber = String.format("Now you have %d items in the list.", Task.taskCount);
        System.out.println(printTask);
        System.out.println(printTaskNumber);
    }

    private static void markTask(Task[] tasks, String userInput) {
        char userInputIntChar = userInput.charAt(userInput.length() - 1);
        int userInputInt = Character.getNumericValue(userInputIntChar);
        final String MARK_TASK_COMMENT = "Nice! I've marked this task as done:";
        tasks[userInputInt].markAsDone();
        String formatOutput = String.format("[%s][%s] %s", tasks[userInputInt].taskType, tasks[userInputInt].getStatusIcon(), tasks[userInputInt].description);

        System.out.println(MARK_TASK_COMMENT);
        System.out.println(formatOutput);
    }

    private static void listTask(Task[] tasks, int taskCount) {
        final String LIST_TASK_COMMENT = "Here are the tasks in your list:";
        System.out.println(LIST_TASK_COMMENT);
        for (int i = 0; i < taskCount; i++) {
            int indexNumber = i + 1;
            String formatOutput = String.format("%d.[%s][%s] %s", indexNumber, tasks[i].taskType, tasks[i].getStatusIcon(), tasks[i].description);
            System.out.println(formatOutput);
        }
    }

    private static boolean isEmptyDescription(String userInput) {
        String[] trimDescription = userInput.trim().split("\\s+", 2);
        return trimDescription.length < 2;
    }

    public static void main(String[] args) {
        Greet();
        listOperations();
    }
}
