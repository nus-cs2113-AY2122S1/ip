import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("");
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        String echo; //echo variable to take in user input
        String[] taskList = new String[100]; //create array to store text entered by user
        int taskCount = 0; //track number of tasks

        Scanner in = new Scanner(System.in); //Create scanner object to take in input
        do {
            echo = in.nextLine(); //user input

            System.out.println("____________________________________________________________");
            switch(echo) {
            case "list":
                for(int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + taskList[i]);
                }
                System.out.println("____________________________________________________________");
                continue;
            default:
            System.out.println("added: " + echo);
            taskList[taskCount] = echo;
            taskCount++;
            System.out.println("____________________________________________________________");
            continue;
            }
        } while (!echo.equals("bye"));

        if (echo.equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println("____________________________________________________________");
            return;
        }
    }
}
