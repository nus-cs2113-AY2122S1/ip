package duke.tasks;

import duke.Ui;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class TaskManager {
    public static final String BY_DIVIDER = "/by";
    public static final String AT_DIVIDER = "/at";
    public static final String LINE_SEPARATOR = "_____________________________";
    private static int numberOfTasksUndone;
    private static ArrayList<Task> taskList;

    public TaskManager() {
        taskList = new ArrayList<>();
    }

    public static int getNumberOfTasksUndone() {
        return numberOfTasksUndone;
    }

    public static int getNumberOfTasksAdded() {
        return taskList.size();
    }

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Prints the task list
     *
     **/
    public static void printTaskList() {
        System.out.println(LINE_SEPARATOR);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(taskList.get(i).toString());
        }
        Ui.printUndoneTaskMessage(numberOfTasksUndone);
    }


    /**
     * Deletes a task from the list
     *
     * @param args the string the user inputs
     */
    public static void deleteTask(String args) {
        int stringLength = args.length();
        int taskIndex = Integer.parseInt(args.substring(stringLength - 1));

        Ui.printDeletedTaskMessage(taskList, taskIndex - 1);

        // if deleted task was not marked as done, decrease numberOfTasksUndone
        if (!taskList.get(taskIndex - 1).getStatusIcon().equals("X")) {
            numberOfTasksUndone--;
        }
        taskList.remove(taskIndex - 1);

        Ui.printTaskNumberMessage(taskList.size());
    }

    /**
     * Store to do tasks in a list.
     *
     * @param args  the item after the command the user inputs
     **/
    public static void addToDoTaskToList(String args) {

        ToDo t = new ToDo(args);
        taskList.add(t);
        numberOfTasksUndone++;
        Ui.printAddedTaskMessage(args);
        Ui.printTaskNumberMessage(taskList.size());
    }

    /**
     * Store deadline tasks in a list.
     *
     * @param args the item after the command the user inputs
     **/
    public static void addDeadlineTaskToList(String args) {
        String description = args.substring(0, args.indexOf(BY_DIVIDER)).trim();
        String time = args.substring(args.indexOf(BY_DIVIDER) + 4).trim();
        Deadline t = new Deadline(description, time);
        taskList.add(t);
        numberOfTasksUndone++;
        Ui.printAddedTaskMessage(description);
        Ui.printTaskNumberMessage(taskList.size());
    }

    /**
     * Store event tasks in a list.
     *
     * @param args  the item after the command the user inputs
     **/
    public static void addEventTaskToList(String args) {
        String description = args.substring(0, args.indexOf(AT_DIVIDER)).trim();
        String time = args.substring(args.indexOf(AT_DIVIDER) + 4).trim();
        Event t = new Event(description, time);
        taskList.add(t);
        numberOfTasksUndone++;
        Ui.printAddedTaskMessage(description);
        Ui.printTaskNumberMessage(taskList.size());
    }

    /**
     * Mark a task as done and prints done message
     *
     * @param args the string the user inputs
     **/
    public static void markTaskAsDone(String args) {
        int doneTaskNumber = Integer.parseInt(args.substring(4).trim());
        taskList.get(doneTaskNumber - 1).markAsDone();
        numberOfTasksUndone--;

        Ui.printMarkAsDoneMessage(taskList, doneTaskNumber - 1, numberOfTasksUndone);
    }

    /**
     * Checks if a task is done using the task number the user entered
     *
     * @param args the command together with the task number to be checked
     * @return returns true if the task is already marked as done, false otherwise
     */
    public static boolean isTaskDone(String args) {
        int taskNumber = Integer.parseInt(args.substring(4).trim());
        if (taskList.get(taskNumber - 1).getStatusIcon().equals("X")) {
            return true;
        }
        return false;
    }

    /**
     * Finds the tasks in the arraylist that contains the keyword
     *
     * @param keyword the keyword to filter the tasks by
     * @return returns the filtered list of the tasks containing the keyword
     */
    public static ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> filteredList = (ArrayList<Task>) taskList.stream()
                .filter((t) -> t.getDescription().contains(keyword))
                .collect((Collectors.toList()));
        return filteredList;
    }
}
