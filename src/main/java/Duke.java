import java.util.Scanner;

public class Duke {

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greet = "____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(greet);
    }

    public static void main(String[] args) {
        greet();

        String userInput;
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        boolean shouldBreak = false;
        int numberOfTasks = 0;

        while (true) {
            userInput = sc.nextLine();
            String output = "";

            switch (userInput) {
            case "list":
                output = "____________________________________________________________\n";
                for (int i = 1; i < numberOfTasks + 1; i++) {
                    output = output + i + ".[" + tasks[i - 1].getStatusIcon() + "] " + tasks[i - 1].getDescription() + "\n";
                }
                output = output + "____________________________________________________________\n";
                System.out.println(output);
                break;
            case "bye":
                output = "____________________________________________________________\n"
                        + " Bye. Hope to see you again soon!\n"
                        + "____________________________________________________________\n";
                System.out.println(output);
                shouldBreak = true;
                break;
            default:
                if (userInput.contains("done ")) {
                    // Mark as done
                    userInput = userInput.replace("done ", "");
                    int taskNumber = Integer.parseInt(userInput) - 1;

                    tasks[taskNumber].markAsDone();
                    output = "____________________________________________________________\n"
                            + "Nice! I've marked this task as done:\n"
                            + "  [X] " + tasks[taskNumber].getDescription() + "\n"
                            + "____________________________________________________________\n";
                    System.out.println(output);
                } else {
                    // Add tasks
                    Task newTask = new Task(userInput);
                    tasks[numberOfTasks] = newTask;
                    numberOfTasks++;
                    output = "____________________________________________________________\n"
                            + "added: " + userInput + "\n"
                            + "____________________________________________________________\n";
                    System.out.println(output);
                }
                break;
            }
            if (shouldBreak == true) {
                break;
            }
        }

    }
}
