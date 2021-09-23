package duke.utilities;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private static final String SPACING = " ";
    private static final String MESSAGE_TASK_ADDED = "Added task:\n    ";
    private static final String ERROR_WRONG_EVENT_FORMAT = "The input was wrong :( "
            + "Please type 'event <description> /at <time of event>'";
    private static final String ERROR_WRONG_DEADLINE_FORMAT = "The input was wrong :( "
            + "Please type 'deadline <description> /by <time of event>'";
    private static final String ERROR_WRONG_TODO_FORMAT = "The input was wrong :( Please type 'todo <description>'";
    private static final String PRINT_REMOVE_MESSAGE = "Task removed :\n    ";
    private static final String MESSAGE_OUT_OF_RANGE = "No such task found! Try a range of 1 to ";
    private static final String PRINT_DONE_MESSAGE_FRONT = "I have marked\n     ";
    private static final String PRINT_DONE_MESSAGE_BACK = "\n as done!";


    private ArrayList<Task> tasks = new ArrayList<>();

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(String input, String type) throws DukeException {
        Task temp = Parser.getTaskType(input, type);
        tasks.add(temp);
        System.out.println(MESSAGE_TASK_ADDED + temp);
        Ui.printTaskNumber(tasks);
        Storage.saveToFile(tasks);
    }

    public void addTask(String input, boolean isDone, String type) throws DukeException {
        Task temp = Parser.getTaskType(input, type);
        if (isDone) {
            temp.setDone();
        }
        tasks.add(temp);
    }

    public void deleteTask(String input) {
        int taskIdx = findTaskNumber(input);
        try {
            Task temp = tasks.get(taskIdx);
            tasks.remove(taskIdx);
            System.out.println();
            System.out.println(PRINT_REMOVE_MESSAGE + temp);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(MESSAGE_OUT_OF_RANGE + tasks.size());
        }
        Storage.saveToFile(tasks);
    }

    /**
     * Sets specific task in array as done
     *
     * @param input input of user
     */
    public void setTaskAsDone(String input) {
        int taskIdx = findTaskNumber(input);
        try {
            Task temp = tasks.get(taskIdx);
            temp.setDone();
            tasks.set(taskIdx, temp);
            System.out.println(PRINT_DONE_MESSAGE_FRONT + temp + PRINT_DONE_MESSAGE_BACK);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(MESSAGE_OUT_OF_RANGE + tasks.size());
        } catch (NumberFormatException e) {
            System.out.println("Please key in a number instead pls :(");
        }
        Storage.saveToFile(tasks);
    }

    private int findTaskNumber(String input) {
        int accountForArray = 1;
        String[] words = input.split(SPACING);
        int taskNumber = Integer.parseInt(words[1]) - accountForArray;
        return taskNumber;
    }
}
