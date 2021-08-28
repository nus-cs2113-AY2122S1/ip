import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        String horizontalLine = "------------------------------------------------------";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String userInput = "";

        System.out.println("Hello from\n" + logo);
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Duke!\n" + "So far, I can create a simple task list for you.\n" + "What can I do for you?\n");

        while (!userInput.equalsIgnoreCase("bye")) {
            userInput = in.nextLine().toLowerCase();

            if (userInput.contains("help")) {
                printHelpList(horizontalLine);
            } else if (userInput.contains("to do")) {
                addTaskAsToDo(taskList, userInput);
            } else if (userInput.contains("deadline")) {
                addTaskAsDeadline(taskList, userInput);
            } else if (userInput.contains("event")) {
                addTaskAsEvent(taskList, userInput);
            } else if (userInput.contains("list")) {
                printTaskList(taskList, horizontalLine);
            } else if (userInput.contains("done")) {
                markTaskAsDone(taskList, userInput);
            } else if (!userInput.contains("bye")) {
                System.out.println("Aw man! I am unable to " + userInput + " yet! Please specify a different function! :D");
            }
        }
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(horizontalLine);
    }

    private static void addTaskAsEvent(ArrayList<Task> taskList, String userInput) {
        Task taskAdded;
        if (userInput.contains("/at")) {
            taskAdded = new Event(userInput);
            taskList.add(taskAdded);
            printTaskAddedConfirmation(taskAdded.description);
        } else {
            System.out.println("Please specify a time!");
        }
    }

    private static void addTaskAsDeadline(ArrayList<Task> taskList, String userInput) {
        Task taskAdded;
        if (userInput.contains("/by")) {
            taskAdded = new Deadline(userInput);
            taskList.add(taskAdded);
            printTaskAddedConfirmation(taskAdded.description);
        } else {
            System.out.println("Please specify a deadline!");
        }
    }

    private static void addTaskAsToDo(ArrayList<Task> taskList, String userInput) {
        Task taskAdded;
        String task = userInput.replace("to do", "").trim();
        taskAdded = new Todo(task);
        taskList.add(taskAdded);
        printTaskAddedConfirmation(taskAdded.description);
    }

    private static void markTaskAsDone(ArrayList<Task> taskList, String userInput) {
        int wordIndex = 0;
        boolean numberExists = false;
        String[] splitTask = userInput.replaceAll("[\\p{Alpha}, \\p{Punct}]", " ").trim().split(" ");
        for (String word : splitTask) {
            if (isValidNumber(word)) {
                numberExists = true;
                int i = (Integer.parseInt(splitTask[wordIndex])) - 1;
                if (i > taskList.size() - 1 || i < 0) {
                    System.out.println("Invalid task number");
                } else {
                    printTaskMarkAsDone(taskList, i);
                }
            } else if (!numberExists && ((wordIndex + 1) >= splitTask.length)) {
                System.out.println("No number specified! Please specify the number on the list of the task you have completed!");
            }
            wordIndex++;
        }
    }

    public static boolean isValidNumber(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private static void printTaskMarkAsDone(ArrayList<Task> taskList, int i) {
        Task taskUpdated = taskList.get(i);
        taskUpdated.updateIsDone();
        if (taskUpdated.getTaskType().equalsIgnoreCase("T")) {
            System.out.println("Nice! I've marked this task as done:\n" + (i + 1) + ".[" + taskUpdated.getTaskType() + "]" + "[" + taskUpdated.getStatusIcon() + "] " + taskUpdated.description);
        } else if (taskUpdated.getTaskType().equalsIgnoreCase("D")) {
            System.out.println("Nice! I've marked this task as done:\n" + (i + 1) + ".[" + taskUpdated.getTaskType() + "]" + "[" + taskUpdated.getStatusIcon() + "] " + taskUpdated.description + " (by:" + taskUpdated.getDeadline() + ")");
        } else if (taskUpdated.getTaskType().equalsIgnoreCase("E")) {
            System.out.println("Nice! I've marked this task as done:\n" + (i + 1) + ".[" + taskUpdated.getTaskType() + "]" + "[" + taskUpdated.getStatusIcon() + "] " + taskUpdated.description + " (at:" + taskUpdated.getDeadline() + ")");
        }
    }

    private static void printTaskList(ArrayList<Task> taskList, String horizontalLine) {
        int listIndex = 1;
        System.out.println(horizontalLine);
        for (Task task : taskList) {
            if (task.getTaskType().equalsIgnoreCase("T")) {
                System.out.println(listIndex + ".[" + task.getTaskType() + "]" + "[" + task.getStatusIcon() + "] " + task.description);
                listIndex++;
            } else if (task.getTaskType().equalsIgnoreCase("D")) {
                System.out.println(listIndex + ".[" + task.getTaskType() + "]" + "[" + task.getStatusIcon() + "] " + task.description + " (by:" + task.getDeadline() + ")"); // edited
                listIndex++;
            } else if (task.getTaskType().equalsIgnoreCase("E")) {
                System.out.println(listIndex + ".[" + task.getTaskType() + "]" + "[" + task.getStatusIcon() + "] " + task.description + " (at:" + task.getDeadline() + ")"); // edited
                listIndex++;
            }
        }
        System.out.println(horizontalLine);
    }

    public static void printTaskAddedConfirmation(String taskAdded) {
        String horizontalLine = "------------------------------------------------------";
        System.out.println(horizontalLine);
        System.out.println("I can do that! I have added [" + taskAdded + "] to your task list!");
        System.out.println(horizontalLine);
    }

    private static void printHelpList(String horizontalLine) {
        System.out.println("Use the following commands!\n" + "To add a task without deadline: to do [taskName]\n" + "To add a task with a deadline: deadline [deadlineName] /by [deadline] \n" +
                "To add an event: event [eventName] /at [eventTime]\n" + "To mark a task as done: done [taskNumber]");
        System.out.println("To view your current task list, simply type: show list\n" + "To end your chat with me, simply type: bye");
        System.out.println(horizontalLine);
    }
}
