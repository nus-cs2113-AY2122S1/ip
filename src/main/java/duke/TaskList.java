package duke;

import duke.exception.DukeException;
import duke.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    static void addTodo(String description, List<Task> taskList) {
        Todo newTodo = new Todo(description, TaskType.TODO);
        taskList.add(newTodo);
        Ui.printTaskAddedMessage(newTodo);
    }

    static void addEvent(String input, List<Task> taskList) throws DukeException {
        String[] separated = Parser.splitDescriptionFromTiming(TaskType.EVENT, input);
        if (separated.length == 1) {
            throw new DukeException("Give me a timing for the event too man come on...");
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

    static void addDeadline(String input, List<Task> taskList) throws DukeException {
        String[] separated = Parser.splitDescriptionFromTiming(TaskType.DEADLINE, input);
        if (separated.length == 1) {
            throw new DukeException("Tell me more about the deadline too man come on...");
        }
        String description = separated[0];
        String timeUnformatted = separated[1];
        try {
            LocalDateTime timeFormatted = Parser.extractDateTime(timeUnformatted);
            Deadline newDeadline = new Deadline(description, TaskType.DEADLINE, timeFormatted);
            taskList.add(newDeadline);
            Ui.printTaskAddedMessage(newDeadline);
        } catch (DateTimeParseException e) {
            System.out.println("DISGUSTING FORMAT YOU INSECT. USE dd/MM/yyyy [HH:mm]");
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

    static void executeCommand(String userInput, ArrayList<Task> list, Command command) {
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
            case UNKNOWN:
                Ui.printHelpMessage();
            default:
                throw new DukeException("Give me a VALID COMMAND!\n" +
                        Ui.getHorizontalLine());
            }
        } catch (DukeException | IndexOutOfBoundsException | DateTimeParseException e) {
            System.out.print(e.getMessage());
        }
    }

    private static void findTask(String input, ArrayList<Task> list) throws DukeException {
        if (list.isEmpty()) {
            throw new DukeException(Ui.getHorizontalLine() +
                    "NO TASKS TO SEARCH THROUGH. DO BETTER.\n" +
                    Ui.getHorizontalLine());
        }

        ArrayList<Task> filteredTasks = filterTasksByStream(input, list);
        Ui.printFoundTasksMessage(filteredTasks);
    }

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
