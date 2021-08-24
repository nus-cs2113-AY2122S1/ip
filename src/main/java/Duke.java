import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {
    public static void printWelcome() {
        String welcomeMessage = "____________________________________________________________\n"
                + "Hello! I'm your friendly chatbot, John!\n"
                + "How can I help?\n"
                + "____________________________________________________________\n";
        System.out.println(welcomeMessage);
    }

    public static void printAddedToList(String line) {
        String repeatMessage = "____________________________________________________________\n"
                + "\"" + line + "\"" + " has been added to the list!\n"
                + "____________________________________________________________\n";
        System.out.println(repeatMessage);
    }

    public static void printExit() {
        String exitMessage = "____________________________________________________________\n"
                + "Thanks for talking with me, see you soon!\n"
                + "____________________________________________________________\n";
        System.out.println(exitMessage);
    }

    public static int filterNumDone(String doneTask) {
        String[] words = doneTask.split(" ");
        if (words.length > 1) { // simple check to see if task number has not been input
            return Integer.parseInt(words[1]);
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;
        Task[] storedTasksArr = new Task[100]; // Store up to 100 tasks.
        int storedTasksCounter = 0;

        printWelcome();
        line = in.nextLine();

        // While input is not "bye", keep taking inputs.
        while (!line.equals("bye")) {
            if (line.equals("list")) { // get List
                System.out.println("___________________________________________________________\n"
                    + "As requested, here are the tasks in your list:");
                for (int i = 0; i < storedTasksCounter; i++) {
                    System.out.println(i + 1 + ". " + "[" + storedTasksArr[i].getStatusIcon() +"] " + storedTasksArr[i].description);
                }
                System.out.println("____________________________________________________________");
            }
            else if (line.contains("done")) { // mark task as Done
                int numOfDone = filterNumDone(line);
                if (numOfDone == -1) { // Error case
                    System.out.println("Missing task number to remove. Please try again.");
                }
                else if ((numOfDone - 1 >= 0) && (storedTasksArr[numOfDone - 1] != null)) {
                    storedTasksArr[numOfDone - 1].markAsDone();
                    System.out.println("___________________________________________________________\n"
                            + "Great work! I've marked this task as done:\n"
                            + "[" + storedTasksArr[numOfDone - 1].getStatusIcon() +"] " + storedTasksArr[numOfDone - 1].description + "\n"
                            + "___________________________________________________________");
                }
                else {
                    System.out.println("That is not a valid task number! Please try again.");
                }
            }
            else {
                storedTasksArr[storedTasksCounter] = new Task(line);
                storedTasksCounter++;
                printAddedToList(line);
            }
            line = in.nextLine();
        }
        printExit();
    }
}
