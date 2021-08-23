import java.sql.Array;
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
        List<Task> list = new ArrayList<Task>();
        Scanner in = new Scanner(System.in);
        System.out.println("Hello from\n" + logo + greetingMessage);

        while (programIsRunning) {
            userInput = in.nextLine().trim();

            if (userInput.toLowerCase().equals("bye")) {
                System.out.println(goodbyeMessage);
                programIsRunning = false;
            } else if (userInput.toLowerCase().equals("list")) {
                printList(list);
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
     * Extracts integers from a given String input and returns them in an array of Ints.
     * Method will extract integers separated by any character.
     * If there are no integers in the input, return null.
     *
     * @param input String for which integers are to be extracted from.
     * @return An array of the integers extracted, returns null otherwise.
     */
    public static int[] extractInt(String input)
    {
        int[] extractedInts = new int[input.length()];

        // Replacing every non-digit number with a space " "
        input = input.replaceAll("[^\\d]", " ");
        input = input.trim();

        // Replace consecutive white spaces with a single space
        String[] arrayOfStringInts = input.split(" +");

        // If there are no numbers, mark the first element of array as -1 and return
        if (arrayOfStringInts[0] == null) {
            extractedInts[0] = -1;
            return null;
        }

        for (int i = 0; i < arrayOfStringInts.length; i++) {
            extractedInts[i] = Integer.parseInt(arrayOfStringInts[i]);
        }

        return extractedInts;
    }
}
