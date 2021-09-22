package shima.command;

import shima.task.Task;

import java.util.ArrayList;

public class ToDoList {
    //Corner symbols for to-do list frames
    public static final String TOP_LEFT_CORNER = "/";
    public static final String TOP_RIGHT_CORNER = "\\";
    public static final String BOTTOM_LEFT_CORNER = "\\";
    public static final String BOTTOM_RIGHT_CORNER = "/";

    /**
     * Prints the to-do list with frames
     *
     * @param tasks the array of class Task instance which stores all the tasks added by the user
     */
    public static void printToDoList(ArrayList<Task> tasks, int longestTaskDescription) {
        final int MIN_LENGTH = " My to-do list: ".length();
        //if longestTaskDescription is shorter than the length of the string "My to-do list: ", sets it to the length of the string
        if (longestTaskDescription < MIN_LENGTH) {
            longestTaskDescription = MIN_LENGTH;
        }
        //Prints the to-do list
        drawUpperFrame(longestTaskDescription);
        printTasks(tasks, longestTaskDescription);
        drawLowerFrame(longestTaskDescription);
    }

    /**
     * Prints the upper frame of the to-do list and its default display string
     *
     * @param longestTaskDescription the length of the longest task description string stored in the tasks array
     */
    private static void drawUpperFrame(int longestTaskDescription) {
        System.out.print("\t" + TOP_LEFT_CORNER); //the top left corner
        for (int i = 0; i < longestTaskDescription + "| [ ][ ] 100. ".length(); i++) {
            System.out.print("-");
        }
        System.out.println(TOP_RIGHT_CORNER);
        System.out.print("\t| My to-do list: ");
        for (int i = 0; i < longestTaskDescription + "| [ ][ ] 100. ".length() - "| My to-do list: ".length() + 1; i++) {
            System.out.print(" ");
        }
        System.out.println("|");
    }

    /**
     * Prints the tasks stored in the array, the frame starts with '|' and ends with '|', the ending frame is always located at the position of the longest task description
     *
     * @param tasks                  the array that stores all the tasks
     * @param longestTaskDescription the length of the longest task description stored in the tasks array
     */
    private static void printTasks(ArrayList<Task> tasks, int longestTaskDescription) {
        for (int i = 0; i < tasks.size(); i++) {
            //Fill the first [] with class type, and the second [] with a 'X' if the task is completed
            if (tasks.get(i).getDone()) {
                System.out.print("\t| [" + tasks.get(i).getClassType() + "][X] " + (i + 1) + ". ");
            } else {
                System.out.print("\t| [" + tasks.get(i).getClassType() + "][ ] " + (i + 1) + ". ");
            }
            //Calculates the required spacing for the current task as compared to the longest task description to print '|'
            int distanceToClosingFrame = longestTaskDescription + "| [ ][ ] 100. ".length() - ("| [ ][ ] " + (i + 1) + ". ").length() + 1;
            System.out.printf("%1$-" + distanceToClosingFrame + "s", tasks.get(i));
            System.out.println("|");
        }
    }

    /**
     * Prints the bottom frame of the to-do list and the guide for reading the to-do list
     *
     * @param longestTaskDescription the length of the longest task description string stored in the tasks array
     */
    private static void drawLowerFrame(int longestTaskDescription) {
        System.out.print("\t" + BOTTOM_LEFT_CORNER);
        for (int i = 0; i < longestTaskDescription + "| [ ][ ] 100. ".length(); i++) {
            System.out.print("-");
        }
        System.out.println(BOTTOM_RIGHT_CORNER);
        //Shows the guide for understanding the to-do list
        System.out.println("\tFor your knowledge, ");
        System.out.println("\tthe first [ ] indicates the type of the task ('T' for to-do, 'D' for deadline, 'E' for event)");
        System.out.println("\tthe second [ ] indicates whether the task is completed:\n" +
                "\t[X] when the task is marked completed\t[ ] when the task is not done.");
    }
}
