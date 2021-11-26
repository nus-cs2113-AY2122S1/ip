package itachi;

import itachi.exception.ItachiException;
import itachi.task.Task;

import java.util.ArrayList;

/**
 * To perform operations on the list of tasks
 */
public class TaskList {
    public static ArrayList<Task> list = new ArrayList<>();

    /**
     * Adds a task to the list and prints a completion message
     *
     * @param task is the Task object is being added to the list
     */
    public void addTask(Task task) {
        list.add(task);

        Ui.printAddTaskMessage(task);
    }

    /**
     * Marks a task as done with the given index and prints a completion message
     *
     * @param taskIndex is the index of the task needed to be marked as done
     */
    public void doneTask(int taskIndex) throws ItachiException {
        if (taskIndex >= list.size()) {
            throw new ItachiException("Task index does not exist!");
        } else if (taskIndex <= -1) {
            throw new ItachiException("Task index must be larger than 0");
        }
        list.get(taskIndex).markAsDone();

        Ui.printDoneTaskMessage(taskIndex);
    }

    /**
     * Deletes a task with the given index and prints a completion message
     *
     * @param taskIndex is the index of the task needed to be deleted
     * @throws ItachiException if index provided is larger than the list size or is lesser than 1
     */
    public void deleteTask(int taskIndex) throws ItachiException {
        if (taskIndex >= list.size()) {
            throw new ItachiException("Task index does not exist!");
        } else if (taskIndex <= -1) {
            throw new ItachiException("Task index must be larger than 0");
        }

        Ui.printDeleteTaskMessage(taskIndex);

        list.remove(taskIndex);
    }

    /**
     * Prints all the tasks present in the list
     */
    public void listTasks() {
        if (list.isEmpty()) {
            Ui.printEmptyListMessage();
        } else {
            Ui.printTaskList();
        }
    }

    /**
     * Searches and prints the task based on the keyword provided.
     *
     * @param keyword is the word that needs to be searched for
     * @throws ItachiException if there are no tasks in the list containing the keyword
     */
    public void findTask(String keyword) throws ItachiException {
        if (list.isEmpty()) {
            Ui.printEmptyListMessage();
        } else {
            // Array to store the list of tasks found from the original array
            ArrayList<Task> listOfTasksFound = new ArrayList<>();
            Ui.printLineSeparator();
            System.out.println("Here are the matching tasks in your list:");

            for (int i = 0; i < list.size(); i++) {
                // Stores the combined description and date into a lowercase string to check for keyword
                String taskDescription = list.get(i).getDataForFind().toLowerCase();
                Ui.printMatchingTasks(keyword, taskDescription, i, listOfTasksFound);
            }

            if (listOfTasksFound.isEmpty()) {
                throw new ItachiException("No matching tasks found, try another keyword");
            }
            Ui.printLineSeparator();
        }
    }

}
