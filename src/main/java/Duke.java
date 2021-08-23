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
        Task[] taskList = new Task[100];

        do {
            command = in.nextLine();

            if (command.contains("done")) {
                int itemNum = Integer.parseInt(command.replaceAll("[^0-9]", "")) - 1;
                taskList[itemNum].setDone();
                System.out.println(separator);
                System.out.println("\tNice! I've marked this task as done:");
                System.out.printf("\t  [%s] %s\n", taskList[itemNum].getStatusIcon(), taskList[itemNum].getDescription());
                System.out.println(separator);
            } else {
                switch (command) {
                case "bye":
                    isDone = true;
                    System.out.println(separator);
                    System.out.println("\tBye. Hope to see you again soon!");
                    System.out.println(separator);
                    break;
                case "list":
                    System.out.println(separator);
                    for (int i = 0; i < Task.getNumOfTasks(); i++) {
                        System.out.printf("\t%d.[%s] %s\n", i + 1, taskList[i].getStatusIcon(), taskList[i].getDescription());
                    }
                    System.out.println(separator);
                    break;
                default:
                    taskList[Task.getNumOfTasks()] = new Task(command);
                    System.out.println(separator);
                    System.out.println("\tadded: " + command);
                    System.out.println(separator);
                    break;
                }
            }
        } while (!isDone);
    }
}
