package shima.task.action;

import shima.design.Default;
import shima.storage.Storage;
import shima.command.CommandLibrary;
import shima.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class DeleteTasks {
    /**
     * Deletes the task(s) according to the task index/indices provided
     *
     * @param tasks The array list that stored all the tasks
     * @param words The array of words that compose the input command
     */
    public static void deleteTasks(ArrayList<Task> tasks, String[] words) {
        switch (words.length) {
        case 1:
            Default.showMessage("Sorry, the input task index to delete is missing!");
            break;
        case 2:
            if (CommandLibrary.isCommandDeleteAll(words[1])) {
                deleteAllTasks(tasks);
            } else {
                deleteSingleTask(tasks, words[1]);
            }
            break;
        default:
            deleteMultipleTasks(tasks, words);
        }
        try {
            Storage.updateStorage(tasks);
        } catch (IOException ioException) {
            Default.showMessage("An unexpected error occurs when I try to update the file");
            System.out.print("\t");
            ioException.printStackTrace();
        }
    }

    /**
     * Deletes all the tasks stored
     *
     * @param tasks The array list that stored all the tasks
     */
    public static void deleteAllTasks(ArrayList<Task> tasks) {
        tasks.clear();
        Default.showMessage("All tasks have been removed! Time to chill?");
    }

    /**
     * Deletes the specific task when there is only one task specified
     *
     * @param tasks The array list that stored all the tasks
     * @param word  The array of words that compose the input command
     */
    public static void deleteSingleTask(ArrayList<Task> tasks, String word) {
        int index;
        try {
            //Compiler throws NumberFormatException if word is not a digit character
            index = Integer.parseInt(word);
            try {
                if (tasks.get(index - 1).getDone()) {
                    Default.showMessage("I have removed this task: [" + tasks.get(index - 1).getClassType() + "][X] " + tasks.get(index - 1));
                } else {
                    Default.showMessage("I have removed this task: [" + tasks.get(index - 1).getClassType() + "][ ] " + tasks.get(index - 1));
                }
                tasks.remove(index - 1);
                if (tasks.size() > 0) {
                    System.out.println("You have left " + tasks.size() + " tasks to do!");
                } else {
                    System.out.println("\tNice! You have finished all tasks! Time to chill~");
                }
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                Default.showMessage("Sorry, the input task index to delete is invalid!");
            }
        } catch (NumberFormatException numberFormatException) {
            Default.showMessage("Sorry, the input task index to delete is invalid!");
        }
    }

    /**
     * Deletes multiple tasks when there are multiple task indices provided
     *
     * @param tasks The array list that stored all the tasks
     * @param words The array of words that compose the input command
     */
    public static void deleteMultipleTasks(ArrayList<Task> tasks, String[] words) {
        try {
            ArrayList<Integer> taskIndices = new ArrayList<>();
            //Checks if the input task indices are valid
            for (int i = 1; i < words.length; i++) {
                taskIndices.add(Integer.parseInt(words[i]) - 1); //throws NumberFormatException if input is not digit character
            }
            try {
                //Deletes the task with the largest task index first
                taskIndices.sort(Collections.reverseOrder());
                for (Integer i : taskIndices) {
                    String doneSymbol = (tasks.get(i).getDone()) ? "X" : " ";
                    Default.showMessage("I have removed this task: [" + tasks.get(i).getClassType() + "][" + doneSymbol + "] " + tasks.get(i));
                    tasks.remove((int) i);
                }
                //Prints the message according to tasks left
                if (tasks.size() > 0) {
                    System.out.println("You have left " + tasks.size() + " tasks to do!");
                } else {
                    System.out.println("\tNice! You have finished all tasks! Time to chill~");
                }
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                Default.showMessage("Sorry, there are task indices which are not in 1 to " + tasks.size() + ", I do not know how to handle :(");
            }
        } catch (NumberFormatException numberFormatException) {
            Default.showMessage("Sorry, there are some task indices which are invalid, I do not know how to handle :(");
        }
    }
}
