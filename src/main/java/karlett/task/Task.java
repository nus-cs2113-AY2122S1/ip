package karlett.task;

import karlett.storage.TaskListEncoder;
import karlett.ui.TextUi;

import java.io.IOException;
import java.util.ArrayList;

public class Task {

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    protected static int numberOfTasks = 0;

    protected String description;
    protected boolean isDone;

    /* constructor used for user input */
    public Task(String description) throws IOException {
        this.description = description;
        this.isDone = false;
        increaseNumberOfTasks();
        TaskListEncoder.appendNewTaskToFile(this);
        printNewTaskAddedMessage();
    }

    /* constructor used for loading file data */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        increaseNumberOfTasks();
    }

    public Task() {
    }

    protected void printNewTaskAddedMessage() {
        TextUi.drawDivider();
        System.out.println("Karlett now remembers:\n" + "  " + this);
        if (numberOfTasks == 1) {
            System.out.println("You have 1 task in the list now meow (((;꒪ꈊ꒪;)))");
        } else {
            System.out.println("You have " + numberOfTasks + " tasks in the list now meow (((;꒪ꈊ꒪;)))");
        }
        TextUi.drawDivider();
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "V" : " ");  // mark done task with V
    }

    public static void increaseNumberOfTasks() {
        Task.numberOfTasks++;
    }

    public void markAsDone(int index) throws IOException {
        this.isDone = true;
        TaskListEncoder.modifyTaskStatusInFile(index);
        printMarkAsDoneMessage();
    }

    private void printMarkAsDoneMessage() {
        TextUi.drawDivider();
        System.out.println("Meow~ Karlett has marked this task as done:\n" +
                "  " + this);
        TextUi.drawDivider();
    }

    public static void remove(ArrayList<Task> list, int index) throws IOException {
        TaskListEncoder.removeTaskInFile(index - 1);
        Task t = list.get(index - 1);
        for (int i = index; i < list.size(); i++) {
            list.set(i - 1, list.get(i));
        }
        list.trimToSize();
        numberOfTasks--;
        printTaskDeletedMessage(t);
    }

    private static void printTaskDeletedMessage(Task t) {
        TextUi.drawDivider();
        System.out.println("Meow~ Karlett has deleted this task:\n" +
                "  " + t + "\nYou have " + numberOfTasks +
                " tasks in the list now meow (((;꒪ꈊ꒪;)))");
        TextUi.drawDivider();
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

    @Override
    public String toString() {
        return "[T] [" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public static void exit() {
        TextUi.drawDivider();
        System.out.println("Bye~Bye~ヾ(￣▽￣) Hope to see you again soon meow.");
        TextUi.drawDivider();
        System.exit(0);
    }
}
