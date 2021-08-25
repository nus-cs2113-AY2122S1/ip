import java.util.Scanner;

public class Duke {
    private static Task[] taskList = new Task[100];
    //private static String[] tasklist = new String[100];
    private static int taskindex = 0;

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
        /*
        if(line.equals("1")){
            echo();
        }

        if(line.equals("2")){
            addTask();
        }

        if(line.equals("3")){
            displayList();
        }

        if(line.equals("bye")){
            bye();
            return;
        }
        */
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
        taskList[taskindex] = new Task(line);
        taskindex++;

        printLine();
        System.out.println("added: " + line);
        printLine();
    }

    private static void displayList() {
        printLine();
        if (taskindex == 0) {
            System.out.println("to-do list is empty! add something");
            return;
        }
        System.out.println("The current to-do list is as follows:");
        for (int i = 0; i < taskindex; i++) {
            System.out.println(i + 1 + ". [" + taskList[i].getStatusIcon() + "] " + taskList[i].getDescription());
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

        if (taskNumber > taskindex) {
            System.out.println("Error! This task does not exist!");
            return;
        }

        taskList[taskNumber].markAsDone();

        printLine();
        System.out.println("I have marked it as completed!");
        System.out.println(taskNumber + 1 + ". [" + taskList[taskNumber].getStatusIcon() + "] " + taskList[taskNumber].getDescription());
        printLine();
    }
}
