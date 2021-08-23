import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String separator = "\t____________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println(separator);
        System.out.println("\tHello I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println(separator);

        Scanner in = new Scanner(System.in);
        String command;
        boolean isDone = false;
        int numOfTasks = 0;
        String[] taskList = new String[100];

        do {
            command = in.nextLine();
            switch (command){
            case "bye":
                isDone = true;
                System.out.println(separator);
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println(separator);
                break;
            case "list":
                System.out.println(separator);
                for (int i = 0; i < numOfTasks; i++) {
                    System.out.printf("\t%d. %s\n", i+1, taskList[i]);
                }
                System.out.println(separator);
                break;
            default:
                taskList[numOfTasks] = command;
                numOfTasks++;
                System.out.println(separator);
                System.out.println("\tadded: " + command);
                System.out.println(separator);
                break;
            }
        } while (!isDone);
    }
}
