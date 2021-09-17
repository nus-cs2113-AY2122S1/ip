package Duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    //define constants
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";

    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_BYE = "bye";

    private static final String DEADLINE_BY_PREFIX = "/by ";
    private static final String EVENT_AT_PREFIX = "/at ";

    //pepepopo response text output
    private static final String GREETING = "Hello! I'm Pepepopo\n" +
            "What can I do for you?";
    private static final String DIVIDER = "____________________________________________________________";
    private static final String BYE = "PLEASE DONT LEAVE :( \n" +
            "a....noo.....ahhhhh..\n" +
            "..AAAAAAGHHHH.......pepepopo loved u :(";

    private static final String TASK_ADD = "New task! Pepepopo has added it to the list: ";
    private static final String TASK_DONE = "Yay! Pepepopo has marked your task as done:";
    private static final String TASK_DELETE = "Alright! Pepepopo has removed the task:";

    private static final String TODO_EMPTY = "Todo cannot be empty!";
    private static final String DEADLINE_EMPTY = "Deadline cannot be empty!";
    private static final String EVENT_EMPTY = "Event cannot be empty!";
    private static final String INVALID_INPUT = "Invalid input, try another command";
    private static final String INVALID_NUMBER = "Please enter a valid number after the command";

    private static final String NEW_FILE = "Pepepopo has created new data file!";
    private static final String UPDATE_FILE = "Pepepopo has updated your database from duke.txt";
    private static final String CORRUPT_FILE = "Pepepopo can't read your corrupted file :(";

    private static final String TODO_TYPE = "T";
    private static final String DEADLINE_TYPE = "D";
    private static final String EVENT_TYPE = "E";

    private static String filePath = String.valueOf(Paths.get("data/duke.txt"));
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static String getInput() {
        String line;
        Scanner in = new Scanner(System.in);

        line = in.nextLine();
        return line;
    }

    public static void executeCommand(String input) throws DukeException, IOException {
        String[] commandAndParams = splitCommandString(input, " ");
        String command = commandAndParams[0];
        String params = commandAndParams[1];
        switch (command) {
        case COMMAND_TODO:
            try {
                executeTodo(params);
            } catch (DukeException e) {
                print(TODO_EMPTY);
            }
            break;
        case COMMAND_DEADLINE:
            try {
                executeDeadline(params);
            } catch (DukeException e) {
                print(DEADLINE_EMPTY);
            }
            break;
        case COMMAND_EVENT:
            try {
                executeEvent(params);
            } catch (DukeException e) {
                print(EVENT_EMPTY);
            }
            break;
        case COMMAND_DONE:
            executeDone(params);
            break;
        case COMMAND_LIST:
            executeList();
            break;
        case COMMAND_DELETE:
            executeDelete(params);
            break;
        case COMMAND_BYE:
            writeToFile();
            executeBye();
            break;
        default:
            throw new DukeException();
        }
    }

    public static void addTask(Task t) {
        tasks.add(t);
        Task.setTaskCount(1);
    }

    public static void addAndPrintTask(Task t) {
        addTask(t);
        printAdd(t);
    }

    public static void executeTodo(String params) throws DukeException {
        if (params == "") {
            throw new DukeException();
        }
        Todo t = new Todo(params);
        addAndPrintTask(t);
    }

    public static void executeDeadline(String params) throws DukeException {
        if (params == "") {
            throw new DukeException();
        }
        String[] descAndBy = splitCommandString(params, DEADLINE_BY_PREFIX);
        String description = descAndBy[0];
        String by = descAndBy[1];
        Deadline d = new Deadline(description, by);
        addAndPrintTask(d);
    }

    public static void executeEvent(String params) throws DukeException {
        if (params == "") {
            throw new DukeException();
        }
        String[] descAndAt = splitCommandString(params, EVENT_AT_PREFIX);
        String description = descAndAt[0];
        String at = descAndAt[1];
        Event e = new Event(description, at);
        addAndPrintTask(e);
    }

    public static void executeDone(String params) {
        if (isDone(params)) {
            int taskNumber = Integer.parseInt(params) - 1;
            tasks.get(taskNumber).setDone(true);
            printDone(taskNumber);
        }
    }

    public static void executeDelete(String params) {
        if (isDone(params)) {
            int taskNumber = Integer.parseInt(params) - 1;
            Task.setTaskCount(-1);
            printDelete(taskNumber);
            tasks.remove(taskNumber);
        }
    }

    public static void executeList() {
        for (Task item : tasks) {
            int curr = tasks.indexOf(item) + 1;
            System.out.println(curr + "." + item);
        }
        print("");
    }

    public static void executeBye() {
        print(UPDATE_FILE);
        print(BYE);
        System.exit(0);
    }

    public static boolean isDone(String params) {
        isInt(params);
        int taskNumber = Integer.parseInt(params);
        if (taskNumber > Task.getTaskCount()) {
            print(INVALID_NUMBER);
            return false;
        } else {
            return true;
        }
    }

    public static void isInt(String string) {
        try {
            int intValue = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            print(INVALID_NUMBER);
        }
    }

    public static void manageFile() {
        try {
            readFile("data/duke.txt");
        } catch (FileNotFoundException e) {
            try {
                Path filePath = Paths.get("data/duke.txt");
                Files.createDirectories(filePath.getParent());
                print(NEW_FILE);
            } catch (IOException e1) {
                e1.getMessage();
            }
        }
    }

    public static void readFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String rawLineData = s.nextLine();
            try {
                String [] lineData = splitDataString(rawLineData);
                manageTaskFromFile(lineData);
            } catch (DukeException e){
                print(CORRUPT_FILE);
            }
        }
    }

    public static void manageTaskFromFile(String[] lineData) throws DukeException {
        String taskType = lineData[0];
        boolean isDone = lineData[1].equals("1");
        String description = lineData[2];
        String extraDescription = lineData[3];
        Task taskToAdd;
        switch (taskType) {
        case(TODO_TYPE):
            taskToAdd = new Todo(description);
            break;
        case(DEADLINE_TYPE):
            taskToAdd = new Deadline(description, extraDescription);
            break;
        case(EVENT_TYPE):
            taskToAdd = new Event(description, extraDescription);
            break;
        default:
            throw new DukeException();
        }
        taskToAdd.setDone(isDone);
        addTask(taskToAdd);
    }

    public static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t: tasks){
            String textToWrite = formatStringForFile(t);
            fw.write(textToWrite);
        }
        fw.close();
    }

    public static String formatStringForFile(Task t) {
        String description = "";
        String taskType = "";
        String status = getStringStatus(t.isDone);
        if (t instanceof Todo) {
            description = t.getDescription();
            taskType = "T";
        }
        else if (t instanceof Deadline) {
            description = String.format("%s | %s", t.getDescription(), t.getBy());
            taskType = "D";
            System.out.println(description);
            System.out.println(t.getDescription());
        }
        else if (t instanceof Event) {
            description = String.format("%s | %s", t.getDescription(), t.getAt());
            taskType = "E";
        }
        String textToWrite = String.format("%s | %s | %s\n", taskType, status, description);
        return textToWrite;
    }

    public static String getStringStatus(boolean b) {
        String status;
        if (b) {
            status = "1";
        } else {
            status = "0";
        }
        return status;
    }

    public static String[] splitCommandString(String input, String separator) {
        String[] split = input.trim().split(separator, 2);
        return split.length == 2 ? split : new String[] {split[0], ""};
    }

    public static String[] splitDataString(String input) throws DukeException {
        String[] split = input.trim().split(" \\| ", 4);
        if (split.length < 3) {
            throw new DukeException();
        }
        return split.length == 4 ? split: new String[] {split[0], split[1], split[2], ""};
    }

    public static void printGreeting() {
        print(GREETING);
    }

    public static void printAdd(Task task) {
        print(TASK_ADD +
                "\n  " + task +
                "\nYou now have " + Task.getTaskCount() + " task(s) in your list.");
    }

    public static void printDone(int taskNumber) {
        print(TASK_DONE +
                "\n" + tasks.get(taskNumber));
    }

    public static void printDelete(int taskNumber) {
        print(TASK_DELETE +
                "\n" + tasks.get(taskNumber) +
                "\nYou now have " + Task.getTaskCount() + " task(s) in your list.");
    }

    public static void print(String text) {
        if (!text.equals("")) {
            System.out.println(text);
        }
        System.out.println(DIVIDER);
    }

    public static void main(String[] args) throws IOException {
        printGreeting();
        manageFile();

        while (true) {
            String input = getInput();
            try {
                executeCommand(input);
            } catch (DukeException e) {
                print(INVALID_INPUT);
            }
        }
    }
}
