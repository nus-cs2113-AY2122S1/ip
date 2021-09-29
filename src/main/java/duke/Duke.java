package duke;

import duke.exception.AtEmptyException;
import duke.exception.ByEmptyException;
import duke.exception.NumberOutOfBoundsException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
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
    private static String dueTime;
    private static String eventDescription;
    private static String atPlace;
    private static int inputNum;
    private static String number;

    //private static int dividerPosition;
    //private static int charAfterDividerPosition;

    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    public static final char TODO_SYMBOL = 'T';
    public static final char DEADLINE_SYMBOL = 'D';
    public static final char EVENT_SYMBOL = 'E';


    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ArrayList<Task> taskArrayList = new ArrayList<>();
    public static final String FILEPATH = "data/duke.txt";
    public static final String FOLDERPATH = "data";
    public static final int INDEX_OF_TASK_TYPE = 1;
    public static final int START_OF_DESCRIPTION = 7;

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
            System.out.println(SAD_FACE + e.getMessage() + number);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(SAD_FACE + " The description of the " + inputCommand + " is not complete.");
        } catch (AtEmptyException e) {
            System.out.println(SAD_FACE + e.getMessage());
        } catch (ByEmptyException e) {
            System.out.println(SAD_FACE + e.getMessage());
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
            writeToFile();
            break;
        case COMMAND_TODO:
            splitTodo(userInput);
            performAddTodo(todoTask);
            writeToFile();
            break;
        case COMMAND_DEADLINE:
            splitDeadline(userInput);
            performAddDeadline(deadlineDescription, dueTime);
            writeToFile();
            break;
        case COMMAND_EVENT:
            splitEvent(userInput);
            performAddEvent(eventDescription, atPlace);
            writeToFile();
            break;
        case COMMAND_DELETE:
            handleIntConversion(userInput, COMMAND_DELETE);
            performDelete(inputNum);
            writeToFile();
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
        int doneTaskIndex = inputNum - 1;

        boolean isValidNum = (inputNum > 0) && (inputNum <= Task.taskCount);
        if (isValidNum) {
            taskArrayList.get(doneTaskIndex).markAsDone();
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(INDENT + taskArrayList.get(doneTaskIndex).taskNum + "." + taskArrayList.get(doneTaskIndex));
        } else {
            throw new NumberOutOfBoundsException();
        }
    }

    private static void performDelete(int inputNum) throws NumberOutOfBoundsException {
        int deleteTaskIndex = inputNum - 1;

        boolean isValidNum = (inputNum > 0) && (inputNum <= Task.taskCount);
        if (isValidNum) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Noted. I've removed this task:");
            System.out.println(INDENT + taskArrayList.get(deleteTaskIndex).taskNum + "." + taskArrayList.get(deleteTaskIndex));
            taskArrayList.remove(deleteTaskIndex);
            decrementTaskCount();
            updateTaskNum(deleteTaskIndex);
            System.out.println("Now you have " + Task.taskCount + " tasks in the list");
        } else {
            throw new NumberOutOfBoundsException();
        }
    }

    private static void decrementTaskCount() {
        Task.taskCount = Task.taskCount - 1;
    }

    private static void updateTaskNum(int deleteTaskNum) {
        for (int i = deleteTaskNum; i < taskArrayList.size(); i++) {
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
        int dividerPosition = userInput.indexOf("/by");
        deadlineDescription = userInput.substring(START_OF_STRING, dividerPosition);
        deadlineDescription = deadlineDescription.replace("deadline", "");
        deadlineDescription = deadlineDescription.trim();
        int charAfterDividerPosition = dividerPosition + 1;
        dueTime = userInput.substring(charAfterDividerPosition);
        dueTime = dueTime.replace("by", "");
        dueTime = dueTime.trim();
        if (dueTime.equals("")) {
            throw new ByEmptyException();
        }
    }

    private static void splitEvent(String userInput) throws StringIndexOutOfBoundsException,
            AtEmptyException {
        int dividerPosition = userInput.indexOf("/at");
        eventDescription = userInput.substring(START_OF_STRING, dividerPosition);
        eventDescription = eventDescription.replace("event", "");
        eventDescription = eventDescription.trim();
        int charAfterDividerPosition = dividerPosition + 1;
        atPlace = userInput.substring(charAfterDividerPosition);
        atPlace = atPlace.replace("at", "");
        atPlace = atPlace.trim();
        if (atPlace.equals("")) {
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

    private static void loadFile() {
        try {
            createFolder();
            createFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (ByEmptyException e) {
            System.out.println(SAD_FACE + e.getMessage());
        } catch (AtEmptyException e) {
            System.out.println(SAD_FACE + e.getMessage());
        }
    }

    private static void createFolder() {
        File folder = new File(FOLDERPATH);
        if (folder.mkdir()) {
            System.out.println("Folder created: " + folder.getName());
        }
    }

    private static void createFile() throws IOException, ByEmptyException, AtEmptyException {
        File f = new File(FILEPATH);
        if (f.createNewFile()) {
            System.out.println("File created: " + f.getName());
        } else {
            readFile(f);
        }
    }

    private static void writeToFile() {
        try {
            FileWriter fw = new FileWriter(FILEPATH);
            for (Task t : taskArrayList) {
                fw.write(t.taskNum + "." + t + System.lineSeparator());
            }
            fw.close();
            System.out.println("File \"duke.txt\" updated");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void readFile(File f) throws FileNotFoundException, ByEmptyException, AtEmptyException {
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            int fullStopPosition = line.indexOf(".");
            String lineWithoutNum = line.substring(fullStopPosition + 1);
            int readNumber = Integer.parseInt(line.substring(START_OF_STRING, fullStopPosition));
            readTaskType(lineWithoutNum, readNumber);
        }
        System.out.println("File Loaded: " + Task.taskCount + " Tasks");
    }

    private static void readTaskType(String lineWithoutNum, int readNumber) throws ByEmptyException, AtEmptyException {
        char taskType = lineWithoutNum.charAt(INDEX_OF_TASK_TYPE);
        switch (taskType) {
        case TODO_SYMBOL:
            taskArrayList.add(new Todo(lineWithoutNum.substring(START_OF_DESCRIPTION)));
            break;
        case DEADLINE_SYMBOL:
            splitFileDeadline(lineWithoutNum);
            taskArrayList.add(new Deadline(deadlineDescription, dueTime));
            break;
        case EVENT_SYMBOL:
            splitFileEvent(lineWithoutNum);
            taskArrayList.add(new Event(eventDescription, atPlace));
        }
        readDoneMark(lineWithoutNum, readNumber);
    }

    private static void splitFileDeadline(String userInput) throws StringIndexOutOfBoundsException,
            ByEmptyException {
        int dividerPosition = userInput.indexOf("(by:");
        deadlineDescription = userInput.substring(START_OF_DESCRIPTION, dividerPosition);
        deadlineDescription = deadlineDescription.replace("deadline", "");
        deadlineDescription = deadlineDescription.trim();
        int charAfterDividerPosition = dividerPosition + 1;
        dueTime = userInput.substring(charAfterDividerPosition);
        dueTime = dueTime.replace("by:", "");
        dueTime = dueTime.trim();
        int INDEX_OF_CLOSING_BRACKET = dueTime.length() - 1;
        dueTime = dueTime.substring(START_OF_STRING, INDEX_OF_CLOSING_BRACKET);
        if (dueTime.equals("")) {
            throw new ByEmptyException();
        }
    }

    private static void splitFileEvent(String userInput) throws StringIndexOutOfBoundsException,
            AtEmptyException {
        int dividerPosition = userInput.indexOf("(at:");
        eventDescription = userInput.substring(START_OF_DESCRIPTION, dividerPosition);
        eventDescription = eventDescription.replace("event", "");
        eventDescription = eventDescription.trim();
        int charAfterDividerPosition = dividerPosition + 1;
        atPlace = userInput.substring(charAfterDividerPosition);
        atPlace = atPlace.replace("at:", "");
        atPlace = atPlace.trim();
        int INDEX_OF_CLOSING_BRACKET = atPlace.length() - 1;
        atPlace = atPlace.substring(START_OF_STRING, INDEX_OF_CLOSING_BRACKET);
        if (atPlace.equals("")) {
            throw new AtEmptyException();
        }
    }

    private static void readDoneMark(String lineWithoutNum, int readNumber) {
        int INDEX_OF_DONE_MARK = 4;
        if (lineWithoutNum.charAt(INDEX_OF_DONE_MARK) == 'X') {
            taskArrayList.get(readNumber - 1).isDone = true;
        }
    }


    public static void main(String[] args) {
        printLogo();
        loadFile();
        printGreeting("Hello! I'm Duke", "What can I do for you?");
        while (true) {
            String userInput = getUserInput();
            printReply(userInput);
        }
    }
}
