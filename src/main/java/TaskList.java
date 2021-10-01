import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }


    public void addTask(Task t) {
        taskList.add(t);
    }

    /**
     * Marks the task that is specified by user, from the list with first index starting from 1, as done.
     *
     * @param doneIndex Index of the task that is to be marked as done.
     * @throws IllegalDoneException If doneIndex >= taskCount, the task that user wants to mark as
     * done does not exist in the list
     */
    public Task markDone(int doneIndex) throws IllegalDoneException {
        if(doneIndex >= taskList.size() || doneIndex < 0) {
            throw new IllegalDoneException();
        } else {
            taskList.get(doneIndex).setDone();
            return taskList.get(doneIndex);
        }
    }


    /**
     * Deletes the task specified by user from the taskList.
     *
     * @param delIndex Index of task to be deleted.
     * @return Task that has been recently delted from the list.
     * @throws IllegalDoneException Exception thrown when user inputs an incorrect index of the number.
     */
    public Task deleteTask(int delIndex) throws IllegalDoneException {
        if (delIndex >= taskList.size() || delIndex < 0) {
            throw new IllegalDoneException();
        } else {
            Task delTask = taskList.get(delIndex);
            taskList.remove(delIndex);
            return delTask;
        }
    }

    /**
     * Searches through the entire Task List to find matching searches. Stores the matches into an array to return
     * to user.
     *
     * @param task Description or date of task that user is trying to find.
     * @return List of all the matches.
     * @throws TaskNotFoundException Exception thrown when there is no matching tasks.
     */
    public TaskList findTasks(String task) throws TaskNotFoundException {
        int pos = 0;
        String currentTask;
        ArrayList<Task> foundTaskList = new ArrayList<>();

        while (pos < taskList.size()) {
            currentTask = taskList.get(pos).toString();
            if (currentTask.contains(task)) {
                foundTaskList.add(taskList.get(pos));
            }
            pos += 1;
        }
        if (foundTaskList.size() == 0) {
            throw new TaskNotFoundException();
        } else {
            return new TaskList(foundTaskList);
        }
    }

}
