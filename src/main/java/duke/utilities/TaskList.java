package duke.utilities;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private final String SPACING = " ";
    private final String MESSAGE_TASK_ADDED = "Added task:\n    ";
    private final String ERROR_WRONG_EVENT_FORMAT = "The input was wrong :( "
            + "Please type 'event <description> /at <time of event>'";
    private final String ERROR_WRONG_DEADLINE_FORMAT = "The input was wrong :( "
            + "Please type 'deadline <description> /by <time of event>'";
    private final String ERROR_WRONG_TODO_FORMAT = "The input was wrong :( Please type 'todo <description>'";
    private final String PRINT_REMOVE_MESSAGE = "Task removed :\n    ";
    private final String MESSAGE_OUT_OF_RANGE = "No such task found! Try a range of 1 to ";
    private final String PRINT_DONE_MESSAGE_FRONT = "I have marked\n     ";
    private final String PRINT_DONE_MESSAGE_BACK = "\n as done!";


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
            ui.print(System.lineSeparator() + PRINT_REMOVE_MESSAGE + temp);
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
}
