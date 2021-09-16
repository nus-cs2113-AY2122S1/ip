package karlett.task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Task {

    private static int numberOfTasks = 0;

    public static void setFilePath(String filePath) {
        Task.filePath = filePath;
    }

    protected static String filePath = "";

    protected String description;
    protected boolean isDone;

    /* constructor used for user input */
    public Task(String description) throws IOException {
        this.description = description;
        this.isDone = false;
        increaseNumberOfTasks();
        appendNewTaskToFile();
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
        drawDivider();
        System.out.println("Karlett now remembers:\n" + "  " + this);
        if (numberOfTasks == 1) {
            System.out.println("You have 1 task in the list now meow (((;꒪ꈊ꒪;)))");
        } else {
            System.out.println("You have " + numberOfTasks + " tasks in the list now meow (((;꒪ꈊ꒪;)))");
        }
        drawDivider();
    }

    private void appendNewTaskToFile() throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        String textToAppend = "T | 0 | " + description + "\n";
        fw.write(textToAppend);
        fw.close();
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
        modifyTaskStatusInFile(index);
        printMarkAsDoneMessage();
    }

    private void modifyTaskStatusInFile(int index) throws IOException {
        String fileContent = "";
        String line = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        reader = new BufferedReader(new FileReader(filePath));
        for (int i = 0; i < index; i++) {
            line = reader.readLine();
            fileContent = fileContent + line + System.lineSeparator();
        }
        line = reader.readLine();
        String updatedTask = line.replaceFirst("0", "1");
        fileContent = fileContent + updatedTask + System.lineSeparator();
        for (int i = index + 1; i < numberOfTasks; i++) {
            line = reader.readLine();
            fileContent = fileContent + line + System.lineSeparator();
        }
        writer = new FileWriter(filePath);
        writer.write(fileContent);
        reader.close();
        writer.close();
    }

    private void printMarkAsDoneMessage() {
        drawDivider();
        System.out.println("Meow~ Karlett has marked this task as done:\n" +
                "  " + this);
        drawDivider();
    }

    public static void remove(ArrayList<Task> list, int index) throws IOException {
        removeTaskInFile(index - 1);
        Task t = list.get(index - 1);
        for (int i = index; i < list.size(); i++) {
            list.set(i - 1, list.get(i));
        }
        list.trimToSize();
        numberOfTasks--;
        printTaskDeletedMessage(t);
    }

    private static void removeTaskInFile(int index) throws IOException {
        String fileContent = "";
        String line = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        reader = new BufferedReader(new FileReader(filePath));
        for (int i = 0; i < numberOfTasks; i++) {
            line = reader.readLine();
            if (i == index) {
                continue;
            }
            fileContent = fileContent + line + System.lineSeparator();
        }
        writer = new FileWriter(filePath);
        writer.write(fileContent);
        reader.close();
        writer.close();
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
