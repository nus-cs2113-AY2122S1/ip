package processors;

import tasks.Task;
import java.util.ArrayList;

public class TaskList {
    private static final Integer KEYWORD_LENGTH = 2;
    private static final Integer ARRAY_INDEX_FINDER = 1;
    private static final Integer ZERO = 0;

    public Ui ui = new Ui();
    public ArrayList<Task> taskList = new ArrayList<>();

    public void add(Task task) {
        taskList.add(task);
    }

    public void delete(int index) {
        taskList.remove(index);
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int size() {
        return taskList.size();
    }
    /* --- Function --- */

    public boolean tryParse(String text) {
        try {
            Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    public int getLastIndex() {
        int index = taskList.size() - ARRAY_INDEX_FINDER;
        return Math.max(index, ZERO);
    }

    public boolean isDateExist(int length, int index) {
        return length == index + KEYWORD_LENGTH + ARRAY_INDEX_FINDER;
    }

    public boolean isIndexExist(int index) {
        return taskList.isEmpty() || getLastIndex() < index || index < ZERO;
    }
}