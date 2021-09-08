import java.util.Scanner;

public class Duke {
    private static final Task[] taskList = new Task[100];
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String EXIT = "bye";
    private static int taskIndex = 0;

    public static void main(String[] args) {
        greet();
        instructions();
        chooseTask();
    }


    private static void chooseTask() {
        String line, taskType,  taskDescription = null;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        try {
            int typePos = line.indexOf(" ");
            taskType = line.substring(0, typePos);
            taskDescription = line.substring(typePos).trim();
        } catch (StringIndexOutOfBoundsException e) {
            taskType = line;
        }

        switch (taskType) {
        case TODO:
            addTask(TODO, taskDescription);
            break;
        case DEADLINE:
            addTask(DEADLINE, taskDescription);
            break;
        case EVENT:
            addTask(EVENT, taskDescription);
            break;
        case LIST:
            displayList();
            break;
        case DONE:
            markTaskComplete(taskDescription);
            break;
        case EXIT:
            bye();
            return;
        default:
            printDividerLine();
            System.out.println("Invalid Input!");
            printDividerLine();
        }
        chooseTask();
    }

    private static void greet() {
        printDividerLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void instructions() {
        System.out.println("The following can be done: ");
        printDividerLine();
        System.out.println("1.  ToDos: tasks without any date/time attached to it");
        System.out.println("    COMMAND: todo xxx");
        System.out.println("2.  Deadlines: tasks that need to be done before a specific date/time");
        System.out.println("    COMMAND: deadline xxx /by yyy");
        System.out.println("3.  Events: tasks that start at a specific time and ends at a specific time");
        System.out.println("    COMMAND: event xxx /at yyy");
        System.out.println("4.  Display list");
        System.out.println("    COMMAND: list");
        System.out.println("5.  Mark task complete");
        System.out.println("    COMMAND: done x");
        System.out.println("6.  Exit");
        System.out.println("    COMMAND: bye");
        printDividerLine();
    }

    private static void bye() {
        printDividerLine();
        System.out.println("Bye. Hope to see you again soon!");
        printDividerLine();
    }

    private static void printDividerLine() {
        for (int i = 0; i < 30; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private static void addTask(String taskType, String taskDescription) {

        if (taskDescription == null) {
            printDividerLine();
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            printDividerLine();
            return;
        }

        switch (taskType) {
        case TODO:
            taskList[taskIndex] = new ToDo(taskDescription);
            break;
        case DEADLINE:
            if (!taskDescription.contains("/by")) {
                printDividerLine();
                System.out.println("Missing /by time!");
                printDividerLine();
                return;
            }
            taskList[taskIndex] = new Deadline(taskDescription);
            break;
        case EVENT:
            if (!taskDescription.contains("/at")) {
                printDividerLine();
                System.out.println("Missing /at time!");
                printDividerLine();
                return;
            }
            taskList[taskIndex] = new Event(taskDescription);
            break;
        }
        taskIndex++;

        printDividerLine();
        System.out.println("I have added this task:");
        System.out.println("[" + taskList[taskIndex - 1].getType() + "][" + taskList[taskIndex - 1].getStatusIcon() + "] " + taskList[taskIndex - 1].getDescription());
        System.out.println("You have " + taskIndex + " task(s) in the list.");
        printDividerLine();
    }

    private static void displayList() {
        printDividerLine();
        if (taskIndex == 0) {
            System.out.println("to-do list is empty! add something");
            return;
        }
        System.out.println("The current to-do list is as follows:");
        for (int i = 0; i < taskIndex; i++) {
            System.out.println(i + 1 + ". [" + taskList[i].getType() + "][" + taskList[i].getStatusIcon() + "] " + taskList[i].getDescription());
        }
        printDividerLine();
    }

    private static void markTaskComplete(String taskDescription) {
        int taskNumber;

        try {
            taskNumber = Integer.parseInt(taskDescription);
            if (taskNumber > taskIndex) {
                System.out.println("Error! This task does not exist!");
                return;
            }
            taskNumber--;
            taskList[taskNumber].markAsDone();
            printDividerLine();
            System.out.println("I have marked it as completed!");
            System.out.println(taskNumber + 1 + ". [" + taskList[taskNumber].getType() + "][" + taskList[taskNumber].getStatusIcon() + "] " + taskList[taskNumber].getDescription());
            printDividerLine();
        } catch (NumberFormatException e) {
            System.out.println("Error! This task does not exist!");
        }

    }
}
