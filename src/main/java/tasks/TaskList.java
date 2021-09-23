package tasks;

import java.util.ArrayList;

/**
 * Handles all the functions to help alter the tasks
 */
public class TaskList {

    /**
     * Manages the input by the user and alters the contents of the ArrayList tasksAL according to the user's input
     * @param input is the user input, which can be any of the following:
     *              list, done, delete, todo, event, deadline, find, bye or change
     * @param tasksAL is the ArrayList container that stores all the tasks that the user enters
     */
    public static void manageTasks(String input, ArrayList<Tasks> tasksAL) {
        try {
            if (input.equalsIgnoreCase("list")) {
                if (tasksAL.size() == 0) {
                    System.out.println("There is nothing in your list! Try adding something into your list.");
                } else {
                    printTasks(tasksAL);
                }

            } else if (input.toLowerCase().startsWith("done") && input.split(" ").length == 2) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                if (tasksAL.get(taskIndex).getComplete().equals("X")) {
                    System.out.println("You have already completed that task!");
                } else {
                    tasksAL.get(taskIndex).makeComplete();
                    printCompleted(tasksAL, taskIndex);
                }

            } else if (input.toLowerCase().startsWith("delete") && input.split(" ").length == 2) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                printDeleted(tasksAL, taskIndex);
                tasksAL.remove(taskIndex);

            } else if (input.toLowerCase().startsWith("todo")) {
                if (input.split(" ").length == 1) {
                    System.out.println("Please add a task after 'todo'");
                } else {
                    tasksAL.add(new TodoTasks(input.substring(5)));
                    printAdded(tasksAL);
                }

            } else if (input.toLowerCase().startsWith("deadline")) {
                tasksAL.add(new DeadlineTasks(extractTask(input, "deadline"), extractDateTime(input)));
                printAdded(tasksAL);

            } else if (input.toLowerCase().startsWith("event")) {
                tasksAL.add(new EventTasks(extractTask(input, "event"), extractDateTime(input)));
                printAdded(tasksAL);

            } else {
                System.out.println("Please add 'todo', 'deadline' or 'event' in the front of your task!");
            }

        } catch (NumberFormatException e){
            System.out.println("Please enter the number of a task.");
        } catch (StringIndexOutOfBoundsException e){
            System.out.println("Please use '/' followed by a date and/or time");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Number does not exist in list, please try again.");
        }
        Storage.saveTasks(tasksAL);
    }

    protected static void checkComplete(String check, ArrayList<Tasks> tasksAL) {
        if (check.equals("true")) {
            tasksAL.get(tasksAL.size() - 1).makeComplete();
        }
    }

    private static void printTasks(ArrayList<Tasks> tasksAL) {
        for (int taskIndex = 0; taskIndex < tasksAL.size(); taskIndex++) {
            System.out.println(taskIndex + 1 + ". " + tasksAL.get(taskIndex).getName());
        }
    }

    private static String extractDateTime(String input){
        return input.substring(input.indexOf("/") + 1);
    }

    private static String extractTask(String input, String type){
        int indexOfTask = type.length() + 1;
        return input.substring(indexOfTask, input.indexOf("/"));
    }

    private static void printAdded(ArrayList<Tasks> tasksAL){
        System.out.println("added: " + tasksAL.get(tasksAL.size() - 1).getName());
        System.out.println("Now you have " + tasksAL.size() + " tasks in the list!");
    }

    private static void printCompleted(ArrayList<Tasks> tasksAL, int taskIndex){
        System.out.println("Great job! You have completed the following task:");
        System.out.println(tasksAL.get(taskIndex).getName());
        System.out.println("Now you have " + tasksAL.size() + " tasks in the list!");
    }

    private static void printDeleted(ArrayList<Tasks> tasksAL, int taskIndex){
        System.out.println("Noted, I have deleted the following task:" + System.lineSeparator() + tasksAL.get(taskIndex).getName());
        System.out.println("Now you have " + (tasksAL.size() - 1) + " tasks in the list!");
    }
}
