package ip.src.main.java;

import java.util.ArrayList;

public class TaskList {

    public static ArrayList<Task> tasks = new ArrayList<>();
    public static ArrayList<String> letter = new ArrayList<>();
    public static ArrayList<String> done = new ArrayList<>();

    private static int i=0;

    public static void addTask(Task t, int type) {
        tasks.add(t);
        if (type == 1) letter.add("D");
        else if (type == 2) letter.add("E");
        else if (type == 3) letter.add("T");
        done.add(" ");
        i++;
    }

    public static void markDone(int i) {
        done.set(i-1, "X");
    }

    public static void remove(int i) {
        done.remove(i-1);
        letter.remove(i-1);
        tasks.remove(i-1);
    }

    public static void printTask() {
        for (int j=1; j<=tasks.size(); j++) System.out.println(j + ". "
                + "[" + letter.get(j-1) + "] "
                + "[" + done.get(j-1) + "] " +
                tasks.get(j-1).description());
    }

    public static String getDescription(int i) {
        return tasks.get(i-1).description();
    }

    public static String getTask(int i) {
        //System.out.println("internal i=" + i);
        return i + " | " + letter.get(i - 1) + " | " + done.get(i - 1) +  " | " + getDescription(i);
    }
}
