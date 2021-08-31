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

        String userLineInput = "initialize";
        Task[] taskItems = new Task[100]; //create array to store text entered by user
        int taskCounter = 0; //track number of tasks

        Scanner in = new Scanner(System.in); //Create scanner object to take in input

        while (!userLineInput.equals("bye")) {
            userLineInput = in.nextLine(); //user input
            String[] userWords = userLineInput.split(" ");

            System.out.println("____________________________________________________________");
            switch (userWords[0]) {
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCounter; i++) {
                    System.out.println((i + 1) + "." + taskItems[i].description);
                }
                System.out.println("____________________________________________________________");
                continue;

            case "done":
                userInputIsDone(taskItems, taskCounter, userWords[1]);
                continue;

            case "bye":
                userInputIsBye();
                break;

            default:
                taskItems[taskCounter] = new Task(userLineInput);
                System.out.println("added: " + userLineInput);
                taskCounter++;
                System.out.println("____________________________________________________________");
            }
        }
    }

    private static void userInputIsDone(Task[] taskItems, int taskCounter, String userWord) {
        int doneInteger = Integer.parseInt(userWord);
        System.out.println("Nice! I've marked this task as done:");
        for (int i = 0; i < taskCounter; i++) {
            if (i == (doneInteger - 1)) {
                taskItems[i].markAsDone();
                System.out.println(doneInteger + "." + taskItems[i].description);
                break;
            }
        }
        System.out.println("____________________________________________________________");
    }

    private static void userInputIsBye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
