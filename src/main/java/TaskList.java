import java.util.ArrayList;

public class TaskList {

    private static int taskListCount = 0;
    private static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * This method adds a task to the taskList and increments the count of tasks in taskList by one.
     */
    public void addTaskList(Task listItem) {
        taskList.add(listItem);
        taskListCount += 1;
    }


    /**
     * This method prints out the current taskList
     */
    public void printTaskList() {
        Ui.printLineSeparator();
        for (int i = 1; i < taskListCount + 1; i++) {
            System.out.print("    " + i + ".");
            taskList.get(i - 1).printTask();
        }
        Ui.printLineSeparator();
    }

    /**
     * This method marks a task as done based on its corresponding task number.
     *
     * @param taskNumber task number corresponding to the task in the list
     * @throws NullPointerException if the taskNumber is not in range of the list
     */
    public void markIsDone(int taskNumber) {
        boolean taskInRange = (taskNumber <= 0 || taskNumber > taskListCount);
        if (taskInRange == true) {
            throw new NullPointerException();
        } else {
            taskList.get(taskNumber - 1).setDone();
            Ui.printLineSeparator();
            Ui.printMarkIsDone();
            taskList.get(taskNumber - 1).printTask();
            Ui.printLineSeparator();
        }
    }

    /**
     * This method deletes a task off the list based on its corresponding task number and decrements
     * the count of tasks in taskList by one.
     *
     * @param taskNumber task number corresponding to the task in the list
     * @throws NullPointerException if the taskNumber is not in range of the list
     */
    public void deleteTask(int taskNumber) {
        boolean taskNotInRange = taskNumber <= 0 || taskNumber > taskListCount;
        if (taskNotInRange) {
            throw new NullPointerException();
        } else {
            Ui.printLineSeparator();
            Ui.printDeleteTask();
            taskList.get(taskNumber - 1).printTask();
            taskList.remove(taskNumber - 1);
            taskListCount -= 1;
            System.out.println("    Now you have " + taskListCount + " tasks in the list.");
            Ui.printLineSeparator();
        }
    }

    /**
     * This method finds all tasks with matching keyword entered and prints them all out.
     *
     * @param keyword keyword to search for in the task list
     * @throws FindEmptyException if keyword consists entirely of whitespaces or is empty
     */
    public void findTask(String keyword) throws FindEmptyException {
        String currentDescription;
        if (keyword.isEmpty()) {
            throw new FindEmptyException();
        }
        Ui.printFindTasks();
        Ui.printLineSeparator();
        for (int i = 1; i < taskListCount + 1; i++) {
            currentDescription = taskList.get(i - 1).getDescription();
            if (currentDescription.contains(keyword)) {
                System.out.print("    " + i + ".");
                taskList.get(i - 1).printTask();
            }
        }
        Ui.printLineSeparator();
    }

    /**
     * This method returns the current number of tasks in taskList
     *
     * @returns taskListCount the current number of tasks in taskList
     */
    public int getTaskListCount() {
        return taskListCount;
    }

    /**
     * This method returns a string formatted specifically to be written for the text file based on the task index in taskList.
     *
     * @param index the current task number in taskList
     * @return a string with all the particular task's information
     */
    public String textString(int index) {
        return taskList.get(index).getTextString();
    }

}
