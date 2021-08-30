package todo;

import java.util.Scanner;

public class Task {

    private static int LIST_SIZE;
    private static int currentIndex = 0;
    private String[] lineStorage;
    private Boolean[] isDone;

    public Task(int LIST_SIZE) {
        this.LIST_SIZE = LIST_SIZE;
        this.lineStorage = new String[LIST_SIZE];
        this.isDone = new Boolean[LIST_SIZE];
    }

    public void increaseIndex() {
        currentIndex++;
    }

    public void decreaseIndex() {
        currentIndex--;
    }

    public void addTask(String inputLine) {
        currentIndex++;
        lineStorage[currentIndex] = inputLine;
        isDone[currentIndex] = false;
        System.out.println("____________________________________________________________\n");
        System.out.println("added" + ": " + lineStorage[currentIndex]);
        System.out.println("____________________________________________________________\n");
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= this.currentIndex; i++) {
            if (this.isDone[i] == false) {
                System.out.println(i + ". [] " + this.lineStorage[i]);
            } else if (this.isDone[i] == true) {
                System.out.println(i + ". [X]" + this.lineStorage[i]);
            }
        }
        System.out.println("____________________________________________________________\n");
    }

    public void completedTask(String input) {
        String[] words = input.split(" ");
        int index = Integer.parseInt(words[1]);
        this.isDone[index] = true;
        System.out.println("Nice! I have marked this task as done!");
        System.out.println("[X] " + this.lineStorage[index]);
    }

    public Boolean checkWord() {
        Scanner in = new Scanner(System.in);
        String line;
        line = in.nextLine();
        assert line != null;
        if (line.contains("done")) {
            completedTask(line);
            return false;
        } else if (line.equals("list")) {
            listTasks();
            return false;
        } else if (line.equals("bye")) {
            return true;
        } else {
            addTask(line);
            return false;
        }
    }
}
