package duke.task;

import java.util.ArrayList;

public class TaskDoneList {
    public static ArrayList<List> doneList;

    public TaskDoneList() {
        doneList = new ArrayList<>();
    }

    /**
     * Get the finished task in the doneList corresponding to the index
     *
     * @param taskId The index of the task that need to be returned
     * @return The task in the doneList corresponding to the index
     */
    public List getDoneTask(int taskId) {
        return doneList.get(taskId);
    }

    /**
     * Add the finished task into doneList
     *
     * @param task The index of the task that need to be added
     * @return True or false which indicates whether add the task successfully
     */
    public boolean addDoneTask(List task) {
        return doneList.add(task);
    }

    /**
     * Get size of doneList
     *
     * @return The size of doneList
     */
    public int doneListSize() {
        return doneList.size();
    }

    @Override
    public String toString() {
        return String.valueOf(doneList);
    }
}
