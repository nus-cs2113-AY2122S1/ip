package duke.tasks;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Adds task to TaskList.
     *
     * @param inputTask Task to be added.
     */
    public void addTask(Task inputTask) {
        tasks.add(inputTask);
    }

    /**
     * Gets Task from TaskList
     *
     * @param index item on the list to be obtained from TaskList
     * @return Task obtained from TaskList
     */
    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Marks a Task as done
     *
     * @param taskIndex item on the list to be obtained from TaskList
     * @return Task that is marked as done
     */
    public Task updateTask(int taskIndex) {
        tasks.get(taskIndex - 1).setDone();
        return tasks.get(taskIndex - 1);
    }

    /**
     * Deletes a task from TasKlist
     *
     * @param index item on the list to be deleted
     * @return Task that is deleted
     */
    public Task deleteTask(int index) {
        Task deletedTask = tasks.remove(index - 1);
        return deletedTask;
    }

    /**
     * Lists the full TaskList
     *
     * @param taskList which TaskList to be listed
     * @return listMessage a formatted list of the TaskList
     */
    public String listTaskList(TaskList taskList) {
        String listMessage = "Here is the full list!";
        for (int i = 1; i <= taskList.getSize(); i++) {
            listMessage += "\n";
            listMessage += String.valueOf(i) + ".";
            listMessage += taskList.getTask(i);
        }
        return listMessage;
    }

    /**
     * Returns an ArrayList of Task that fits the date criteria set by user
     *
     * @param keyword date of Task to be found in ISO format
     * @return tasksDateList a filtered list containing tasks that fit date criteria
     */
    public ArrayList<Task> findByDate(LocalDate keyword) {
        ArrayList<Task> tasksDateList = new ArrayList<Task>();
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask instanceof Todo) {
                continue;
            }
            String taskDateInString = currTask.getDate().toString();
            if (taskDateInString.equals(keyword.toString())) {
                tasksDateList.add(currTask);
            }
        }
        return tasksDateList;
    }

    /**
     * Returns a string of the filtered list that matches a certain search criteria
     * provided by the user
     *
     * @param taskList a filtered list
     * @return listQueryMessage the formmatted filtered list
     */
    public static String listQueryTaskList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            return "Hmm there does not seem to be any tasks that matches your search request";
        }
        String listQueryMessage = "Here is what we found from the list:";
        for (int i = 1; i <= taskList.size(); i++) {
            listQueryMessage += "\n";
            listQueryMessage += String.valueOf(i) + ".";
            listQueryMessage += taskList.get(i - 1);
        }
        return listQueryMessage;
    }

    /**
     * Returns size of TaskList
     *
     * @return size of TaskList
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns an ArrayList of Task that fits the task description criteria set by user
     *
     * @param key description criteria set by user
     * @return tasksFound a filtered list containing tasks that fit task description criteria
     */
    public ArrayList<Task> findTask(String key) {
        ArrayList<Task> tasksFound = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            String TaskDescription = currTask.getDescription();
            if (TaskDescription.contains(key)) {
                tasksFound.add(currTask);
            }
        }
        return tasksFound;
    }

}
