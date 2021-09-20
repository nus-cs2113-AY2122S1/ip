package duke;

import duke.exception.DukeException;
import duke.task.*;

import java.util.ArrayList;
import java.util.List;

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
        String time = separated[1];
        Event newEvent = new Event(description, TaskType.EVENT, time);
        taskList.add(newEvent);
        Ui.printTaskAddedMessage(newEvent);
    }

    static void addDeadline(String input, List<Task> taskList) throws DukeException {
        String[] separated = Parser.splitDescriptionFromTiming(TaskType.DEADLINE, input);
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
        int[] tasksToDelete = Parser.extractInt(userInput);

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

    static void executeCommand(String userInput, ArrayList<Task> list, Command command) {
        try {
            switch (command) {
            case BYE:
                Ui.printGoodbyeMessage();
                Storage.saveToFile(list);
                break;
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
            case UNKNOWN:
                Ui.printHelpMessage();
            default:
                throw new DukeException("What is this nonsense I don't know what's happening!");
            }
        } catch (DukeException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}
