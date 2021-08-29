import java.util.Scanner;

public class Duke {
    private static final Task[] taskList = new Task[100];
    private static int taskIndex = 0;

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        chooseTask();
    }

    private static void chooseTask() {
        printLine();
        System.out.println("Choose a task to perform:");
        System.out.println("1. Echo");
        System.out.println("2. Add task");
        System.out.println("3. Display list");
        System.out.println("4. Mark task complete");
        printLine();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        switch (line) {
        case "1":
            echo();
            break;
        case "2":
            addTask();
            break;
        case "3":
            displayList();
            break;
        case "4":
            markTaskComplete();
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
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static void bye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    private static void printLine() {
        for (int i = 0; i < 30; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private static void echo() {
        String line;
        Scanner in = new Scanner(System.in);

        printLine();
        System.out.println("What do you want me to repeat?");
        System.out.println("*Type 'exit echo' to leave*");
        printLine();

        line = in.nextLine();
        if (line.equals("exit echo")) {
            return;
        }
        System.out.println("You said: " + line);
        echo();
    }

    private static void addTask() {
        String line;
        Scanner in = new Scanner(System.in);

        printLine();
        System.out.println("What task do you want to add?");
        printLine();

        line = in.nextLine();
        //Split the input into type and description
        int typePos = line.indexOf(" ");
        String taskType = line.substring(0, typePos);
        String taskDescription = line.substring(typePos).trim();

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

        printLine();
        System.out.println("I have added this task:");
        System.out.println("[" + taskList[taskIndex-1].getType() +"][" + taskList[taskIndex-1].getStatusIcon() + "] " + taskList[taskIndex-1].getDescription());
        System.out.println("You have " + taskIndex + " task(s) in the list.");
        printLine();
    }

    private static void displayList() {
        printLine();
        if (taskIndex == 0) {
            System.out.println("to-do list is empty! add something");
            return;
        }
        System.out.println("The current to-do list is as follows:");
        for (int i = 0; i < taskIndex; i++) {
            System.out.println(i + 1 + ". [" + taskList[i].getType() +"][" + taskList[i].getStatusIcon() + "] " + taskList[i].getDescription());
        }
        printLine();
    }

    private static void markTaskComplete() {
        printLine();
        System.out.println("Which Task is complete?");
        printLine();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        int taskNumber = Integer.parseInt(line) - 1;

        if (taskNumber > taskIndex) {
            System.out.println("Error! This task does not exist!");
            return;
        }
        taskList[taskNumber].markAsDone();
        printLine();
        System.out.println("I have marked it as completed!");
        System.out.println(taskNumber + 1 + ". [" + taskList[taskNumber].getType() +"][" + taskList[taskNumber].getStatusIcon() + "] " + taskList[taskNumber].getDescription());
        printLine();
    }
}
