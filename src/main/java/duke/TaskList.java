package duke;

import java.util.stream.Collectors;

import java.util.ArrayList;
import java.util.List;

import duke.task.*;

/**
 * Classifies different actions on the list into various methods.
 */
public class TaskList {

    private static final ArrayList<Task> listInput = new ArrayList<>();
    private static int taskNumber = 0;

    /**
     * Prints the list of the tasks.
     */
    public static void showTask() {
        UI.printBreaker();
        if (taskNumber == 0) {
            System.out.println("The list is empty! Add something to it:)");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskNumber; i++) {
                System.out.println((i + 1) + "." + listInput.get(i).toString());
            }
        }
    }

    /**
     * Marks a task as done.
     *
     * @param inputIndex The index of the task done.
     * @param printMessage The flag of whether or not to print the summary message after the action.
     */
    public static void doneTask(int inputIndex, boolean printMessage) {
        try {
            listInput.get(inputIndex - 1).markAsDone();
            if (printMessage) {
                UI.printDoneTask(listInput.get(inputIndex -1).toString());
            }
            Storage.saveToFile(listInput, taskNumber);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The index of the task that you entered does not exist:(");
        }
    }

    /**
     * Search for a task in the list.
     *
     * @param arrayInput The string array input of the task to be searched for.
     */
    public static void searchTask(String[] arrayInput) {
        List<Task> taskSearch =listInput.stream().filter(t -> t.getTaskDescription().contains(arrayInput[1])).collect(Collectors.toList());
        UI.printSearchTask(taskSearch);
    }

    /**
     * Records different types of tasks.
     *
     * @param commandInput Command from the user showing which type of task to be recorded.
     * @param lineInput The line input from the user.
     * @param printMessage The flag of whether or not to print the summary message after the action.
     */
    public static void recordTask(String commandInput, String lineInput, boolean printMessage) {
        try {
            switch (commandInput) {
            case "event":
                String eventName = lineInput.substring(6, lineInput.indexOf("/"));
                String eventTime = lineInput.substring(lineInput.indexOf("/") + 3);
                if (lineInput.length() < 9) {
                    throw new DukeExceptions ("The description of the event is too short! Please enter again.");
                }
                listInput.add(taskNumber, new Event(eventName, eventTime));
                if (printMessage) {
                    UI.printRecordTask(taskNumber, new Event(eventName, eventTime));
                }
                break;
            case "deadline":
                String deadlineName = lineInput.substring(9, lineInput.indexOf("/"));
                String deadlineTime = lineInput.substring(lineInput.indexOf("/") + 3);
                if (lineInput.length() < 12) {
                    throw new DukeExceptions ("The description of the deadline is too short! Please enter again.");
                }
                listInput.add(taskNumber, new Deadline(deadlineName, deadlineTime));
                if (printMessage) {
                    UI.printRecordTask(taskNumber, new Deadline(deadlineName, deadlineTime));
                }
                break;
            case "todo":
                listInput.add(taskNumber, new ToDo(lineInput.substring(5)));
                if (printMessage) {
                    UI.printRecordTask(taskNumber, new ToDo(lineInput.substring(5)));
                }
                break;
            }
            taskNumber++;
            Storage.saveToFile(listInput, taskNumber);
        } catch (DukeExceptions e) {
            System.out.println("OOPS!!! Sorry, but I do not understand:(");
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param inputIndex The index of the task deleted.
     */
    public static void deleteTask(String inputIndex) {
        try {
            UI.printDeleteTask(taskNumber, listInput.get(Integer.parseInt(inputIndex) - 1).toString());
            listInput.remove(Integer.parseInt(inputIndex) - 1);
            taskNumber--;
            Storage.saveToFile(listInput, taskNumber);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The index of the task that you entered does not exist:(");
        }
    }
}