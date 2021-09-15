package duke;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

import duke.exception.IllegalCommandException;
import duke.exception.EmptyCommandException;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<>();
    public static final String FILE_NAME = "duke.txt";
    public static final String DIR_NAME = "data";
    public static final String FILE_PATH = DIR_NAME + "/" + FILE_NAME;

    /**
     * Prints a farewell message when the user exits the program.
     */
    public static void commandBye() {
        System.out.println("Bye! I hope to see you again soon :)");
    }

    /**
     * Lists all tasks with their status.
     */
    public static void commandList() {
        System.out.println("Here are your current tasks and their status:");
        for (Task task : tasks) {
            System.out.println((tasks.indexOf(task) + 1) + ". " + task);
        }
    }

    /**
     * Adds a task of a certain type defined by the user to the list.
     *
     * @param line The command entered by the user.
     */
    public static void addTask(String line) throws EmptyCommandException, IllegalCommandException, IOException {

        if (!line.startsWith("todo") && !line.startsWith("deadline") && !line.startsWith("event")) {
            throw new IllegalCommandException();
        }

        if (line.split(" ").length < 2) {
            throw new EmptyCommandException();
        }

        if (line.startsWith("todo")) {
            tasks.add(Task.getTaskCount(), new Todo(line.replace("todo ", "")));
        } else if (!line.contains("/")) {
            throw new IllegalCommandException();
        } else if (line.startsWith("deadline")) {
            String[] words = line.split(" /by ");
            tasks.add(Task.getTaskCount(), new Deadline(words[0].replace("deadline ", ""), words[1]));
        } else if (line.startsWith("event")) {
            String[] words = line.split(" /at ");
            tasks.add(Task.getTaskCount(), new Event(words[0].replace("event ", ""), words[1]));
        }

        System.out.println("I've added that to your list:\n" + tasks.get(Task.getTaskCount() - 1));
        System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
        saveData();
    }

    /**
     * Sets a task in the list (specified by the user) as done.
     * The command is entered in the format "done X", where X is
     * the task's number.
     *
     * @param line The command entered by the user.
     */
    public static void doneTask(String line) throws EmptyCommandException, IllegalCommandException, IOException {
        String[] words = line.split(" ");
        if (words.length < 2) {
            throw new EmptyCommandException();
        } else {
            try {
                int taskIndex = Integer.parseInt(words[1]);
                tasks.get(taskIndex - 1).setDone(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(taskIndex - 1));
            } catch (NumberFormatException e) {
                throw new IllegalCommandException();
            }
        }
        saveData();
    }

    public static void deleteTask(String line) throws EmptyCommandException, IllegalCommandException, IOException {
        String[] words = line.split(" ");
        if (words.length < 2) {
            throw new EmptyCommandException();
        } else {
            try {
                int taskIndex = Integer.parseInt(words[1]);
                System.out.println("Okay, I've deleted that task!");
                System.out.println(tasks.get(taskIndex - 1));
                tasks.remove(taskIndex - 1);
            } catch (NumberFormatException e) {
                throw new IllegalCommandException();
            }
        }
        saveData();
    }

    public static void startDuke() throws IOException {
        if (Files.notExists(Paths.get(FILE_PATH))) {
            Files.createDirectories(Paths.get(FILE_PATH).getParent());
            Files.createFile(Paths.get(FILE_PATH));
            System.out.println("Hi! I'm Duke. I've created your data file for you, what would you like me to do?");
        } else {
            loadData();
            System.out.println("Welcome back!");
            commandList();
        }
    }

    public static void loadData() throws FileNotFoundException {
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        while(s.hasNext()) {
            tasks.add(parseTask(s.nextLine()));
        }
    }

    public static void saveData() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task task : tasks) {
            fw.write(task.toText() + "\n");
        }
        fw.close();
    }

    public static Task parseTask(String taskInfo) throws IllegalArgumentException {
        String[] infoParsed = taskInfo.split("\\|");
        Task newTask;

        switch (infoParsed[0]) {
            case "[D] ":
                newTask = new Deadline(infoParsed[2].strip(), infoParsed[3].strip());
                break;
            case "[E] ":
                newTask = new Event(infoParsed[2].strip(), infoParsed[3].strip());
                break;
            case "[T] ":
                newTask = new Todo(infoParsed[2].strip());
                break;
            default:
                throw new IllegalArgumentException();
        }

        if (infoParsed[1].equals(" [X] ")) {
            newTask.setDone(true);
        }
        return newTask;
    }

    public static void main(String[] args) {
        try {
            startDuke();
        } catch (IOException e) {
            System.out.println("I could not create/load your file!");
            System.exit(1);
        }
        Scanner in = new Scanner(System.in);
        // Read a command from the user.
        String line = in.nextLine();
        // Check which command the user entered and
        // call the corresponding method.
        while (!line.equals("bye")) {
            try {
                if (line.equals("list")) {
                    commandList();
                } else if (line.startsWith("done")) {
                    doneTask(line);
                } else if (line.startsWith("delete")) {
                    deleteTask(line);
                } else {
                    addTask(line);
                }
            //exception handling
            } catch (EmptyCommandException e) {
                System.out.println("Sorry, you didn't give me a fitting description for your task.");
            } catch (IllegalCommandException e) {
                System.out.println("That's not a known command format!");
            } catch (IOException e) {
                System.out.println("Something went wrong reading/writing to your data file.");
            }

            line = in.nextLine();
        }
        commandBye();
    }
}
