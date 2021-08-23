import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String horizontalLine = "____________________________________________________________\n";

    public static void main(String[] args) {

        String logo = " ______    _   __   __            \n" +
                "|_   _ \\  (_) [  | [  |           \n" +
                "  | |_) | __   | |  | |   _   __  \n" +
                "  |  __'.[  |  | |  | |  [ \\ [  ] \n" +
                " _| |__) || |  | |  | |   \\ '/ /  \n" +
                "|_______/[___][___][___][\\_:  /   \n" +
                "                         \\__.'    \n";

        String greetingMessage = horizontalLine +
                " Hello! I'm Billy\n" +
                " What can I do for you?\n" +
                horizontalLine;
        String goodbyeMessage = horizontalLine +
                " Bye. Hope to see you again soon!\n" +
                horizontalLine;

        boolean programIsRunning = true;
        String userInput;
        List<Task> list = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.println("Hello from\n" + logo + greetingMessage);

        while (programIsRunning) {
            userInput = in.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(goodbyeMessage);
                programIsRunning = false;
            } else if (userInput.equalsIgnoreCase("list")) {
                printList(list);
            } else if (userInput.toLowerCase().startsWith("done")) {
                markTasksAsDone(userInput, list);
            } else {
                list.add(new Task(userInput));
                System.out.println(horizontalLine + "added: " + userInput + "\n" + horizontalLine);
            }
        }

    }

    public static void printList(List<Task> list) {
        Task task;
        System.out.println(horizontalLine + "Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            task = list.get(i);
            System.out.println((i + 1) + ". [" + task.getStatusIcon() + "] " + task.getDescription());
        }
        System.out.println(horizontalLine);
    }

    /**
     * Marks and prints given tasks as done. Accepts multiple tasks in one input and provides feedback
     * if invalid inputs are detected.
     *
     * @param userInput String of user input containing task numbers to be marked as done.
     * @param list      List of tasks
     */
    public static void markTasksAsDone(String userInput, List<Task> list) {
        int[] tasksToMarkDone = extractInt(userInput);

        // Error handling: if user inputs no numbers or task number 0
        if (tasksToMarkDone[0] == 0 || tasksToMarkDone[0] == -1) {
            System.out.println(horizontalLine +
                    "Please provide me with a valid task to mark as done/not done!\n" +
                    "Usage example:\n" +
                    "-> Done 1, 2, 3\n" +
                    "-> Done 1 2 3\n" +
                    horizontalLine);
            return;
        }

        System.out.println("Nice! Let me mark your given tasks as done:");

        for (int taskNumber : tasksToMarkDone) {
            if (taskNumber == 0) {
                continue;
            }

            if (taskNumber > list.size()) {
                System.out.println("Oops! You've given be an invalid task number. Skipping...");
                continue;
            }
            list.get(taskNumber - 1).markAsDone();
            System.out.println("[" + list.get(taskNumber - 1).getStatusIcon() + "] " +
                    list.get(taskNumber - 1).getDescription());
        }
        System.out.println("All done!\n" + horizontalLine);
    }

    /**
     * Extracts integers from a given String input and returns them in an array of Ints.
     * Method will extract integers separated by any character.
     * If there are no integers in the input, return -1 at the first element of the array.
     *
     * @param input String for which integers are to be extracted from.
     * @return An array of the integers extracted, returns -1 at the first element.
     */
    public static int[] extractInt(String input) {
        // Replacing every non-digit number with a space " "
        input = input.replaceAll("[^\\d]", " ");
        input = input.trim();

        // Replace consecutive white spaces with a single space
        String[] arrayOfStringInts = input.split(" +");
        int[] extractedInts = new int[arrayOfStringInts.length];

        // If there are no numbers, mark the first element of array as -1 and return
        if (arrayOfStringInts[0].equals("")) {
            extractedInts[0] = -1;
            return extractedInts;
        } else {
            for (int i = 0; i < arrayOfStringInts.length; i++) {
                extractedInts[i] = Integer.parseInt(arrayOfStringInts[i]);
            }
        }

        return extractedInts;
    }
}
