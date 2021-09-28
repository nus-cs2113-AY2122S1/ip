package duke.utilities;

import duke.task.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {

    private final String SPACING = " ";
    private final String MESSAGE_TASK_ADDED = "Added task:\n    ";
    private final String ERROR_WRONG_FIND_FORMAT = "Find format wrong! Please try again";
    private final String PRINT_REMOVE_MESSAGE = "Task removed :\n    ";
    private final String MESSAGE_OUT_OF_RANGE = "No such task found! Try a range of 1 to ";
    private final String PRINT_DONE_MESSAGE_FRONT = "I have marked\n     ";
    private final String PRINT_DONE_MESSAGE_BACK = "\nas done!";


    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the TaskList from user input
     *
     * @param input Input of user
     * @param ui Prints the task that has just been added
     * @param type Type of file to be added
     */
    public void addTask(String input, Ui ui, String type) throws DukeException {
        Task temp = Parser.getTaskType(input, type);
        tasks.add(temp);
        System.out.println(MESSAGE_TASK_ADDED + temp);
        ui.printTaskNumber(tasks);
    }

    /**
     * Adds a task to the TaskList from saved file
     *
     * @param input Input from file
     * @param isDone Checks the task has been completed
     * @param type Type of file to be added
     * @throws DukeException If file format is corrupted/invalid
     */
    public void addTask(String input, boolean isDone, String type) throws DukeException {
        Task temp = Parser.getTaskType(input, type);
        if (isDone) {
            temp.setDone();
        }
        tasks.add(temp);
    }

    /**
     * Deletes a task from TaskList
     *
     * @param input The task index to be deleted
     * @param ui Prints which task is removed
     */
    public void deleteTask(String input, Ui ui) {
        int taskIdx = findTaskNumber(input);
        try {
            Task temp = tasks.get(taskIdx);
            tasks.remove(taskIdx);
            ui.print(PRINT_REMOVE_MESSAGE + temp);
            ui.printTaskNumber(tasks);
        } catch (IndexOutOfBoundsException e) {
            ui.print(MESSAGE_OUT_OF_RANGE + tasks.size());
        }
    }

    /**
     * Sets task in TaskList as done
     *
     * @param input The task index to be set as done
     * @param ui Prints the task set as done
     */
    public void setTaskAsDone(String input, Ui ui) {
        int taskIdx = findTaskNumber(input);
        try {
            Task temp = tasks.get(taskIdx);
            temp.setDone();
            tasks.set(taskIdx, temp);
            System.out.println(PRINT_DONE_MESSAGE_FRONT + temp + PRINT_DONE_MESSAGE_BACK);
        } catch (IndexOutOfBoundsException e) {
            ui.print(MESSAGE_OUT_OF_RANGE + tasks.size());
        } catch (NumberFormatException e) {
            ui.print("Please key in a number instead pls :(");
        }
    }

    private int findTaskNumber(String input) {
        int accountForArray = 1;
        String[] words = input.split(SPACING);
        int taskNumber = Integer.parseInt(words[1]) - accountForArray;
        return taskNumber;
    }

    /**
     * Searches for the tasks that have the same keyword
     *
     * @param input Keyword of the user
     * @param ui Prints the list of matches found in the TaskList
     */
    public void findTasks(String input, Ui ui) {
        String[] words = input.split(" ");

        if (words.length != 2) {
            System.out.println(ERROR_WRONG_FIND_FORMAT);
            return;
        }

        String keyword = words[1].toLowerCase();
        ArrayList<Task> matches = (ArrayList<Task>) tasks.stream()
                .filter(t -> t.getDescription().toLowerCase().contains(keyword))
                .collect(Collectors.toList());

        ui.printMatchingList(matches);
    }
}
