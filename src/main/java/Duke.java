import java.util.Scanner;

public class Duke {

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        int i;
        String userInput;
        Task[] scheduledTasks = new Task[100];
        int taskCounter = 0;
        String taskCompletionStatus = "";

        Scanner in = new Scanner(System.in);

        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();

        userInput = in.nextLine();

        while (!(userInput.equals("bye"))) {
            if (userInput.equals("list")) {
                printLine();
                if (taskCounter == 0) {
                    System.out.println("Sorry, no tasks have been added to the list as yet! \n" +
                            "You can add tasks to this list simply by typing and pressing \"Enter\"!!");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (i = 0; i < taskCounter; i++) {
                        taskCompletionStatus = scheduledTasks[i].getStatus();
                        System.out.print((i + 1) + ".");
                        scheduledTasks[i].printTaskAndStatus();
                    }
                }
            } else if (userInput.startsWith("done")) {
                printLine();
                int taskNumberCompleted = Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1));
                if ((taskNumberCompleted <= taskCounter) && (taskNumberCompleted > 0)) {
                    scheduledTasks[taskNumberCompleted - 1].markAsDone();
                    System.out.println("Nice! I have marked this task as done:");
                    scheduledTasks[taskNumberCompleted - 1].printTaskAndStatus();
                } else {
                    System.out.println("Sorry, no task is assigned at this number, you might want to re-check?");
                }
            } else {
                scheduledTasks[taskCounter] = new Task(userInput);
                taskCounter++;
                printLine();
                System.out.println("added : " + userInput);
            }

            printLine();
            userInput = in.nextLine();
        }

        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();

    }
}

