import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        String userInput = "initialize";
        Task[] taskItems = new Task[100]; //create array to store text entered by user
        int taskCount = 0; //track number of tasks

        Scanner in = new Scanner(System.in); //Create scanner object to take in input

        while (!userInput.equals("bye")) {
            userInput = in.nextLine(); //user input
            String[] userWords = userInput.split(" ");

            System.out.println("____________________________________________________________");
            switch (userWords[0]) {
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + taskItems[i].description);
                }
                System.out.println("____________________________________________________________");
                continue;

            case "done":
                int doneInteger = Integer.parseInt(userWords[1]);
                System.out.println("Nice! I've marked this task as done:");
                for (int i = 0; i < taskCount; i++) {
                    if (i == (doneInteger - 1)) {
                        taskItems[i].markAsDone();
                        System.out.println(doneInteger + "." + taskItems[i].description);
                        break;
                    }
                }

                System.out.println("____________________________________________________________");
                continue;

            case "bye":
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;

            default:
                taskItems[taskCount] = new Task(userInput);
                System.out.println("added: " + userInput);
                taskCount++;
                System.out.println("____________________________________________________________");
            }
        }
    }
}
