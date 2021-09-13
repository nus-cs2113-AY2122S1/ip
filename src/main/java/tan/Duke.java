package tan;

import tan.tasktype.Task;

import java.util.List;
import java.util.Scanner;


public class Duke {
    final static String BORDER = "------------------------------------------------------------------------";

    public static void main(String[] args) {
        final Scanner SC = new Scanner(System.in);
        printIntro();
        intializeAndLoadFile();
        String input;
        while (true) {
            input = readInput(SC);
            String command = getCommand(input);
            switch (command) {
            case "end":
                exitProgram();
                break;
            case "list":
                TaskManager.printList();
                break;
            case "done":
                int index = getIndexOfTask(input);
                if (index >= 0) {
                    TaskManager.markTaskAsDone(index);
                    saveList();
                }
                break;
            default:
                TaskManager.addTask(input);
                saveList();
                break;
            }
            System.out.println(BORDER);
        }
    }

    /**
     * This function saves the current list
     * into the file taskData. It will also inform
     * the user if the save was successful or not.
     */
    private static void saveList() {
        int saveStatus = TaskManager.saveCurrentList();
        if (saveStatus == 0) {
            System.out.println("File successfully updated.");
        } else {
            System.out.println("Error in saving current task! Please check the inputs.");
        }
    }

    /**
     * This function setups the reader & writer
     * needed to access the data file. If the file is not
     * found it creates one. After doing so,
     * reads the current data in the file & loads
     * it into the tasklist.
     */
    private static void intializeAndLoadFile() {
        List<Task> listOfStoredDatas = DataManager.setFileAndGetTasks();
        TaskManager.loadDataIntoList(listOfStoredDatas);
    }

    /**
     * Simply prints the intro message
     * & border once.
     */
    public static void printIntro() {
        System.out.println("Top of the morning my good sir, what can I do for you on this fine day?");
        System.out.println(BORDER);
    }

    /**
     * Simply prints the outro message
     * & border once.
     */
    public static void printOutro() {
        System.out.println("I bid you farewell my good man. Good Bye.");
        System.out.println(BORDER);
    }

    /**
     * This function reads the users input
     * and strips any extra front & back space
     * and returns the String.
     *
     * @param sc The scanner that allows java to read from the terminal.
     * @return The user input in a String format without any trailing spaces
     */
    public static String readInput(Scanner sc) {
        String input = sc.nextLine();
        return input.strip();
    }

    /**
     * Calls the printOutro function
     * and closes this program.
     */
    public static void exitProgram() {
        printOutro();
        System.exit(0);
    }

    /**
     * Takes in the whole user input as a String
     * splits it into an array along spaces
     * and assumes the user keyed in the corresponding
     * index at the 2nd input. It then returns the index
     * in integer.
     *
     * @param x The whole user input as a string.
     * @return The index of the task in integer.
     */
    public static int getIndexOfTask(String x) {
        int index;
        try {
            String[] listOfInputs = x.split(" ");
            index = Integer.parseInt(listOfInputs[1]);
        } catch (NumberFormatException e) {
            System.out.println("Index not recognized. Try again!");
            index = -1;
        } catch (Exception e) {
            System.out.println("Error in converting Index." + e);
            e.printStackTrace();
            index = -1;
        }
        return index;
    }

    /**
     * Takes in the whole user input
     * splits it along the spaces & returns
     * the first "word" of the string.
     *
     * @param x The whole string.
     * @return The first word of the string.
     */
    public static String getCommand(String x) {
        return x.split(" ")[0];
    }

}
