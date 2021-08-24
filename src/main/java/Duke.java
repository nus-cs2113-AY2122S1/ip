import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greet =  "------------------------\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "------------------------\n";

        String userCommand;
        Task[] userTasks = new Task[100];

        int numberOfTasks = 0;

        Scanner input = new Scanner(System.in);

        System.out.println("Hello from\n" + logo);
        System.out.println(greet);

        userCommand = input.nextLine();

        while (!userCommand.equals("bye")) {
            System.out.println("------------------------");

            if (userCommand.equals("list")) {
                for (int i = 0; i < numberOfTasks; i++) {
                    System.out.println((i + 1) + ".["
                            + userTasks[i].getStatusIcon()
                            + "] "
                            + userTasks[i].getTaskDescription());
                }
            } else if (userCommand.contains("done")) {
                String taskDone = userCommand.substring(5);
                int taskDoneNumber = Integer.parseInt(taskDone);

                if (taskDoneNumber > numberOfTasks) {
                    System.out.println("Task number have not existed!");
                } else {
                    userTasks[taskDoneNumber - 1].setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[X] "
                            + userTasks[taskDoneNumber - 1].getTaskDescription());
                }
            } else {
                userTasks[numberOfTasks] = new Task(userCommand);
                System.out.println("added: " + userCommand);
                numberOfTasks++;
            }

            System.out.println("------------------------");
            userCommand = input.nextLine();
        }

        System.out.println("------------------------\n"
                + "Bye. Hope to see you again soon!\n"
                + "------------------------");
    }
}
