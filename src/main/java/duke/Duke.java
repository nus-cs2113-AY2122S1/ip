import duke.tasks.*;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class Duke {

    private static Scanner in = new Scanner(System.in);
    static final int MAX_SIZE = 100;
    private static String content = "";

    private static String lineInput = "";
    private static ArrayList<Task> taskList = new ArrayList<Task>();
    private static int tasksCount = 0;

    public static void addTask(Task t){
        if (t.getDescription().length() > 0) {
            System.out.println("Got it. I've added this task:");
            taskList.add(t);
            tasksCount++;
            System.out.println(t);
            System.out.println("Now you have " + tasksCount + " tasks on the list.");
        }
    }

    public static void addTask(Task t, boolean b){
        if (t.getDescription().length() > 0) {
            taskList.add(t);
            tasksCount++;
        }
    }


    private static void listTasks(){
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasksCount; i++) {
            Task currTask = taskList.get(i - 1);
            System.out.println(i + ". " + currTask);
        }
    }

    private static void markTaskDone(int index) {
        taskList.get(index).markDone();
        Task currTask = taskList.get(index);
        System.out.println((index + 1) + ". " + currTask);
    }

    private static void deleteTask(int index) {
        Task currTask = taskList.get(index);
        System.out.println("Noted. I've removed this task:");
        taskList.remove(index);
        System.out.println(currTask);
        tasksCount--;
        System.out.println("Now you have " + tasksCount + " tasks on the list.");
    }

    private static void printBorder(){
        System.out.println("____________________________________________________________");
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            String lineType = currentLine.split("\\|", 3)[0];
            String lineIsDone = currentLine.split("\\|", 3)[1];
            String lineAction = currentLine.split("\\|", 3)[2];
            String lineDescription;
            String lineDueDate;
            switch(lineType){
                case "[T]":
                    Todo oldTodo = new Todo(lineAction, Boolean.valueOf(lineIsDone));
                    addTask(oldTodo, true);
                    break;
                case "[D]":
                    lineDescription = lineAction.split("/by", 2)[0];
                    lineDueDate = lineAction.split("/by", 2)[1];
                    Deadline oldDeadline = new Deadline(lineDescription, Boolean.valueOf(lineIsDone), lineDueDate);
                    addTask(oldDeadline, true);
                    break;
                case "[E]":
                    lineDescription = lineAction.split("/at", 2)[0];
                    lineDueDate = lineAction.split("/at", 2)[1];
                    Event oldEvent = new Event(lineDescription, Boolean.valueOf(lineIsDone), lineDueDate);
                    addTask(oldEvent, true);
                    break;
            }
        }
    }



    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greet = "Hello I'm Duke\n"
                + "What can I do for you?";
        String exit = "Bye. Hope to see you again soon!";


        printBorder();
        System.out.println("Hello from\n" + logo);
        System.out.println(greet);
        printBorder();

        try {
            printFileContents("C:/Wayne/NUS/Y2S1/CS2113/iP/data/duke.txt");

        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        while (!lineInput.equals("bye")) {
            printBorder();
            String[] inputArray = lineInput.split(" ", 2);
            String inputCommand = inputArray[0];
            String inputAction = null;
            if (inputArray.length > 1){
                inputAction = inputArray[1];
            }

            switch(inputCommand){
                case "":
                    break;
                case "list":
                    listTasks();
                    break;
                case "done":
                    try {
                        markTaskDone(Integer.parseInt(inputAction) - 1);
                    }
                    catch (NullPointerException e){
                        System.out.println("INVALID: Empty 'done' index");
                    }
                    catch (IndexOutOfBoundsException e){
                        System.out.println("INVALID: Index out of bounds");
                    }
                    break;
                case "todo":
                    try {
                        Todo newTodo = new Todo(inputAction);
                        addTask(newTodo);
                    }
                    catch (NullPointerException e){
                        System.out.println("INVALID: Empty 'todo' input");
                    }
                    break;
                case "deadline":
                    try {
                        String DeadlineToDo = inputAction.split("/", 2)[0];
                        String dueDate = inputAction.split("/by", 2)[1];
                        Deadline newDeadline = new Deadline(DeadlineToDo, dueDate);
                        addTask(newDeadline);
                    }
                    catch (NullPointerException e){
                        System.out.println("INVALID: Empty 'deadline' input");
                    }
                    catch (IndexOutOfBoundsException e){
                        System.out.println("INVALID: Insufficient 'deadline' description or time");
                    }
                    break;
                case "event":
                    try {
                        String EventToDo = inputAction.split("/", 2)[0];
                        String dueTime = inputAction.split("/at", 2)[1];
                        Event newEvent = new Event(EventToDo, dueTime);
                        addTask(newEvent);
                    }
                    catch (NullPointerException e){
                        System.out.println("INVALID: Empty 'event' description");
                    }
                    catch (IndexOutOfBoundsException e){
                        System.out.println("INVALID: Insufficient 'event' description or time");
                    }
                    break;
                case "delete":
                    try {
                        deleteTask(Integer.parseInt(inputAction) - 1);
                    }
                    catch (NullPointerException e){
                        System.out.println("INVALID: Empty 'delete' index");
                    }
                    catch (IndexOutOfBoundsException e){
                        System.out.println("INVALID: Index out of bounds");
                    }
                    break;
                default:
                    System.out.println("INVALID: Invalid command");
                    break;
            }
            printBorder();
            lineInput = in.nextLine();
        }
        printBorder();
        String file = "C:/Wayne/NUS/Y2S1/CS2113/iP/data/duke.txt";
        try {
            for (int i = 0; i < taskList.size(); i++) {
                Task currentTask = taskList.get(i);
                String currentType = currentTask.getType();
                switch(currentType){
                    case "[T]":
                        content += currentType +  '|' + String.valueOf(currentTask.getIsDone()) + '|' + currentTask.getDescription() + System.lineSeparator();
                        break;
                    case "[D]":
                        content += currentType +  '|' + String.valueOf(currentTask.getIsDone()) + '|' + currentTask.getDescription() + "/by" + currentTask.getDueDate() + System.lineSeparator();
                        break;
                    case "[E]":
                        content += currentType +  '|' + String.valueOf(currentTask.getIsDone()) + '|' + currentTask.getDescription() + "/at" + currentTask.getDueDate() + System.lineSeparator();
                        break;
                }
            }
            writeToFile(file, content);
        }
        catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        System.out.println(exit);
        printBorder();
    }
}
