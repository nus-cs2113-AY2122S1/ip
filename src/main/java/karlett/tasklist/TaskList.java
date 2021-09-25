package karlett.tasklist;

import karlett.storage.TaskListEncoder;
import karlett.task.Task;
import karlett.ui.TextUi;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    public static int numberOfTasks = 0;
    ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
        numberOfTasks = 0;
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    public static void increaseNumberOfTasks() {
        numberOfTasks++;
    }

    public static void remove(ArrayList<Task> list, int index) throws IOException {
        TaskListEncoder.removeTaskInFile(index - 1);
        Task t = list.get(index - 1);
        for (int i = index; i < list.size(); i++) {
            list.set(i - 1, list.get(i));
        }
        list.trimToSize();
        numberOfTasks--;
        TextUi.printTaskDeletedMessage(t);
    }

    public static void printList(ArrayList<Task> list) {
        TextUi.drawDivider();
        if (numberOfTasks == 0) {
            System.out.println("You have done everything! Time to relax with Karlett meow ʕ♡ﻌ♡ʔ");
        } else {
            for (int i = 0; i < numberOfTasks; i++) {
                System.out.println("ฅ" + (i + 1) + " " + list.get(i));
            }
        }
        TextUi.drawDivider();
    }
}
