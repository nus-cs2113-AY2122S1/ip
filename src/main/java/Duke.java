import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void printLine() {
        System.out.println("                 ...                 ");
    }

    public static void sayHi() {
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void sayGoodbye() {
        printLine();
        System.out.println("Bye. Hope to see you  again soon!");
        printLine();
    }

    public static String echoCommand() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        System.out.println("    " + command);
        return command;
    }

    public static void listTasks(Task[] items) {
        int in = 1;
        System.out.println(" / start of list / ");
        for (Task item : items) {
            if (item != null) {
                String tick = (item.isDone()) ? "✓" : " ";
                System.out.println(in + ". " + "[" + tick + "]" + " " + item.getDescription());
                in++;
            }
        }
        System.out.println(" / end of list / ");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        sayHi();
        String command;
        Task[] taskList = new Task[100];
        int taskCount = 0;
        do {
            command = in.nextLine();
            switch (command) {
            case ("list"):
                listTasks(taskList);
                break;
            case ("add"):
                String taskName;
                do {
                    taskName = in.nextLine();
                    if (taskName.equals("stop")) {
                        break;
                    }
                    taskList[taskCount] = new Task(taskName);
                    taskCount++;
                } while (!taskName.equals("stop"));
                System.out.println("Finished adding tasks!");
                break;

            case ("done"):
                String number = in.nextLine();
                String[] numberList = number.split(" ");
                System.out.print("remove ");
                for (String i : numberList) {
                    int index = Integer.parseInt(i) - 1;
                    taskList[index].setDone(true);
                    System.out.print(taskList[index].getDescription() + ", ");
                }
                System.out.println("\n / done tasks, good job! / ");
                break;
            case ("clear"):
                taskList = new Task[100];
                taskCount = 0;
                break;
            case ("bye"):
                break;
            default:
                System.out.println("Command not recognized. Please enter a command again!");
            }
        } while (!command.equals("bye"));
        sayGoodbye();
    }
}
