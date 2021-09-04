import java.util.Scanner;

public class Duke {
    private static final Task[] taskList = new Task[100];
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
        case "todo":
            addTask("todo", taskDescription);
            break;
        case "deadline":
            addTask("deadline", taskDescription);
            break;
        case "event":
            addTask("event", taskDescription);
            break;
        case "list":
            displayList();
            break;
        case "done":
            markTaskComplete(taskDescription);
            break;
        case "bye":
            bye();
            return;
        default:
            System.out.println("Invalid Input!");
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
        System.out.println("modus tolens");
        System.out.println("1. Echo");
        System.out.println("2. Add task");
        System.out.println("3. Display list");
        System.out.println("4. Mark task complete");
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

    private static void echo() {
        String line;
        Scanner in = new Scanner(System.in);

        printDividerLine();
        System.out.println("What do you want me to repeat?");
        System.out.println("*Type 'exit echo' to leave*");
        printDividerLine();

        line = in.nextLine();
        if (line.equals("exit echo")) {
            return;
        }
        System.out.println("You said: " + line);
        echo();
    }

    private static void addTask(String taskType, String taskDescription) {

        switch (taskType) {
        case "todo":
            taskList[taskIndex] = new ToDo(taskDescription);
            break;
        case "deadline":
            taskList[taskIndex] = new Deadline(taskDescription);
            break;
        case "event":
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
