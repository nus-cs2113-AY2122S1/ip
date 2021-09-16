package duke;

import duke.exception.AtEmptyException;
import duke.exception.ByEmptyException;
import duke.exception.NumberOutOfBoundsException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String INDENT = "    ";
    private static final int START_OF_STRING = 0;
    public static final String SAD_FACE = "\uD83D\uDE41";

    private static String inputCommand;
    private static String todoTask;
    private static String deadlineDescription;
    private static String by;
    private static String eventDescription;
    private static String at;
    private static int inputNum;
    private static String number;

    private static int dividerPosition;
    private static int charAfterDividerPosition;

    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ArrayList<Task> taskArrayList = new ArrayList<>();
    public static final String FILEPATH = "data/duke.txt";
    public static final String FOLDERPATH = "data";

    private static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    private static void printGreeting(String s, String s2) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(s);
        System.out.println(s2);
    }

    private static String getUserInput() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Enter command: ");
        String input = SCANNER.nextLine();
        // removes whitespace from user input
        while (input.trim().isEmpty()) {
            input = SCANNER.nextLine();
        }
        return input;
    }

    private static void printReply(String userInput) {
        try {
            processUserInput(userInput);
        } catch (NumberFormatException e) {
            System.out.println(SAD_FACE + " OOPS! The character you entered is not a number: " + number);
        } catch (NumberOutOfBoundsException e) {
            System.out.println(SAD_FACE + " OOPS! There is no task with this number: " + number);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(SAD_FACE + " The description of the " + inputCommand + " is not complete.");
        } catch (AtEmptyException e) {
            System.out.println(SAD_FACE + " \"at:\" cannot be empty.");
        } catch (ByEmptyException e) {
            System.out.println(SAD_FACE + " \"by:\" cannot be empty.");
        }
    }

    private static void processUserInput(String userInput) throws NumberOutOfBoundsException,
            StringIndexOutOfBoundsException, AtEmptyException, ByEmptyException {
        String[] splitInput = userInput.split(" ");
        inputCommand = splitInput[0];
        switch (inputCommand) {
        case COMMAND_LIST:
            performListTask();
            break;
        case COMMAND_DONE:
            handleIntConversion(userInput, COMMAND_DONE);
            performMarkTaskDone(inputNum);
            saveFile();
            break;
        case COMMAND_TODO:
            splitTodo(userInput);
            performAddTodo(todoTask);
            saveFile();
            break;
        case COMMAND_DEADLINE:
            splitDeadline(userInput);
            performAddDeadline(deadlineDescription, by);
            saveFile();
            break;
        case COMMAND_EVENT:
            splitEvent(userInput);
            performAddEvent(eventDescription, at);
            saveFile();
            break;
        case COMMAND_DELETE:
            handleIntConversion(userInput, COMMAND_DELETE);
            performDelete(inputNum);
            saveFile();
            break;
        case COMMAND_BYE:
            exitProgram();
            break;
        default:
            printInvalidInput();
        }
    }

    private static void printInvalidInput() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(SAD_FACE + " OOPS! I'm sorry, but I don't know what that means " + SAD_FACE);
    }

    private static void performListTask() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");

        for (Task t : taskArrayList) {
            System.out.println(INDENT + t.taskNum + "." + t);
        }
    }

    private static void handleIntConversion(String userInput, String command) {
        number = userInput.replace(command, "");
        number = number.trim();
        inputNum = Integer.parseInt(number);
    }

    private static void performMarkTaskDone(int inputNum) throws NumberOutOfBoundsException {
        //index of taskNum in taskArrayList
        int doneTaskNum = inputNum - 1;

        boolean isValidNum = (inputNum > 0) && (inputNum <= Task.taskCount);
        if (isValidNum) {
            taskArrayList.get(doneTaskNum).markAsDone();
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(INDENT + taskArrayList.get(doneTaskNum).taskNum + "." + taskArrayList.get(doneTaskNum));
        } else {
            throw new NumberOutOfBoundsException();
        }
    }

    private static void performDelete(int inputNum) throws NumberOutOfBoundsException {
        //index of taskNum in taskArrayList
        int deleteTaskNum = inputNum - 1;

        boolean isValidNum = (inputNum > 0) && (inputNum <= Task.taskCount);
        if (isValidNum) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Noted. I've removed this task:");
            System.out.println(INDENT + taskArrayList.get(deleteTaskNum).taskNum + "." + taskArrayList.get(deleteTaskNum));
            taskArrayList.remove(deleteTaskNum);
            updateTaskCount();
            updateTaskNum(deleteTaskNum);
            System.out.println("Now you have " + Task.taskCount + " tasks in the list");
        } else {
            throw new NumberOutOfBoundsException();
        }
    }

    private static void updateTaskCount(){
        Task.taskCount = Task.taskCount - 1;
    }

    private static void updateTaskNum(int deleteTaskNum){
        for (int i = deleteTaskNum; i < taskArrayList.size(); i++){
            taskArrayList.get(i).taskNum = taskArrayList.get(i).taskNum - 1;
        }
    }

    private static void splitTodo(String userInput) throws StringIndexOutOfBoundsException {
        todoTask = userInput.replace("todo", "");
        todoTask = todoTask.trim();
        if (todoTask.equals("")) {
            throw new StringIndexOutOfBoundsException();
        }
    }

    private static void splitDeadline(String userInput) throws StringIndexOutOfBoundsException,
            ByEmptyException {
        dividerPosition = userInput.indexOf("/by");
        deadlineDescription = userInput.substring(START_OF_STRING, dividerPosition);
        deadlineDescription = deadlineDescription.replace("deadline", "");
        deadlineDescription = deadlineDescription.trim();
        charAfterDividerPosition = dividerPosition + 1;
        by = userInput.substring(charAfterDividerPosition);
        by = by.replace("by", "");
        by = by.trim();
        if (by.equals("")) {
            throw new ByEmptyException();
        }
    }

    private static void splitEvent(String userInput) throws StringIndexOutOfBoundsException,
            AtEmptyException {
        dividerPosition = userInput.indexOf("/at");
        eventDescription = userInput.substring(START_OF_STRING, dividerPosition);
        eventDescription = eventDescription.replace("event", "");
        eventDescription = eventDescription.trim();
        charAfterDividerPosition = dividerPosition + 1;
        at = userInput.substring(charAfterDividerPosition);
        at = at.replace("at", "");
        at = at.trim();
        if (at.equals("")) {
            throw new AtEmptyException();
        }
    }

    private static void performAddTodo(String todoTask) {
        System.out.println(HORIZONTAL_LINE);
        taskArrayList.add(new Todo(todoTask));
        System.out.println("Got it. I've added this task:");
        System.out.println(INDENT + taskArrayList.get(Task.taskCount - 1));
        System.out.println("Now you have " + Task.taskCount + " tasks in the list");
    }

    private static void performAddDeadline(String description, String by) {
        System.out.println(HORIZONTAL_LINE);
        taskArrayList.add(new Deadline(description, by));
        System.out.println("Got it. I've added this task:");
        System.out.println(INDENT + taskArrayList.get(Task.taskCount - 1));
        System.out.println("Now you have " + Task.taskCount + " tasks in the list");
    }

    private static void performAddEvent(String description, String at) {
        System.out.println(HORIZONTAL_LINE);
        taskArrayList.add(new Event(description, at));
        System.out.println("Got it. I've added this task:");
        System.out.println(INDENT + taskArrayList.get(Task.taskCount - 1));
        System.out.println("Now you have " + Task.taskCount + " tasks in the list");
    }

    public static void exitProgram() {
        printGreeting("Bye. Hope to see you again soon!", HORIZONTAL_LINE);
        System.exit(0);
    }

    private static void saveFile() {
        try {
            createFolder();
            createFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void createFolder() {
        File folder = new File(FOLDERPATH);
        if (folder.mkdir()) {
            System.out.println("Folder created: " + folder.getName());
        }
    }

    private static void createFile() throws IOException {
        File f = new File(FILEPATH);
        if (f.createNewFile()) {
            System.out.println("File created: " + f.getName());
        }
        writeToFile();
    }

    private static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(FILEPATH);
        for (Task t : taskArrayList){
            fw.write(t.taskNum + "." + t + System.lineSeparator());
        }
        fw.close();
        System.out.println("File \"duke.txt\" updated");
    }


    public static void main(String[] args) {
        printLogo();
        printGreeting("Hello! I'm Duke", "What can I do for you?");
        while (true) {
            String userInput = getUserInput();
            printReply(userInput);
        }
    }
}
