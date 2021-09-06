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
    private static final String HELP_MESSAGE = "Here's some tips on how to use me!\n\n" +
            "todo [input]\n" +
            "\t - Add a todo task to the list\n\n" +
            "deadline [input] /by [input]\n" +
            "\t - Add a deadline task by the given deadline\n\n" +
            "event [input] /at [input]\n" +
            "\t - Add an event task at the given time\n\n" +
            "list\n" +
            "\t - List out all the current tasks\n\n" +
            "done [task_numbers]\n" +
            "\t - Marks the given tasks as done\n\n" +
            "bye\n" +
            "\t - Terminates me :(\n";
    public static final String MARK_TASKS_USAGE_MESSAGE = HORIZONTAL_LINE +
            "Please provide me with a valid task to mark as done/not done!\n" +
            "Some usage examples:\n" +
            "-> Done 1, 2, 3\n" +
            "-> Done 1 2 3\n" +
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
            String userInputLowerCase = userInput.toLowerCase();

            try {
                if (userInput.equalsIgnoreCase("bye")) {
                    System.out.println(GOODBYE_MESSAGE);
                    isProgramRunning = false;
                } else if (userInput.equalsIgnoreCase("list")) {
                    printList(list);
                } else if (userInputLowerCase.startsWith("done")) {
                    markTasksAsDone(userInput, list);
                } else if (userInputLowerCase.startsWith("todo")) {
                    String description = extractDescription(userInput);
                    addTodo(description, list);
                } else if (userInputLowerCase.startsWith("deadline")) {
                    String description = extractDescription(userInput);
                    addDeadline(description, list);
                } else if (userInputLowerCase.startsWith("event")) {
                    String description = extractDescription(userInput);
                    addEvent(description, list);
                } else {
                    System.out.print(HORIZONTAL_LINE + HELP_MESSAGE + HORIZONTAL_LINE);
                }
            } catch (DukeException | IndexOutOfBoundsException e) {
                System.out.print(e.getMessage());
            }
        }
    }

    private static void addTodo(String description, List<Task> taskList) throws DukeException {
        taskList.add(new Todo(description));
        Task addedTodo = taskList.get(taskList.size() - 1);
        System.out.println(HORIZONTAL_LINE + "Got it! I've added this task:\n" +
                addedTodo + "\n" + HORIZONTAL_LINE);
    }

    private static void addEvent(String description, List<Task> taskList) {
        if (description.contains("/at")) {
            String[] separated = splitDescriptionFromTiming(TaskType.EVENT, description);
            taskList.add(new Event(separated[0], separated[1]));
        } else {
            System.out.println("You need to specify an event! TIP: Use \"/at\" to do so!\n" +
                    HORIZONTAL_LINE);
        }
        Task addedEvent = taskList.get(taskList.size() - 1);
        System.out.println(HORIZONTAL_LINE + "Got it! I've added this task:\n" +
                addedEvent + "\n" + HORIZONTAL_LINE);
    }

    private static void addDeadline(String description, List<Task> taskList) {
        if (description.contains("/by")) {
            String[] separated = splitDescriptionFromTiming(TaskType.DEADLINE, description);
            taskList.add(new Deadline(separated[0], separated[1]));
        } else {
            System.out.println("You need to specify a deadline! TIP: Use \"/by\" to do so!\n" +
                    HORIZONTAL_LINE);
        }
        Task addedDeadline = taskList.get(taskList.size() - 1);
        System.out.println(HORIZONTAL_LINE + "Got it! I've added this task:\n" +
                 addedDeadline + "\n" + HORIZONTAL_LINE);
    }

    /**
     * Split the description of a Task from its timing (e.g. deadline) if it has one
     *
     * @param type        The type of task
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
            throw new IllegalStateException("Unexpected value: " + type);
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
    public static void markTasksAsDone(String userInput, List<Task> list) throws DukeException {
        int[] tasksToMarkDone = extractInt(userInput);

        System.out.println(HORIZONTAL_LINE + "Nice! Let me see what I have to mark as done:");
        for (int taskNumber : tasksToMarkDone) {
            // tasksToMarkDone may contain '0's from current implementation of extractInt method
            if (taskNumber == 0) {
                continue;
            }
            try {
                // TODO: Check whether the given task is already done and throw an exception if it is already done
                Task currTask = list.get(taskNumber - 1);
                currTask.markAsDone();
                System.out.println("[X] " + currTask.getDescription());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Dude you've given be an invalid task number... Skipping...");
            }
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
    public static int[] extractInt(String input) throws DukeException {
        // Replacing every non-digit number with a space " "
        input = input.replaceAll("[^\\d]", " ");
        input = input.trim();

        // Split the integers (if any) using whitespace(s) as the delimiter
        String[] arrayOfStringInts = input.split(" +");
        int[] extractedInts = new int[arrayOfStringInts.length];

        // TODO: Tweak implementation such that all extra '0's at the end are not included in the final return
        // If there are no numbers, throw an exception and alert the user
        if (arrayOfStringInts[0].equals("")) {
            throw new DukeException("Monster... You have tricked me and given me NO VALID TASKS!");
        } else {
            for (int i = 0; i < arrayOfStringInts.length; i++) {
                extractedInts[i] = Integer.parseInt(arrayOfStringInts[i]);
            }
        }

        return extractedInts;
    }

    public static String extractDescription(String input) throws DukeException {
        String[] splitArray = input.split(" +", 2);
        if (splitArray.length == 1) {
            throw new DukeException(HORIZONTAL_LINE + "Give me a TASK DESCRIPTION too please???\n" + HORIZONTAL_LINE);
        }
        return splitArray[1];
    }
}
