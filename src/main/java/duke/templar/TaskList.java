package duke.templar;

import duke.exception.NoSuchTaskException;

import java.util.ArrayList;

public class TaskList {

    public static Task[] foundTasks;
    public TaskList() {

    }

    public static void addTask(Task newTask, ArrayList<Task> tasks) {
        tasks.add(newTask);
    }

    /*
     * method returns which task (in tasks array) has been completed and also prints the result
     *
     * @params taskNumber the input string
     * @params tasks the array of tasks
     *
     * @return the task (in tasks array) that has been marked done
     */
    public static int doneTask(int taskNumber, ArrayList<Task> tasks) {
        tasks.get(taskNumber - 1).setDone(true); //mark task as done
        Ui.printTaskDone(taskNumber, tasks);
        return taskNumber - 1;

    }


    public static void deleteTask(int taskNumber, ArrayList<Task> tasks) {
        System.out.println("TARGET REMOVED: " + taskNumber + ". " + tasks.get(taskNumber - 1));
        tasks.remove(taskNumber - 1);
    }

    public static void findTask(String s, ArrayList<Task> tasks) throws NoSuchTaskException {
        String line;
        int j = 0;
        try {
            Ui.printFoundTasks();
            for (int i = 0; i < tasks.size(); i++) {
                line = tasks.get(i).toString();
                if (line.contains(s)) {
                    System.out.println("-" + tasks.get(i).toString());
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
