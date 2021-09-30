package duke.taskmanager;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.Todo;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    /**
     * Adds a to-do task into the list of tasks
     *
     * @param description The description of the to-do task
     * @param taskList The list for which the to-do task will be added into
     */
    static void addTodo(String description, List<Task> taskList) {
        Todo newTodo = new Todo(description, TaskType.TODO);
        taskList.add(newTodo);
        Ui.printTaskAddedMessage(newTodo);
    }

    /**
     * Adds an event into the list of tasks.
     * The format for date/time info is dd/MM/yyyy [HH:mm]
     * Fields in square bracket are optional
     *
     * @param input User input containing the description and date/time info of the event
     * @param taskList The list for which the event will be added into
     * @throws DukeException If no description or date/time is given for the event
     */
    static void addEvent(String input, List<Task> taskList) throws DukeException {
        String[] separated = Parser.splitDescriptionFromTiming(TaskType.EVENT, input);
        if (separated.length == 1) {
            throw new DukeException(Ui.getHorizontalLine() +
                    "Give me a timing for the event too man come on...\n" +
                    Ui.getHorizontalLine());
        }
        String description = separated[0];
        String timeUnformatted = separated[1];

        try {
            LocalDateTime timeFormatted = Parser.extractDateTime(timeUnformatted);
            Deadline newEvent = new Deadline(description, TaskType.EVENT, timeFormatted);
            taskList.add(newEvent);
            Ui.printTaskAddedMessage(newEvent);
        } catch (DateTimeParseException e) {
            System.out.println("DISGUSTING FORMAT YOU INSECT. USE dd/MM/yyyy [HH:mm]");
        }
    }

    /**
     * Adds a deadline into the list of tasks.
     * The format for date/time info is dd/MM/yyyy [HH:mm]
     * Fields in square bracket are optional
     *
     * @param input User input containing the description and date/time info of the deadline
     * @param taskList The list for which the deadline will be added into
     * @throws DukeException If no description or date/time is given for the event
     */
    static void addDeadline(String input, List<Task> taskList) throws DukeException {
        String[] separated = Parser.splitDescriptionFromTiming(TaskType.DEADLINE, input);
        if (separated.length == 1) {
            throw new DukeException(Ui.getHorizontalLine() +
                    "Tell me more about the deadline too man come on...\n" +
                    Ui.getHorizontalLine());
        }
        String description = separated[0];
        String timeUnformatted = separated[1];
        try {
            LocalDateTime timeFormatted = Parser.extractDateTime(timeUnformatted);
            Deadline newDeadline = new Deadline(description, TaskType.DEADLINE, timeFormatted);
            taskList.add(newDeadline);
            Ui.printTaskAddedMessage(newDeadline);
        } catch (DateTimeParseException e) {
            System.out.print("DISGUSTING FORMAT YOU INSECT. USE dd/MM/yyyy [HH:mm]\n" +
                    Ui.getHorizontalLine());
        }
    }

    /**
     * Deletes given tasks from the provided list of tasks. Accepts multiple tasks in one input and provides feedback
     * if invalid inputs are detected. Can identify task numbers amidst redundant input (e.g. done 1, 2 and 3)
     *
     * @param userInput String of user input containing task numbers to be deleted.
     * @param list      List of tasks
     */
    public static void deleteTask(String userInput, List<Task> list) throws DukeException {
        int[] indexesOfTasksToDelete = Parser.extractInt(userInput);
        ArrayList<Task> tasksToDelete = new ArrayList<>();

        Ui.printDeleteAttemptStartMessage();
        for (int taskNumber : indexesOfTasksToDelete) {
            // indexesOfTasksToDelete may contain '0's from current implementation of extractInt method
            if (taskNumber == 0) {
                continue;
            }
            try {
                // TODO: Check whether the given task is already done and throw an exception if it is already done
                Task currTask = list.get(taskNumber - 1);
                tasksToDelete.add(currTask);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Dude you've given me an invalid task number [" + taskNumber + "] ! Skipping...");
            }
        }

        for (Task task : tasksToDelete) {
            Ui.printDeletedTaskMessage(task);
            list.remove(task);
        }
        Ui.printDeleteAttemptEndMessage();
    }

    /**
     * Marks and prints given tasks as done. Accepts multiple tasks in one input and provides feedback
     * if invalid inputs are detected. Can identify task numbers amidst redundant input (e.g. done 1, 2 and 3)
     *
     * @param userInput String of user input containing task numbers to be marked as done.
     * @param list      List of tasks
     */
    public static void markTasksAsDone(String userInput, List<Task> list) throws DukeException {
        int[] tasksToMarkDone = Parser.extractInt(userInput);

        System.out.println(Ui.getHorizontalLine() + "Nice! Let me see what I have to mark as done:");
        for (int taskNumber : tasksToMarkDone) {
            // tasksToMarkDone may contain '0's from current implementation of extractInt method
            if (taskNumber == 0) {
                continue;
            }
            try {
                Task currTask = list.get(taskNumber - 1);
                if (currTask.isDone()) {
                    throw new DukeException("TASK IS ALREADY DONE. STOP TOYING WITH ME...");
                }
                currTask.markAsDone();
                System.out.println("[X] " + currTask.getDescription());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Dude you've given be an invalid task number... Skipping...");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.print("All done!\n" + Ui.getHorizontalLine());
    }

    /**
     * Executes the desired command by the user with the provided ArrayList of tasks
     *
     * @param userInput User input containing information about the task
     * @param list      ArrayList of tasks to perform command on
     * @param command   Desired command to execute
     */
    public static void executeCommand(String userInput, ArrayList<Task> list, Command command) {
        try {
            switch (command) {
            case LIST:
                Ui.printList(list);
                break;
            case DONE:
                markTasksAsDone(userInput, list);
                Storage.saveToFile(list);
                break;
            case ADD_TODO:
                String todoDescription = Parser.extractDescription(userInput);
                addTodo(todoDescription, list);
                Storage.saveToFile(list);
                break;
            case ADD_DEADLINE:
                String deadlineDescription = Parser.extractDescription(userInput);
                addDeadline(deadlineDescription, list);
                Storage.saveToFile(list);
                break;
            case ADD_EVENT:
                String eventDescription = Parser.extractDescription(userInput);
                addEvent(eventDescription, list);
                Storage.saveToFile(list);
                break;
            case DELETE:
                deleteTask(userInput, list);
                Storage.saveToFile(list);
                break;
            case FIND:
                String findDescription = Parser.extractDescription(userInput);
                findTask(findDescription, list);
                break;
            default:
                throw new DukeException("ERROR: Unknown command passed into executeCommand() in TaskList.java\n");
            }
        } catch (DukeException | IndexOutOfBoundsException | DateTimeParseException e) {
            System.out.print(e.getMessage());
        }
    }

    /**
     * Queries the list of tasks for one that contains a substring of the given description from the user input
     * and prints out any tasks that are found that contain it.
     *
     * @param input Description of the task to be queried for
     * @param list List of tasks to be searched through
     * @throws DukeException If the provided list of tasks is empty
     */
    private static void findTask(String input, ArrayList<Task> list) throws DukeException {
        if (list.isEmpty()) {
            throw new DukeException(Ui.getHorizontalLine() +
                    "NO TASKS TO SEARCH THROUGH. DO BETTER.\n" +
                    Ui.getHorizontalLine());
        }
        ArrayList<Task> filteredTasks = filterTasksByStream(input, list);
        Ui.printFoundTasksMessage(filteredTasks);
    }

    /**
     * Filters the provided ArrayList of tasks with the given String query using Streams and returns an
     * ArrayList of the filtered tasks.
     *
     * @param input String query to filter the tasks by
     * @param list  ArrayList of tasks to filter through
     * @return An ArrayList of the filtered tasks
     * @throws DukeException If there are no matches found with the given String query
     */
    private static ArrayList<Task> filterTasksByStream(String input, ArrayList<Task> list) throws DukeException {
        List<Task> filteredTasks = list.stream()
                .filter((t) -> (t).toString().contains(input))
                .collect(Collectors.toList());
        if (filteredTasks.isEmpty()) {
            throw new DukeException(Ui.getHorizontalLine() +
                    "HAHA there are NO MATCHES YOU FOOL. Try again.\n" +
                    Ui.getHorizontalLine());
        }
        return (ArrayList<Task>) filteredTasks;
    }
}
