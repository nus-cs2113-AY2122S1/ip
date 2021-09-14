package xRoss;

import xRoss.exception.EmptyStringException;
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

public class TaskManager implements FileManager{

    private ArrayList<Task> tasks = new ArrayList<>();
    private int completedTasksCount = 0;
    private int tasksCount = 0;

    final String filepath = "data/xRoss.txt";

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

    public void addTask(Task task) {
        // add new task to task list
        tasks.add(task);

        // print name of task to system output
        System.out.print("\tGot it! I've added this task for you:\n\t\t");
        tasks.get(tasksCount).printTask();

        // increment total tasksCount
        tasksCount++;
        printTaskCounts();
    }

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
    }

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
    }

    private void printTaskCounts() {
        System.out.println("\tThere are "
                + tasksCount
                + " task(s) in your task list, of which "
                + (tasksCount - completedTasksCount)
                + " is/are uncompleted.\n");
    }

    @Override
    public void readFromFile() {
        try {
            File file = new File(filepath);

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

    private Task convertFileStrToTask(String nextLine) {
        // split string using " | " pattern
        String[] scannedTask = nextLine.split(" \\| ");
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

        // check if scanned task is
        if (scannedTask[1].equals("1")){
            task.setDone();
        }

        return task;
    }

    @Override
    public void saveToFile() {
        try {
            File file = new File(filepath);

            // create new directory and file if they do not exist
            if (!file.exists()){
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(filepath, false);
            for (int i = 0; i < tasksCount; i++){
                fileWriter.write(tasks.get(i).toString());
            }
            fileWriter.close();
        } catch (IOException e){
            System.out.println("Something went wrong while saving the task list to file...");
            e.printStackTrace();
        }
    }
}
