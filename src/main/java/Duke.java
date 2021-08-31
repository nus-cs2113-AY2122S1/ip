import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final String HORIZONTAL_LINE = "------------------------------------------------------";
    public static final String STRING_LOGO =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        String userInput = "";

        System.out.println("Hello from\n" + STRING_LOGO);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Duke!\n" + "So far, I can create a simple task list for you.\n" + "What can I do for you?\n");

        while (!userInput.startsWith("bye")) {
            userInput = in.nextLine().toLowerCase();
            if (userInput.contains("help")) {
                printHelpList();
            } else if (userInput.startsWith("to do")) {
                addTaskAsToDo(taskList, userInput);
            } else if (userInput.startsWith("deadline")) {
                addTaskAsDeadline(taskList, userInput);
            } else if (userInput.startsWith("event")) {
                addTaskAsEvent(taskList, userInput);
            } else if (userInput.startsWith("list")) {
                printTaskList(taskList);
            } else if (userInput.startsWith("done")) {
                markTaskAsDone(taskList, userInput);
            } else if (!userInput.startsWith("bye")) {
                System.out.println("Aw man! I am unable to " + userInput + " yet! Please specify a different function! :D");
            }
        }
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void addTaskAsEvent(ArrayList<Task> taskList, String userInput) {
        if (userInput.contains("/at")) {
            Task taskAdded = new Event(userInput);
            taskList.add(taskAdded);
            printTaskAddedConfirmation(taskAdded);
        } else {
            System.out.println("Please specify a time!");
        }
    }

    private static void addTaskAsDeadline(ArrayList<Task> taskList, String userInput) {
        if (userInput.contains("/by")) {
            Task taskAdded = new Deadline(userInput);
            taskList.add(taskAdded);
            printTaskAddedConfirmation(taskAdded);
        } else {
            System.out.println("Please specify a deadline!");
        }
    }

    private static void addTaskAsToDo(ArrayList<Task> taskList, String userInput) {
        String task = userInput.replace("to do", "").trim();
        Task taskAdded = new Todo(task);
        taskList.add(taskAdded);
        printTaskAddedConfirmation(taskAdded);
    }

    private static void markTaskAsDone(ArrayList<Task> taskList, String userInput) {
        int wordIndex = 0;
        boolean numberExists = false;
        String[] splitTask = userInput.replaceAll("[\\p{Alpha}, \\p{Punct}]", " ").trim().split(" ");
        for (String word : splitTask) {
            if (isValidNumber(word)) {
                numberExists = true;
                int taskNumber = (Integer.parseInt(splitTask[wordIndex])) - 1;
                if (taskNumber > taskList.size() - 1 || taskNumber < 0) {
                    System.out.println("Invalid task number");
                } else {
                    printTaskMarkAsDone(taskList, taskNumber);
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

    private static void printTaskMarkAsDone(ArrayList<Task> taskList, int taskNumber) {
        Task taskUpdated = taskList.get(taskNumber);
        taskUpdated.updateIsDone();
        taskUpdated.printMarkAsDoneMessage(taskNumber);
    }

    private static void printTaskList(ArrayList<Task> taskList) {
        int listIndex = 1;
        System.out.println(HORIZONTAL_LINE);
        for (Task task : taskList) {
            task.printTaskList(listIndex);
            listIndex++;
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printTaskAddedConfirmation(Task taskAdded) {
        System.out.println(HORIZONTAL_LINE);
        taskAdded.printTaskAddedMessage();
        System.out.println(HORIZONTAL_LINE);
    }

    private static void printHelpList() {
        System.out.println("Use the following commands!\n" + "To add a task without deadline: to do [taskName]\n" + "To add a task with a deadline: deadline [deadlineName] /by [deadline]\n" +
                "To add an event: event [eventName] /at [eventTime]\n" + "To mark a task as done: done [taskNumber]");
        System.out.println("To view your current task list, simply type: show list\n" + "To end your chat with me, simply type: bye");
        System.out.println(HORIZONTAL_LINE);
    }
}
