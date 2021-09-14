package duke;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE_SEPARATOR = "------------------------------------";
    private static final String HELLO_MESSAGE = "Hello! I'm duke.Duke\nWhat can I do for you?";
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String ERROR_MESSAGE = "You need to specify the task type!";

    private static final String FILE_PATH = "data/duke.txt";

    private static final String GAP = " / ";

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasks();
        printHelloMessage();
        handleCommand();
        printByeMessage();
        saveTasks();
    }

    private static void loadTasks() {
        try {
            addTasksIntoList();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static File loadFile() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            File dir = new File("data");
            dir.mkdir();
            File newFile = new File("data/duke.txt");
            newFile.createNewFile();
            return newFile;
        }
        return file;
    }

    private static void addTasksIntoList() throws IOException {
        File f = loadFile();
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String entry = s.nextLine();
            String[] entryComponents = entry.split(GAP);
            String description = entryComponents[0] + " " + entryComponents[2];
            switch (entryComponents[0]) {
            case "T":
                tasks.add(new Todo(description));
                break;
            case "D":
                String by = "by " + entryComponents[3];
                tasks.add(new Deadline(description, by));
                break;
            case "E":
                String at = "at " + entryComponents[3];
                tasks.add(new Event(description, at));
                break;
            default:
                break;
            }
            if (entryComponents[1].equals("X")) {
                tasks.get(tasks.size() - 1).completeTask();;
            }
        }
    }

    private static void saveTasks() {
        try {
            writeTasksToFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void writeTasksToFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String details = task.getDescription().substring(task.getDescription().indexOf(" ") + 1);
            String date = "";
            if (task instanceof Deadline) {
                date = ((Deadline) task).getBy().split(" ", 2)[1].trim();
            } else if (task instanceof Event) {
                date = ((Event) task).getAt().split(" ", 2)[1].trim();
            }
            String taskLabel = task.getType() + GAP + task.getIsDone() + GAP;
            String taskBody = details.trim() + GAP + date + System.lineSeparator();
            fw.write(taskLabel + taskBody);
        }
        fw.close();
    }

    private static void handleCommand() {
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        while (!input.equals("bye")) {
            try {
                String keyword = input.split(" ")[0].toLowerCase();
                switch (keyword) {
                case "bye":
                    continue;
                case "list":
                    listTasks();
                    break;
                case "todo":
                    addTodo(input);
                    break;
                case "deadline":
                    addDeadline(input);
                    break;
                case "event":
                    addEvent(input);
                    break;
                case "done":
                    finishTask(input);
                    break;
                default:
                    promptInvalidInput();
                    break;
                }
            } catch (DukeException e) {
                System.out.println(DukeException.getErrorMessage());
            } catch (NumberFormatException e) {
                System.out.println("You are supposed to enter a number!");
            }
            input = in.nextLine();
        }
    }

    private static void promptInvalidInput() throws DukeException {
        throw new DukeException(ERROR_MESSAGE);
    }

    private static void finishTask(String input) throws DukeException {
        int index = Integer.parseInt(input.split(" ", 2)[1].trim());
        if (index > tasks.size()) {
            throw new DukeException("You don't have so many tasks yet!");
        }
        tasks.get(index - 1).completeTask();
        System.out.println("Nice! I have marked this task as done: ");
        System.out.println(tasks.get(index - 1).getTaskInfo());
    }

    private static void addEvent(String input) throws DukeException {
        String at;
        String description;
        String[] inputSplit = input.trim().split(" ");
        boolean noInput = inputSplit.length == 1;
        if (noInput) {
            throw new DukeException("You have to specify the task!");
        }
        boolean noSeparator = !input.contains("/");
        if (noSeparator) {
            throw new DukeException("Your task is not in the right format");
        }
        String[] inputWords = input.split("/");
        boolean noTask = inputWords[0].trim().equals("event");
        boolean noDate = inputWords.length == 1;
        if (noTask || noDate) {
            throw new DukeException("Your task is not in the right format");
        }
        description = inputWords[0].trim();
        at = inputWords[1].trim();
        System.out.println("Got it. I've added this task:");
        tasks.add(new Event(description, at));
        System.out.println(tasks.get(tasks.size() - 1).getTaskInfo());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    private static void addDeadline(String input) throws DukeException {
        String by;
        String description;
        String[] inputSplit = input.trim().split(" ");
        boolean noInput = inputSplit.length == 1;
        if (noInput) {
            throw new DukeException("You have to specify the task!");
        }
        boolean noSeparator = !input.contains("/");
        if (noSeparator) {
            throw new DukeException("Your task is not in the right format");
        }
        String[] inputWords = input.split("/");
        boolean noTask = inputWords[0].trim().equals("deadline");
        boolean noDate = inputWords.length == 1;
        if (noTask || noDate) {
            throw new DukeException("Your task is not in the right format");
        }
        description = inputWords[0].trim();
        by = inputWords[1].trim();
        System.out.println("Got it. I've added this task:");
        tasks.add(new Deadline(description, by));
        System.out.println(tasks.get(tasks.size() - 1).getTaskInfo());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    private static void addTodo(String input) throws DukeException {
        String[] inputSplit = input.split(" ");
        boolean noInput = inputSplit.length == 1;
        if (noInput) {
            throw new DukeException("You have to specify the task!");
        }
        System.out.println("Got it. I've added this task:");
        tasks.add(new Todo(input));
        System.out.println(tasks.get(tasks.size() - 1).getTaskInfo());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    private static void listTasks() throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("You do not have any tasks in your list!");
        }
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).printTask(i + 1);
        }
    }

    private static void printHelloMessage() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(LINE_SEPARATOR);
        System.out.println(HELLO_MESSAGE);
        System.out.println(LINE_SEPARATOR);
    }

    private static void printByeMessage() {
        System.out.println(BYE_MESSAGE);
        System.out.println(LINE_SEPARATOR);
    }
}
