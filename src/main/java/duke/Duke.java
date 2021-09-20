package duke;

import duke.exception.DukeException;
import duke.task.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        // Initialize variables for program startup
        Ui.printGreetingMessage();
        String userInput;
        ArrayList<Task> list = new ArrayList<>();
        DukeFileUtils.loadSaveData(list);
        Ui.printList(list);
        Scanner in = new Scanner(System.in);


        while (true) {
            userInput = in.nextLine().trim();
            String userInputLowerCase = userInput.toLowerCase();

            try {
                if (userInput.equalsIgnoreCase("bye")) {
                    Ui.printGoodbyeMessage();
                    DukeFileUtils.saveToFile(list);
                    break;
                } else if (userInput.equalsIgnoreCase("list")) {
                    Ui.printList(list);
                } else if (userInputLowerCase.startsWith("done")) {
                    markTasksAsDone(userInput, list);
                    DukeFileUtils.saveToFile(list);
                } else if (userInputLowerCase.startsWith("todo")) {
                    String description = extractDescription(userInput);
                    addTodo(description, list);
                    DukeFileUtils.saveToFile(list);
                } else if (userInputLowerCase.startsWith("deadline")) {
                    String description = extractDescription(userInput);
                    addDeadline(description, list);
                    DukeFileUtils.saveToFile(list);
                } else if (userInputLowerCase.startsWith("event")) {
                    String description = extractDescription(userInput);
                    addEvent(description, list);
                } else if (userInputLowerCase.startsWith("delete")) {
                    deleteTask(userInputLowerCase, list);
                    DukeFileUtils.saveToFile(list);
                } else {
                    Ui.printHelpMessage();
                }
            } catch (DukeException | IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void addTodo(String description, List<Task> taskList) {
        Todo newTodo = new Todo(description, TaskType.TODO);
        taskList.add(newTodo);
        Ui.printTaskAddedMessage(newTodo);
    }

    private static void addEvent(String input, List<Task> taskList) throws DukeException {
        String[] separated = splitDescriptionFromTiming(TaskType.EVENT, input);
        if (separated.length == 1) {
            throw new DukeException("Give me a timing for the event too man come on...");
        }
        String description = separated[0];
        String time = separated[1];
        Event newEvent = new Event(description, TaskType.EVENT, time);
        taskList.add(newEvent);
        Ui.printTaskAddedMessage(newEvent);
    }

    private static void addDeadline(String input, List<Task> taskList) throws DukeException {
        String[] separated = splitDescriptionFromTiming(TaskType.DEADLINE, input);
        if (separated.length == 1) {
            throw new DukeException("Tell me more about the deadline too man come on...");
        }
        String description = separated[0];
        String time = separated[1];
        Deadline newDeadline = new Deadline(description, TaskType.DEADLINE,time);
        taskList.add(newDeadline);
        Ui.printTaskAddedMessage(newDeadline);
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

        Ui.printDeleteAttemptStartMessage();
        for (int taskNumber : tasksToDelete) {
            // tasksToDelete may contain '0's from current implementation of extractInt method
            if (taskNumber == 0) {
                continue;
            }
            try {
                // TODO: Check whether the given task is already done and throw an exception if it is already done
                Task currTask = list.get(taskNumber - 1);
                Ui.printDeletedTaskMessage(currTask);
                list.remove(currTask);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Dude you've given me an invalid task number [" + taskNumber + "] ! Skipping...");
            }
        }
        Ui.printDeleteAttemptEndMessage();
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
                throw new DukeException(Ui.getHorizontalLine() +
                        "Am I supposed to guess when your deadline is???\n" +
                        "TIP: Use \"/by\" to do so!");
            }
            separated = description.split("/by +");
            break;
        case EVENT:
            if (!description.contains("/at")) {
                throw new DukeException(Ui.getHorizontalLine() +
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

    /**
     * Marks and prints given tasks as done. Accepts multiple tasks in one input and provides feedback
     * if invalid inputs are detected. Can identify task numbers amidst redundant input (e.g. done 1, 2 and 3)
     *
     * @param userInput String of user input containing task numbers to be marked as done.
     * @param list      List of tasks
     */
    public static void markTasksAsDone(String userInput, List<Task> list) throws DukeException {
        int[] tasksToMarkDone = extractInt(userInput);

        System.out.println(Ui.getHorizontalLine() + "Nice! Let me see what I have to mark as done:");
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
        System.out.print("All done!\n" + Ui.getHorizontalLine());
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
        return splitArray[1].trim();
    }
}
