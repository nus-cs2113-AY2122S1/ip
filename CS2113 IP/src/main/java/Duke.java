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
        boolean isBye;
        boolean isList;
        boolean isDone;
        boolean isTodo;
        boolean isDeadline;
        boolean isEvent;

        Task[] taskList = new Task[100];

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
                System.out.println("Bye. Hope to see you again soon!");
            } else if (isList) {
                listTask(taskList, Task.taskCount);
            } else if (isDone) {
                markTask(taskList, userInput);
            } else if (isTodo) {
                addTask(taskList, Task.taskCount, userInput, TaskType.TODO);
            } else if (isDeadline) {
                addTask(taskList, Task.taskCount, userInput, TaskType.DEADLINE);
            } else if (isEvent) {
                addTask(taskList, Task.taskCount, userInput, TaskType.EVENT);
            } else {
                System.out.println("Error input..");
            }
            System.out.println(horizontalLine);

        } while (!isBye);
    }

    private static void addTask(Task[] taskList, int taskCount, String userInput, TaskType specificTask) {
        System.out.println("Got it. I've added this task:");
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

    private static void markTask(Task[] taskList, String userInput) {
        char userInputIntChar = userInput.charAt(userInput.length() - 1);
        int userInputInt = Character.getNumericValue(userInputIntChar);
        taskList[userInputInt].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        String formatOutput = String.format("[%s][%s] %s", taskList[userInputInt].taskType, taskList[userInputInt].getStatusIcon(), taskList[userInputInt].description);
        System.out.println(formatOutput);
    }

    private static void listTask(Task[] taskList, int taskCount) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            int indexNumber = i + 1;
            String formatOutput = String.format("%d.[%s][%s] %s", indexNumber, taskList[i].taskType, taskList[i].getStatusIcon(), taskList[i].description);
            System.out.println(formatOutput);
        }
    }

    public static void main(String[] args) {
        Greet();
        listOperations();
    }
}
