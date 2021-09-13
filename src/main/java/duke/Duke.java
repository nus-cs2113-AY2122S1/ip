package duke;

import duke.exception.DukeException;
import duke.task.*;

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
        System.out.print("Hello from\n" + LOGO + GREETING_MESSAGE);
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
                } else if (userInputLowerCase.startsWith("delete")) {
                    deleteTask(userInputLowerCase, list);
                } else {
                    System.out.print(HORIZONTAL_LINE + HELP_MESSAGE + HORIZONTAL_LINE);
                }
            } catch (DukeException | IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void addTodo(String description, List<Task> taskList) {
        Todo newTodo = new Todo(description);
        taskList.add(newTodo);
        System.out.print(HORIZONTAL_LINE + "Got it! I've added this task:\n" +
                newTodo + "\n" + HORIZONTAL_LINE);
    }

    private static void addEvent(String input, List<Task> taskList) throws DukeException {
        String[] separated = splitDescriptionFromTiming(TaskType.EVENT, input);
        if (separated.length == 1) {
            throw new DukeException("Give me a timing for the event too man come on...");
        }
        String description = separated[0];
        String time = separated[1];
        Event newEvent = new Event(description, time);
        taskList.add(newEvent);

        System.out.print(HORIZONTAL_LINE + "Got it! I've added this task:\n" +
                newEvent + "\n" + HORIZONTAL_LINE);
    }

    private static void addDeadline(String input, List<Task> taskList) throws DukeException {
        String[] separated = splitDescriptionFromTiming(TaskType.DEADLINE, input);
        if (separated.length == 1) {
            throw new DukeException("Tell me more about the deadline too man come on...");
        }
        String description = separated[0];
        String time = separated[1];
        Deadline newDeadline = new Deadline(description, time);
        taskList.add(newDeadline);

        System.out.print(HORIZONTAL_LINE + "Got it! I've added this task:\n" +
                newDeadline + "\n" + HORIZONTAL_LINE);
    }

    /**
     * Deletes given tasks from the provided list of tasks. Accepts multiple tasks in one input and provides feedback
     * if invalid inputs are detected. Can identify task numbers amidst redundant input (e.g. done 1, 2 and 3)
     *
     * @param userInput String of user input containing task numbers to be deleted.
     * @param list      List of tasks
     */
    public static void deleteTask(String userInput, List<Task> list) throws DukeException {
        int[] tasksToDelete = extractInt(userInput);

        System.out.println(HORIZONTAL_LINE + "Awesome! I shall now try to delete the following...");
        for (int taskNumber : tasksToDelete) {
            // tasksToDelete may contain '0's from current implementation of extractInt method
            if (taskNumber == 0) {
                continue;
            }
            try {
                // TODO: Check whether the given task is already done and throw an exception if it is already done
                Task currTask = list.get(taskNumber - 1);
                System.out.println("Deleted: " + currTask);
                list.remove(currTask);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Dude you've given be an invalid task number [" + taskNumber + "] ! Skipping...");
            }
        }
        System.out.print("Boom, insect. The tasks are now DELETED!\n" + HORIZONTAL_LINE);
    }

    /**
     * Split the description of a Task from its timing (e.g. deadline) if it has one
     *
     * @param type        The type of task
     * @param description Full string input of the task and its timing
     * @return Returns a string array with index 0 containing the task description and index 1 containing the timing
     */
    public static String[] splitDescriptionFromTiming(TaskType type, String description) throws DukeException {
        String[] separated;
        switch (type) {
        case DEADLINE:
            if (!description.contains("/by")) {
                throw new DukeException("Am I supposed to guess when your deadline is???\n" +
                        "TIP: Use \"/by\" to do so!");
            }
            separated = description.split("/by +");
            break;
        case EVENT:
            if (!description.contains("/at")) {
                throw new DukeException(HORIZONTAL_LINE +
                        "Am I supposed to guess when your event is happening???\n" +
                        "TIP: Use \"/at\" to do so!");
            }
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
        System.out.print(HORIZONTAL_LINE);
    }

    /**
     * Marks and prints given tasks as done. Accepts multiple tasks in one input and provides feedback
     * if invalid inputs are detected. Can identify task numbers amidst redundant input (e.g. done 1, 2 and 3)
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
        System.out.print("All done!\n" + HORIZONTAL_LINE);
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
            throw new DukeException("Give me a DESCRIPTION too please???");
        }
        return splitArray[1];
    }
}
