package processors;

import tasks.Task;
import java.util.ArrayList;

public class TaskList {
    private static final Integer KEYWORD_LENGTH = 2;
    private static final Integer ARRAY_INDEX_FINDER = 1;
    private static final Integer ZERO = 0;

    public Ui ui = new Ui();
    public ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Function adds a Task into the list
     * @param task Task to be added into the list
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Function removes a Task from the list base on the input index specified
     * @param index of the Task that is to be removed
     */
    public void delete(int index) {
        taskList.remove(index);
    }

    /**
     * Function returns the Task from the list based on the input index specified
     * @param index of the Task that is to be returned
     * @return the task from the list specified by the input index
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Function returns the integer value of the size of the list
     * @return the size of the list
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Function attempts to parse the text into an integer value and
     * returns false if the attempt is successful. This is to ensure that the input done command
     * has a number that could be parsed
     * @param text input text to be parsed
     * @return true if the attempt is unsuccessful and false if the attempt was successful
     */
    public boolean tryParse(String text) {
        try {
            Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    /**
     * Function returns the index of the last Task in the taskList. This is to
     * access the Task most recently added into the taskList
     * @return the maximum between zero and the index of the last Task in the taskList
     */
    public int getLastIndex() {
        int index = taskList.size() - ARRAY_INDEX_FINDER;
        return Math.max(index, ZERO);
    }

    /**
     * Functions checks whether the date of deadline or event exist and returns true if it does
     * and false if it does not. This is to ensure that the input Event or Deadline from the user
     * has a specified date
     * @param length the integer value of the length of the date
     * @param index the index of the first character where the date starts
     * @return true if date exist, otherwise it returns false
     */
    public boolean isDateExist(int length, int index) {
        return length == index + KEYWORD_LENGTH + ARRAY_INDEX_FINDER;
    }

    /**
     * Function checks whether the index of the list exist within the taskList
     * This is to ensure that the user is not accessing the array that is out of bounds
     * @param index the integer value of the index
     * @return true if the index is out of bounds, otherwise returns false
     */
    public boolean isIndexExist(int index) {
        return taskList.isEmpty() || getLastIndex() < index || index < ZERO;
    }
}