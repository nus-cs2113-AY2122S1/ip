import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String Logo = " _____         _____\n"
                + "|     \\ _____ |     \\ _____\n"
                + "|  o  /|     ||  o  /|     |\n"
                + "|  o  \\|  o  ||  o  \\|  o  |\n"
                + "|_____/|_____||_____/|_____|\n";
        String horizontalLine = "____________________________________________________________";

        Task[] tasks = new Task[100];
        int numberOfTasks = 0;
        String line;
        boolean isRunning = true;
        Scanner in = new Scanner(System.in);

        System.out.println(Logo);
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Bobo!");
        System.out.println("I'm a little blur, but I'd love to help!");
        System.out.println(horizontalLine);

        while (isRunning) {
            line = in.nextLine();

            if (line.equals("bye")) {
                isRunning = false;
            } else if(line.equals("list")) {
                System.out.println(horizontalLine);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < numberOfTasks; i++) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }
                System.out.println(horizontalLine);
            } else if (line.startsWith("done")) {
                int taskNumber = Integer.parseInt(line.substring(4).trim()) - 1;
                tasks[taskNumber].markAsDone();

                System.out.println(horizontalLine);
                System.out.println("Okie! Marked this as done:");
                System.out.println((taskNumber + 1) + "." + tasks[taskNumber]);
                System.out.println(horizontalLine);
            } else if (line.startsWith("todo")) {
                String description = line.substring(5);
                tasks[numberOfTasks] = new Todo(description);
                numberOfTasks++;

                System.out.println(horizontalLine);
                System.out.println("Umm ok added:");
                System.out.println("  " + tasks[numberOfTasks-1]);
                System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
                System.out.println(horizontalLine);
            } else if (line.startsWith("deadline")) {
                int endOfDescriptionIndex = line.indexOf("/by") - 1;
                int startOfByIndex = line.indexOf("/by") + 4;
                String description = line.substring(9, endOfDescriptionIndex);
                String by = line.substring(startOfByIndex);

                tasks[numberOfTasks] = new Deadline(description, by);
                numberOfTasks++;

                System.out.println(horizontalLine);
                System.out.println("Umm ok added:");
                System.out.println("  " + tasks[numberOfTasks-1]);
                System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
                System.out.println(horizontalLine);
            } else if (line.startsWith("event")) {
                int endOfDescriptionIndex = line.indexOf("/at") - 1;
                int startOfAtIndex = line.indexOf("/at") + 4;
                String description = line.substring(6, endOfDescriptionIndex);
                String at = line.substring(startOfAtIndex);

                tasks[numberOfTasks] = new Event(description, at);
                numberOfTasks++;

                System.out.println(horizontalLine);
                System.out.println("Umm ok added:");
                System.out.println("  " + tasks[numberOfTasks-1]);
                System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
                System.out.println(horizontalLine);
            } else {
                System.out.println(horizontalLine);
                System.out.println("Sorry I don't understand that! Can you rephrase?");
                System.out.println(horizontalLine);
            }
        }

        System.out.println(horizontalLine);
        System.out.println("Ok. Bye bye!");
        System.out.println(horizontalLine);
    }
}
