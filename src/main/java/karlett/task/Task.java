package karlett.task;

import java.util.ArrayList;

public class Task {

    private static int numberOfTasks = 0;

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        increaseNumberOfTasks();
        printNewTaskAddedMessage();
    }

    protected void printNewTaskAddedMessage() {
        drawDivider();
        System.out.println("Karlett now remembers:\n" + "  " + this);
        if (numberOfTasks == 1) {
            System.out.println("You have 1 task in the list now meow (((;꒪ꈊ꒪;)))");
        } else {
            System.out.println("You have " + numberOfTasks + " tasks in the list now meow (((;꒪ꈊ꒪;)))");
        }
        drawDivider();
    }

    public Task() {
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "V" : " ");  // mark done task with V
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    public static void increaseNumberOfTasks() {
        Task.numberOfTasks++;
    }

    public void markAsDone() {
        this.isDone = true;
        printMarkAsDoneMessage();
    }

    private void printMarkAsDoneMessage() {
        drawDivider();
        System.out.println("Meow~ Karlett has marked this task as done:\n" +
                "  " + this);
        drawDivider();
    }

    public static void remove(ArrayList<Task> list, int index) {
        Task t = list.get(index - 1);
        for (int i = index; i < list.size(); i++) {
            list.set(i - 1, list.get(i));
        }
        list.trimToSize();
        numberOfTasks--;
        printTaskDeletedMessage(t);
    }

    private static void printTaskDeletedMessage(Task t) {
        drawDivider();
        System.out.println("Meow~ Karlett has deleted this task:\n" +
                "  " + t + "\nYou have " + numberOfTasks +
                " tasks in the list now meow (((;꒪ꈊ꒪;)))");
        drawDivider();
    }

    public static void printList(ArrayList<Task> list) {
        drawDivider();
        if (numberOfTasks == 0) {
            System.out.println("You have done everything! Time to relax with Karlett meow ʕ♡ﻌ♡ʔ");
        } else {
            for (int i = 0; i < numberOfTasks; i++) {
                System.out.println("ฅ" + (i + 1) + " " + list.get(i));
            }
        }
        drawDivider();
    }

    @Override
    public String toString() {
        return "[T] [" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public static void exit() {
        drawDivider();
        System.out.println("Bye~Bye~ヾ(￣▽￣) Hope to see you again soon meow.");
        drawDivider();
        System.exit(0);
    }

    public static void drawDivider() {
        int n = 4;
        while (n > 0) {
            System.out.print("ﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟ");
            n--;
        }
        System.out.println();
    }
}
