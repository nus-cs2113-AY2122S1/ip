package tasks;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public abstract class Tasks {
    final public static String saveFilesDir = System.getProperty("user.dir") + File.separator + "saveFiles.txt";
    boolean isCompleted;
    String name;

    public abstract String getName();
    public abstract String getTaskData();

    protected void makeComplete() {
        isCompleted = true;
    }

    public String mark(){
        if (isCompleted) {
            return "X";
        } else {
            return " ";
        }
    }

    //Function to execute function to manage tasks
    public static void manageTasks(String input, ArrayList<Tasks> tasksAL) {
        if (input.equalsIgnoreCase("list")) {
            if (tasksAL.size() == 0){
                System.out.println("There is nothing in your list! Try adding something into your list.");
            } else {
                printTasks(tasksAL);
            }

        } else if(input.toLowerCase().startsWith("done") && input.split(" ").length == 2) {
            try {
                int taskNumber = Integer.parseInt(input.split(" ")[1]);
                if (tasksAL.get(taskNumber - 1).mark().equals("X")){
                    System.out.println("You have already completed that task!");
                } else {
                    tasksAL.get(taskNumber - 1).makeComplete();
                    printCompleted(tasksAL, taskNumber);
                }
            } catch(IndexOutOfBoundsException e){
                System.out.println("Number does not exist in list, please try again.");
            } catch(NumberFormatException e){
                System.out.println("Please add 'todo', 'deadline' or 'event' in the front of your task!");
            }

        } else if (input.toLowerCase().startsWith("todo")) {
            if (input.split(" ").length == 1) {
                System.out.println("Please add a task after 'todo'");
            } else {
                tasksAL.add(new TodoTasks(input.substring(5)));
                printAdded(tasksAL);
            }

        } else if (input.toLowerCase().startsWith("deadline")) {
            try {
                tasksAL.add(new DeadlineTasks(extractTask(input, "deadline"), extractDateTime(input)));
                printAdded(tasksAL);
            } catch (StringIndexOutOfBoundsException e){
                System.out.println("Please use / followed by a deadline");
            }

        } else if (input.toLowerCase().startsWith("event")) {
            try {
                tasksAL.add(new EventTasks(extractTask(input, "event"), extractDateTime(input)));
                printAdded(tasksAL);
            } catch (StringIndexOutOfBoundsException e){
                System.out.println("Please use / followed by a date or time");
            }

        } else {
            System.out.println("Please add 'todo', 'deadline' or 'event' in the front of your task!");
        }
        try {
            FileWriter myWriter = new FileWriter(saveFilesDir); //directory
            for (Tasks task : tasksAL) {
                myWriter.write(task.getTaskData());
                myWriter.write(System.lineSeparator());
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void loadTasks(String input, ArrayList<Tasks> tasksAL) {
        String[] data = input.split(",");
        switch (data[0]){
        case "T":
            tasksAL.add(new TodoTasks(data[2]));
            break;
        case "D":
            tasksAL.add(new DeadlineTasks(data[2], data[3]));
            break;
        case "E":
            tasksAL.add(new EventTasks(data[2], data[3]));
            break;
        default:
            checkComplete(data[1], tasksAL);
        }
    }

    private static void checkComplete(String check, ArrayList<Tasks> tasksAL) {
        if (check.equals("true")) {
            tasksAL.get(tasksAL.size() - 1).makeComplete();
        }
    }

    private static void printTasks(ArrayList<Tasks> tasksAL) {
        for (int taskNumber = 0; taskNumber < tasksAL.size(); taskNumber++) {
            System.out.println(taskNumber + 1 + ". " + tasksAL.get(taskNumber).getName());
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

    private static void printCompleted(ArrayList<Tasks> tasksAL, int taskNumber){
        System.out.println("Great job! You have completed the following task:");
        System.out.println(tasksAL.get(taskNumber - 1).getName());
        System.out.println("Now you have " + tasksAL.size() + " tasks in the list!");
    }
}
