package xRoss;

import xRoss.exception.EmptyStringException;
import xRoss.storage.FileManager;
import xRoss.task.Deadline;
import xRoss.task.Event;
import xRoss.task.Task;
import xRoss.task.Todo;

import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents collection of Tasks and methods to edit them in task list.
 */
public class TaskManager implements FileManager {

    /**
     * tasks                ArrayList containing Tasks in task list
     * tasksCount           Number of Tasks in task list
     * completedTasksCount  Number of Tasks in task list that have been marked as done
     */
    private ArrayList<Task> tasks = new ArrayList<>();
    private int tasksCount = 0;
    private int completedTasksCount = 0;

    /**
     * Specified file path to save task list.
     */
    final String filepath = "data/xRoss.txt";

    /**
     * Prints current task list to system output.
     */
    public void printTasks() {
        if (tasksCount == 0) {
            System.out.println("\tYou have no tasks in your task list at the moment.\n");
            return;
        }
        System.out.println("\tThis is your current task list:");

        for (int i = 0; i < tasksCount; i++) {
            System.out.print("\t\t" + (i + 1) + ".");
            tasks.get(i).printTask();
        }
        printTaskCounts();
    }

    /**
     * Adds new Task to task list.
     *
     * @param task  New Task to be added to task list.
     */
    public void addTask(Task task) {
        // add new task to task list
        tasks.add(task);

        // print name of task to system output
        System.out.print("\tGot it! I've added this task for you:\n\t\t");
        tasks.get(tasksCount).printTask();

        // increment total tasksCount
        tasksCount++;
        printTaskCounts();

        // save current task list after change
        saveToFile();
    }

    /**
     * Marks a specified Task in current task list as done.
     *
     * @param inputTaskIndex    Index of Task in current task list to be marked as done.
     */
    public void markAsDone(int inputTaskIndex) {
        int taskIndex = inputTaskIndex - 1;

        // check if taskIndex is out of bounds of current task list
        if (taskIndex < 0 || taskIndex >= tasksCount) {
            System.out.println("\tYou have chosen an invalid task number.\n");
            printTasks();
            return;
        }

        // check if task is already marked as done
        if (tasks.get(taskIndex).isDone()) {
            System.out.println("\tThis task has already been completed and marked as done.\n");
            return;
        }

        // set task as done and increment completedTasksCount
        tasks.get(taskIndex).setDone();
        completedTasksCount++;

        // Print name of task to system output
        System.out.print("\tGood job! I have marked your task as done.\n\t\t");
        tasks.get(taskIndex).printTask();
        System.out.println("\tYou have "
                + (tasksCount - completedTasksCount)
                + " uncompleted task(s) left in your task list\n");

        // save current task list after change
        saveToFile();
    }

    /**
     * Deletes a specified Task in the current task list.
     *
     * @param inputTaskIndex    Index of Task in current task list to be deleted.
     */
    public void deleteTask(int inputTaskIndex){
        int taskIndex = inputTaskIndex - 1;

        // check if taskIndex is out of bounds of current task list
        if (taskIndex < 0 || taskIndex >= tasksCount) {
            System.out.println("\tYou have chosen an invalid task number.\n");
            printTasks();
            return;
        }

         // deduct from completedTasksCount if task to be deleted is completed
        if (tasks.get(taskIndex).isDone()){
            completedTasksCount--;
        }

        // delete task from task list and deduct from tasksCount
        Task deletedTask = tasks.remove(taskIndex);
        tasksCount--;

        // print name of task to system output
        System.out.print("\tI have deleted task "
                + inputTaskIndex
                + " from your task list.\n\t\t");
        deletedTask.printTask();
        printTaskCounts();

        // save current task list after change
        saveToFile();
    }

    /**
     * Prints number of tasks and uncompleted tasks in current task list to system output.
     */
    private void printTaskCounts() {
        System.out.println("\tThere are "
                + tasksCount
                + " task(s) in your task list, of which "
                + (tasksCount - completedTasksCount)
                + " is/are uncompleted.\n");
    }

    /**
     * Reads saved task list from specified text file.
     */
    @Override
    public void readFromFile() {
        try {
            File file = new File(filepath);

            // instantiate scanner to read file contents
            Scanner s = new Scanner(file);

            while (s.hasNext()){
                Task task = convertFileStrToTask(s.nextLine());

                tasks.add(task);
                tasksCount++;
                if (task.isDone()){
                    completedTasksCount++;
                }
            }
        } catch (FileNotFoundException e){ // file does not exist on first boot
            return;
        }

    }

    /**
     * Converts scanned file string to Task instance.
     *
     * @param fileStr  Scanned file string.
     * @return  Converted Task instance from scanned file string.
     */
    private Task convertFileStrToTask(String fileStr) {
        // split string using " | " pattern
        String[] scannedTask = fileStr.split(" \\| ");

        Task task = null;

        try {
            switch (scannedTask[0]){
            case "T":
                task = new Todo(scannedTask[2]);
                break;
            case "D":
                task = new Deadline(scannedTask[2], scannedTask[3]);
                break;
            case "E":
                task = new Event(scannedTask[2], scannedTask[3]);
                break;
            default:
                System.out.println("Error in saved file string...");
            }
        } catch (IndexOutOfBoundsException e){
            System.out.println("Error in task type description in saved file string...");
        } catch (EmptyStringException e){
            System.out.println("Empty string in saved file string...");
        }

        // check if scanned task is done
        if (scannedTask[1].equals("1")){
            task.setDone();
        }

        return task;
    }

    /**
     * Writes current task list to specified text file to save it.
     */
    @Override
    public void saveToFile() {
        try {
            File file = new File(filepath);

            // create new directory and file if they do not exist
            if (!file.exists()){
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            // instantiate FileWriter object to overwrite specified text file
            FileWriter fileWriter = new FileWriter(filepath, false);
            for (int i = 0; i < tasksCount; i++){
                fileWriter.write(tasks.get(i).toString());
            }
            fileWriter.close();
        } catch (IOException e){
            System.out.println("Something went wrong while saving the task list to file...");
        }
    }
}
