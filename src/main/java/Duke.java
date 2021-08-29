import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    private static final String LOGO = " ______    _   __   __            \n" +
                                        "|_   _ \\  (_) [  | [  |           \n" +
                                        "  | |_) | __   | |  | |   _   __  \n" +
                                        "  |  __'.[  |  | |  | |  [ \\ [  ] \n" +
                                        " _| |__) || |  | |  | |   \\ '/ /  \n" +
                                        "|_______/[___][___][___][\\_:  /   \n" +
                                        "                         \\__.'    \n";
    private static final String GREETING_MESSAGE = HORIZONTAL_LINE +
            " Hello! I'm Billy\n" +
            " What can I do for you?\n" +
            HORIZONTAL_LINE;
    private static final String GOODBYE_MESSAGE = HORIZONTAL_LINE +
            " Bye. Hope to see you again soon!\n" +
            HORIZONTAL_LINE;

    public static void main(String[] args) {
        // Initialize variables for program startup
        System.out.println("Hello from\n" + LOGO + GREETING_MESSAGE);
        boolean isProgramRunning = true;
        String userInput;
        List<Task> list = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        while (isProgramRunning) {
            userInput = in.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(GOODBYE_MESSAGE);
                isProgramRunning = false;
            } else if (userInput.equalsIgnoreCase("list")) {
                printList(list);
            } else if (userInput.toLowerCase().startsWith("done")) {
                markTasksAsDone(userInput, list);
            } else if (userInput.toLowerCase().startsWith("todo")) {
                String taskToAdd = removeFirstWord(userInput);
                addTask(TaskType.TODO, taskToAdd, list);
            } else if (userInput.toLowerCase().startsWith("deadline")) {
                String taskToAdd = removeFirstWord(userInput);
                addTask(TaskType.DEADLINE, taskToAdd, list);
            } else if (userInput.toLowerCase().startsWith("event")) {
                String taskToAdd = removeFirstWord(userInput);
                addTask(TaskType.EVENT, taskToAdd, list);
            }
        }

    }

    /**
     * Adds a task to an array list of tasks, printing out the newly added to task to the terminal
     *
     * @param type The type of task (using the TaskType enumerator)
     * @param description Description of the task to be added
     * @param taskList Array list of tasks
     */
    private static void addTask(TaskType type, String description, List<Task> taskList) {
        switch(type) {
        case TODO:
            taskList.add(new Todo(description));
            break;
        case DEADLINE:
            if (description.contains("/by")) {
                String[] separated = splitDescriptionFromTiming(TaskType.DEADLINE, description);
                taskList.add(new Deadline(separated[0], separated[1]));
            } else {
                System.out.println("You need to specify a deadline! TIP: Use \"/by\" to do so!\n" +
                        HORIZONTAL_LINE);
                return;
            }
            break;
        case EVENT:
            if(description.contains("/at")) {
                String[] separated = splitDescriptionFromTiming(TaskType.EVENT, description);
                taskList.add(new Event(separated[0], separated[1]));
            } else {
                System.out.println("You need to specify an event! TIP: Use \"/at\" to do so!\n" +
                        HORIZONTAL_LINE);
                return;
            }
            break;
        default:
            taskList.add(new Task(description));
        }

        Task newlyAddedTask = taskList.get(taskList.size() - 1);
        System.out.println(HORIZONTAL_LINE + "Got it! I've added this task:\n" +
                newlyAddedTask + "\n" + HORIZONTAL_LINE);
    }

    /**
     * Split the description of a Task from its timing (e.g. deadline) if it has one
     *
     * @param type The type of task
     * @param description Full string input of the task and its timing
     * @return Returns a string array with index 0 containing the task description and index 1 containing the timing
     */
    public static String[] splitDescriptionFromTiming(TaskType type, String description) {
        String[] separated;
        switch (type) {
        case DEADLINE:
            separated = description.split("/by +");
            break;
        case EVENT:
            separated = description.split("/at +");
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + type.toString());
        }
        return separated;
    }

    public static void printList(List<Task> list) {
        Task task;
        System.out.println(HORIZONTAL_LINE + "Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            task = list.get(i);
            System.out.println(i + 1 + "." + task);
        }
        System.out.println(HORIZONTAL_LINE);
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
            System.out.println(HORIZONTAL_LINE +
                    "Please provide me with a valid task to mark as done/not done!\n" +
                    "Some usage examples:\n" +
                    "-> Done 1, 2, 3\n" +
                    "-> Done 1 2 3\n" +
                    HORIZONTAL_LINE);
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
        System.out.println("All done!\n" + HORIZONTAL_LINE);
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

        // Split the integers (if any) using whitespace(s) as the delimiter
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

    /**
     * Removes the first word of the input string and returns the remaining input
     * @param input
     * @return The remaining String that is left with the first word removed
     */
    public static String removeFirstWord(String input) {
        String[] splitArray = input.split(" +", 2);
        return splitArray[1];
    }

    public static void addTask (TaskType type, String description) {

    }
}
