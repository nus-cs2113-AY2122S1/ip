package duke;

import duke.exception.NoDescriptionException;
import duke.exception.NoSpaceException;
import duke.exception.NoTimeException;
import duke.exception.WrongTimeTypeException;
import duke.list.Deadline;
import duke.list.Event;
import duke.list.Task;
import duke.list.ToDo;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Duke {
    public static final String SEPARATE_LINE = "____________________________________________________________";
    public static final String INSTRUCTION = "Here are what I can do:\n"
            + "echo description: I will repeat your description\n"
            + "todo description: I will add a task with no time constraint to your task list\n"
            + "deadline description /by time: I will add a task with deadline to your task list\n"
            + "event description /at time: I will add a task with its time to your task list\n"
            + "done taskNumber: I will mark this task as done\n"
            + "list: I will show your task list\n"
            + "help: view the commands you can use\n"
            + "bye: finish using Duke";
    public static final String LIST_STORAGE_FOLDER = "./data/";
    public static final String LIST_STORAGE_FILE = "./data/duke.txt";
    
    public static void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(SEPARATE_LINE);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(INSTRUCTION);
        System.out.println(SEPARATE_LINE);
    }

    public static void printBye() {
        System.out.println(SEPARATE_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(SEPARATE_LINE);
    }

    public static void checkEcho(String command) throws NoDescriptionException, NoSpaceException{
        if (command.equals("echo") || command.equals("echo ")) {
            throw new NoDescriptionException();
        } else if (!command.startsWith("echo ")) {
            throw new NoSpaceException();
        }
    }

    public static void handleEcho(String command) {
        boolean proceed = true;
        System.out.println(SEPARATE_LINE);
        try {
            checkEcho(command);
        } catch (NoDescriptionException e) {
            System.out.println("Sorry, I have no idea what you want me to echo");
            proceed = false;
        } catch (NoSpaceException e) {
            System.out.println("Please use a space to separate command, task, and time (if any)");
            proceed = false;
        }

        if (proceed) {
            System.out.println(command.substring(5));
        }
        System.out.println(SEPARATE_LINE);
    }

    public static void checkToDo(String command) throws NoDescriptionException, NoSpaceException{
        if (command.equals("todo") || command.equals("todo ")) {
            throw new NoDescriptionException();
        } else if (!command.startsWith("todo ")) {
            throw new NoSpaceException();
        }
    }

    public static boolean proceedTodo(String command) {
        try {
            checkToDo(command);
        } catch (NoDescriptionException e) {
            System.out.println("What task do you want to add?");
            return false;
        } catch (NoSpaceException e) {
            System.out.println("Please use a space to separate command, task, and time (if any)");
            return false;
        }
        return true;
    }

    public static void printToDo(Task task, int size) {
        System.out.println("Got it. I've added this task: \n" + "\t" + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public static void checkDeadline(String command) throws NoDescriptionException, NoTimeException, NoSpaceException, WrongTimeTypeException{
        int slashIndex = command.indexOf("/");
        if (command.equals("deadline") || command.equals("deadline ")) {
            throw new NoDescriptionException();
        } else if (!command.startsWith("deadline ")) {
            throw new NoSpaceException();
        } else if (!command.contains("/")) {
            throw new NoTimeException();
        }  else if (slashIndex == 9) {
            throw new NoDescriptionException();
        }  else if (command.charAt(slashIndex - 1) != ' ') {
            throw new NoSpaceException();
        } else if ((command.length() == slashIndex + 3)) {
            throw new NoTimeException();
        } else if ((command.length() == (slashIndex + 4)) && (command.charAt(slashIndex + 3) == ' ')) {
            throw new NoTimeException();
        } else if (command.charAt(slashIndex + 3) != ' ') {
            throw new NoSpaceException();
        } else if (command.contains("/at")) {
            throw new WrongTimeTypeException();
        }
    }

    public static boolean proceedDeadline(String command) {
        try {
            checkDeadline(command);
        } catch (NoDescriptionException e) {
            System.out.println("What is the description of the task?");
            return false;
        } catch (NoTimeException e) {
            System.out.println("When is the deadline?");
            return false;
        } catch (NoSpaceException e) {
            System.out.println("Please use a space to separate command, task, and time (if any)");
            return false;
        } catch (WrongTimeTypeException e) {
            System.out.println("Please use \"\\by\" for deadline type tasks");
            return false;
        }
        return true;
    }

    public static Deadline setupDeadline(String command) {
        int slashIndex = command.indexOf("/");
        int startIndex = command.indexOf(" ");
        String description = command.substring(startIndex+1, slashIndex-1);
        String by = command.substring(slashIndex+4);
        return (new Deadline(description, by));
    }

    public static void printDeadline(Task task, int size) {
        System.out.println("Got it. I've added this task: \n" + "\t" + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public static void checkEvent(String command) throws NoDescriptionException, NoTimeException, NoSpaceException, WrongTimeTypeException {
        int slashIndex = command.indexOf("/");
        if (command.equals("event") || command.equals("event ")) {
            throw new NoDescriptionException();
        } else if (!command.startsWith("event ")) {
            throw new NoSpaceException();
        } else if (!command.contains("/")) {
            throw new NoTimeException();
        } else if (slashIndex == 6) {
            throw new NoDescriptionException();
        } else if (command.charAt(slashIndex - 1) != ' ') {
            throw new NoSpaceException();
        } else if ((command.length() == slashIndex + 3)) {
            throw new NoTimeException();
        } else if ((command.length() == (slashIndex + 4)) && (command.charAt(slashIndex + 3) == ' ')) {
            throw new NoTimeException();
        } else if (command.charAt(slashIndex + 3) != ' ') {
            throw new NoSpaceException();
        } else if (command.contains("/by")) {
            throw new WrongTimeTypeException();
        }
    }

    public static boolean proceedEvent(String command) {
        try {
            checkEvent(command);
        } catch (NoDescriptionException e) {
            System.out.println("What is the description of the task?");
            return false;
        } catch (NoTimeException e) {
            System.out.println("When is the time?");
            return false;
        } catch (NoSpaceException e) {
            System.out.println("Please use a space to separate command, task, and time (if any)");
            return false;
        } catch (WrongTimeTypeException e) {
            System.out.println("Please use \"\\at\" for deadline type tasks");
            return false;
        }
        return true;
    }

    public static Event setupEvent(String command) {
        int slashIndex = command.indexOf("/");
        int startIndex = command.indexOf(" ");
        String description = command.substring(startIndex+1, slashIndex-1);
        String at = command.substring(slashIndex+4);
        return (new Event(description, at));
    }

    public static void printEvent(Task task, int size) {
        System.out.println("Got it. I've added this task: \n" + "\t" + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public static void printList(ArrayList<Task> tasks, int size) {
        System.out.println(SEPARATE_LINE);
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println(SEPARATE_LINE);
    }

    public static void setTaskDone(ArrayList<Task> tasks, ArrayList<String> taskLines, int index) {
        Task updatedTask = tasks.get(index);
        updatedTask.markAsDone();
        tasks.set(index, updatedTask);
        if ("T".equals((updatedTask.toString()).substring(1, 2))) {
            taskLines.set(index, convertTodotoLine((ToDo) updatedTask));
        } else if ("D".equals((updatedTask.toString()).substring(1, 2))) {
            taskLines.set(index, convertDeadlinetoLine((Deadline) updatedTask));
        } else if ("E".equals((updatedTask.toString()).substring(1, 2))) {
            taskLines.set(index, convertEventtoLine((Event) updatedTask));
        }
        storetoHardDisk(taskLines);
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("\t" + tasks.get(index));
    }

    public static void handleDone(String command) throws NoDescriptionException, NoSpaceException {
        if (command.equals("done") || command.equals("done ")) {
            throw new NoDescriptionException();
        } else if (!command.startsWith("done ")) {
            throw new NoSpaceException();
        }
    }

    public static void printDone(ArrayList<Task> tasks, ArrayList<String> taskLines, String command) {
        try {
            handleDone(command);
            String taskNumber = command.substring(5);
            int number = Integer.parseInt(taskNumber);
            setTaskDone(tasks, taskLines, number - 1);
        } catch (NoDescriptionException e) {
            System.out.println("Which task do you want to mark as done?");
        } catch (NoSpaceException e) {
            System.out.println("Please use a space to separate command, task, and time (if any)");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, this is not one of the task");
        } catch (NumberFormatException e) {
            System.out.println("Please use the number of the task");
        }
    }

    public static void handleDelete(String command) throws NoDescriptionException, NoSpaceException {
        if (command.equals("delete") || command.equals("delete ")) {
            throw new NoDescriptionException();
        } else if (!command.startsWith("delete ")) {
            throw new NoSpaceException();
        }
    }

    public static void deleteTask(ArrayList<Task> tasks, ArrayList<String> taskLines, int index){
        System.out.println("Noted! I've deleted this task: ");
        System.out.println("\t" + tasks.get(index));
        tasks.remove(index);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        taskLines.remove(index);
        storetoHardDisk(taskLines);
    }

    public static void printDelete(ArrayList<Task> tasks, ArrayList<String> taskLines, String command) {
        try {
            handleDelete(command);
            String taskNumber = command.substring(7);
            int number = Integer.parseInt(taskNumber);
            deleteTask(tasks, taskLines, number - 1);
        } catch (NoDescriptionException e) {
            System.out.println("Which task do you want to mark as done?");
        } catch (NoSpaceException e) {
            System.out.println("Please use a space to separate command, task, and time (if any)");
        } catch (NullPointerException e) {
            System.out.println("Sorry, this is not one of the task");
        } catch (NumberFormatException e) {
            System.out.println("Please use the number of the task");
        }
    }

    public static boolean createFile(String filePath) {
        boolean canCreate = false;
        File newFile = new File(filePath);
        try {
            canCreate = newFile.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return canCreate;
    }

    public static boolean createFolder(String folderPath) {
        boolean canCreate = false;
        File newFolder = new File(folderPath);
        if (!newFolder.exists()) {
            canCreate = newFolder.mkdir();
        }
        return canCreate;
    }

    public static void initializeHardDiskSave() {
        if (createFolder(LIST_STORAGE_FOLDER)) {
            System.out.println("Folder created for storing task list");
        } else {
            System.out.println("Nice! You already have a folder for storing task list");
        }

        if (createFile(LIST_STORAGE_FILE)) {
            System.out.println("duke.txt created inside data folder for storing task list");
        } else {
            System.out.println("Nice! You already have a file inside data folder for storing task list");
        }
        System.out.println("Reading your List......");
    }

    public static Task convertLinetoTask(String taskLine) {
        Task task;
        String taskType = taskLine.substring(0, 1);
        String taskDone = taskLine.substring(4, 5);
        int index = taskLine.indexOf(" | ", 8);
        String description;
        String time = "";

        if (index == -1) {
            description = taskLine.substring(8);
        } else {
            description = taskLine.substring(8, index);
            time = taskLine.substring(index + 3);
        }

        switch (taskType) {
        case "D":
            task = new Deadline(description, time);
            if (taskDone.equals("√")) {
                task.markAsDone();
            }
        case "E":
            task = new Event(description, time);
            if (taskDone.equals("√")) {
                task.markAsDone();
            }
        default:
            task = new ToDo(description);
            if (taskDone.equals("√")) {
                task.markAsDone();
            }
        }
        return task;
    }

    public static String convertTodotoLine(ToDo task) {
        if (task.isDone()) {
            return ("T | √ | " + task.getDescription());
        } else {
            return ("T | X | " + task.getDescription());
        }
    }

    public static String convertDeadlinetoLine(Deadline task) {
        if (task.isDone()) {
            return ("D | √ | " + task.getDescription() + " | " + task.getBy());
        } else {
            return ("D | X | " + task.getDescription() + " | " + task.getBy());
        }
    }

    public static String convertEventtoLine(Event task) {
        if (task.isDone()) {
            return ("E | √ | " + task.getDescription() + " | " + task.getAt());
        } else {
            return ("E | X | " + task.getDescription() + " | " + task.getAt());
        }
    }

    public static void initializeList(ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File(LIST_STORAGE_FILE);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            tasks.add(convertLinetoTask(s.nextLine()));
        }
        printList(tasks, tasks.size());
    }

    public static void initializeTaskLineList(ArrayList<String> taskLines) throws FileNotFoundException {
        File f = new File(LIST_STORAGE_FILE);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            taskLines.add(s.nextLine());
        }
    }

    public static void appendToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(LIST_STORAGE_FILE, true);
        fw.write(textToAdd);
        fw.close();
    }

    public static void clearFile() throws IOException {
        FileWriter fw = new FileWriter(LIST_STORAGE_FILE);
        fw.write("");
        fw.close();
    }

    public static void storetoHardDisk(ArrayList<String> taskLines) {
        try {
            clearFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        for (String taskLine : taskLines) {
            try {
                appendToFile(taskLine + System.lineSeparator());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args){
        printGreeting();
        initializeHardDiskSave();
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<String> taskLines = new ArrayList<>();
        try {
            initializeTaskLineList(taskLines);
            initializeList(tasks);
        } catch (FileNotFoundException e) {
            System.out.println("File not found, pls try again");
        }

        Scanner in = new Scanner(System.in);
        while (true) {
            String command = in.nextLine();
            if (command.equals("bye")) {
                printBye();
                break;
            } else if (command.startsWith("echo")){
                handleEcho(command);
            } else if (command.startsWith("todo")) {
                System.out.println(SEPARATE_LINE);
                boolean proceed = proceedTodo(command);
                if (proceed) {
                    tasks.add(new ToDo(command.substring(5)));
                    int size = tasks.size();
                    taskLines.add(convertTodotoLine((ToDo) tasks.get(size - 1)));
                    storetoHardDisk(taskLines);
                    printToDo(tasks.get(size - 1), size);
                }
                System.out.println(SEPARATE_LINE);
            } else if (command.startsWith("deadline")) {
                System.out.println(SEPARATE_LINE);
                boolean proceed = proceedDeadline(command);
                if (proceed) {
                    tasks.add(setupDeadline(command));
                    int size = tasks.size();
                    taskLines.add(convertDeadlinetoLine((Deadline) tasks.get(size - 1)));
                    storetoHardDisk(taskLines);
                    printDeadline(tasks.get(size - 1), size);
                }
                System.out.println(SEPARATE_LINE);
            } else if (command.startsWith("event")) {
                System.out.println(SEPARATE_LINE);
                boolean proceed = proceedEvent(command);
                if (proceed) {
                    tasks.add(setupEvent(command));
                    int size = tasks.size();
                    taskLines.add(convertEventtoLine((Event) tasks.get(size - 1)));
                    storetoHardDisk(taskLines);
                    printEvent(tasks.get(size - 1), size);
                }
                System.out.println(SEPARATE_LINE);
            } else if (command.equals("list")) {
                int size = tasks.size();
                printList(tasks, size);
            } else if (command.startsWith("done")) {
                System.out.println(SEPARATE_LINE);
                printDone(tasks, taskLines, command);
                System.out.println(SEPARATE_LINE);
            } else if (command.startsWith("delete")) {
                System.out.println(SEPARATE_LINE);
                printDelete(tasks, taskLines, command);
                System.out.println(SEPARATE_LINE);
            } else if (command.equals("help")) {
                System.out.println(SEPARATE_LINE);
                System.out.println(INSTRUCTION);
                System.out.println(SEPARATE_LINE);
            } else {
                System.out.println(SEPARATE_LINE);
                System.out.println("I'm sorry, but I don't know what that means");
                System.out.println(SEPARATE_LINE);
            }
        }
    }
}

