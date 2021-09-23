package duke.templar;

import java.util.ArrayList;

public class TaskList {

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

}
