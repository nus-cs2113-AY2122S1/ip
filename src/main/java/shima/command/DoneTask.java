package shima.command;

import shima.design.Default;
import shima.storage.Storage;
import shima.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class DoneTask {
    /**
     * Marks the given tasks as done, and handles the possible errors if the input task number is not valid
     *
     * @param tasks The array list that contains all the tasks stored inside the to-do list
     * @param words The array of words that compose the input command
     */
    public static void handleTaskDone(ArrayList<Task> tasks, String[] words) {
        try {
            if (words.length == 1) {
                Default.showMessage("Sorry, the input task number is missing, please try again! :(");
            }
            for (int i = 1; i < words.length; i++) {
                //check if the input character after the word "done" is integer value
                int taskIndex = Integer.parseInt(words[i]);
                showTaskDoneMessage(tasks, taskIndex);
            }
            Storage.updateStorage(tasks);
        } catch (NumberFormatException |
                IndexOutOfBoundsException ex) {
            Default.showMessage("Sorry, the input task number is invalid, please try again! :(");
        } catch (IOException ex) {
            Default.showMessage("An unexpected error occurs when I try to update the file");
            System.out.print("\t");
            ex.printStackTrace();
        }
    }

    /**
     * Shows the message to indicate that the task is marked as done
     *
     * @param tasks      The array list which stores all the tasks
     * @param taskNumber The given task number to mark as done
     */
    private static void showTaskDoneMessage(ArrayList<Task> tasks, int taskNumber) {
        if (!tasks.get(taskNumber - 1).getDone()) {
            tasks.get(taskNumber - 1).setDone();
            System.out.println("\tHooray! Task number " + taskNumber + " has been marked completed!");
            System.out.println("\t[✔] " + tasks.get(taskNumber - 1).getTask());
        } else {
            System.out.println("\tThe task number " + taskNumber + " - \"" + tasks.get(taskNumber - 1).getTask() + "\" has already been done!");
        }
    }
}
