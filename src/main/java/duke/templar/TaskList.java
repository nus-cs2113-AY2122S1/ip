package duke.templar;

import duke.exception.NoSuchTaskException;

import java.util.ArrayList;



/**
 * Contains the task list as well as operations to handle tasks
 * namely adding, deleting, finding and marking as done.
 */
public class TaskList
{
    public static final String MESSAGE_DIVIDER = "____________________________________________________________";

    public TaskList()
    {

    }

    /**
     * Adds a new task to the task list
     * @param newTask
     * @param tasks
     */
    public static void addTask(Task newTask, ArrayList<Task> tasks) {
        tasks.add(newTask);
    }

    /**
     * method returns which task (in tasks array) has been completed and also prints the result
     * @param taskNumber
     * @param tasks
     * @return completedTaskIndex
     */
    public static int doneTask(int taskNumber, ArrayList<Task> tasks) {
        int completedTaskIndex = taskNumber - 1;
        tasks.get(completedTaskIndex).setDone(true); //mark task as done
        Ui.printTaskDone(taskNumber, tasks);
        return completedTaskIndex;

    }

    /**
     * Deletes specified task
     * @param taskNumber
     * @param tasks
     */
    public static void deleteTask(int taskNumber, ArrayList<Task> tasks) {
        Ui.printDeletedTask(taskNumber,tasks);
        tasks.remove(taskNumber - 1);
    }

    /**
     * Finds specified task
     * @param s
     * @param tasks
     * @throws NoSuchTaskException
     */
    public static void findTask(String s, ArrayList<Task> tasks) throws NoSuchTaskException {
        String line;
        int j = 0;
        try {
            Ui.printFoundTasks();
            for (int i = 0; i < tasks.size(); i++) {
                line = tasks.get(i).toString();
                if (line.contains(s)) {
                    System.out.println("- " + tasks.get(i).toString());
                    j++;
                }
            }

            if (j == 0) {
                throw new NoSuchTaskException();
            }

        }
        catch (NoSuchTaskException e) {
            e.printNoSuchTaskException();
        }
    }

}
