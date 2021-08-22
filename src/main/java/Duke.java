import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "████████ ██████  ██ ███████ ███████ \n" +
                "   ██    ██   ██ ██ ██      ██      \n" +
                "   ██    ██████  ██ ███████ ███████ \n" +
                "   ██    ██   ██ ██      ██      ██ \n" +
                "   ██    ██   ██ ██ ███████ ███████ \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Triss :)");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        // Initialise boolean to track if user has said "bye"
        boolean hasUserSaidBye = false;
        Scanner in = new Scanner(System.in);

        // Initialise array to keep track of user's tasks
        Task[] tasks = new Task[100];
        int noOfTasks = 0;

        // While user has not said "bye", check for next line of input
        while (!hasUserSaidBye) {

            String userInput = in.nextLine();
            System.out.println("____________________________________________________________");

            // Check if user has said "bye"
            if (userInput.equals("bye")) {
                // If user said "bye", update hasUserSaidBye and print closing phrase
                hasUserSaidBye = true;
                System.out.println("Thanks for coming. Auf wiedersehen!");
            } else if (userInput.equals("list")) {
                // If user said "list", print a list of all saved tasks
                for (int i = 0; i < noOfTasks; i++) {
                    System.out.println(Integer.toString(i + 1) + "." + tasks[i].getDoneStatusAsSymbol()
                            + " " + tasks[i].getName());
                }
            } else if (userInput.contains("done")) {
                // Get number of task after the term "done"
                int indexOfCompletedTask = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (indexOfCompletedTask > noOfTasks - 1 || indexOfCompletedTask < 0) {
                    // If task does not exist, do not delete any task
                    System.out.println("Apologies! That task does not exist.");
                } else {
                    Task chosenTask = tasks[indexOfCompletedTask];
                    if (chosenTask.isDone()) {
                        // If task was already done, let user know
                        System.out.println("Oh! This task was already marked as done:");
                    } else {
                        chosenTask.setDone(true);
                        // Else, let user know task has been marked as done
                        System.out.println("Wunderbar! This task has been marked as done:");
                    }
                    // Prints out the task in the following format: "    [X] Task"
                    System.out.println("    "
                            + chosenTask.getDoneStatusAsSymbol() + " " + chosenTask.getName());
                }
            } else {
                // If user has not said any other command, store user input as task
                tasks[noOfTasks] = new Task(userInput);
                noOfTasks++;
                // Then, echo the task
                System.out.println("I've added: " + userInput);
            }

            System.out.println("____________________________________________________________");

        }
    }
}
