package duke;

import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a list of tasks
 */
public class TaskList {
    private static Ui ui = new Ui();
    private Storage storage = new Storage("duke.txt");

    private ArrayList<Task> tasks;

    /**
     * Constructor for {@code TaskList} class. Creates a new {@code} TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Calculates size of TaskList
     *
     * @return Number of tasks in TaskList
     */
    public int getSize(){
        return tasks.size();
    }


    /**
     * Returns a particular task based on task number specified by the user
     *
     * @param taskNumber The index of the task to be retrieved
     * @return Task corresponding to the taskNumber in the list
     */
    public Task getTask(int taskNumber){
        return tasks.get(taskNumber);
    }

    /**
     * Adds a task a task list
     *
     * @param t Task to be added to the list
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Deletes a task from a task list
     *
     * @param t Task to be deleted from the list
     */
    public void removeTask (Task t){
        tasks.remove(t);
    }

    /**
     * Prints details of a particular task based on task number specified by the user
     * Details include type of task, status (done/ not done) and description
     *
     * @param taskNumber Index of task to be printed
     */
    public void printTask(int taskNumber){
        String type = tasks.get(taskNumber).type;
        String icon = tasks.get(taskNumber).getStatusIcon();
        System.out.println("[" + type + "]" + " [" + icon + "] "
                + tasks.get(taskNumber).description);

    }

    /**
     * Prints entire task list
     */
    public void printTaskList() {

        ui.printHorizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print((i + 1) + ".");
            printTask(i);
        }
        ui.printHorizontalLine();
    }

    /**
     * Changes status of a particular task to done and prints the task out
     *
     * @param taskNumber Index of task to be set as done
     * @param tasks Entire task list
     * @throws IOException Errors relating to writing to external storage file
     */
    public void setDoneTask(int taskNumber, TaskList tasks) throws IOException {
        tasks.getTask(taskNumber).setDone(true);
        storage.writeToFile(tasks);

        ui.printHorizontalLine();
        System.out.println("Nice, I've marked this task as done:");
        String type = tasks.getTask(taskNumber).type;
        String icon = tasks.getTask(taskNumber).getStatusIcon();
        System.out.println("[" + type + "]" + " [" + icon + "] " + tasks.getTask(taskNumber).description);
        ui.printHorizontalLine();
    }

    /**
     * Searches entire task list to find all tasks that contains key, then prints those tasks out
     *
     * @param tasks Entire task list
     * @param key String to search for
     */
    public void searchList(TaskList tasks, String key){
        boolean keyExists = false;
        ui.printHorizontalLine();
        System.out.println("Here are the matching tasks in your list");
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i).description.contains(key)){
                keyExists = true;
                printTask(i);
            }
        }

        if (!keyExists){
            System.out.println("Sorry, no matching tasks exist");
        }

        ui.printHorizontalLine();
    }












}

