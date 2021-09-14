import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

public class Duke {

    public static final String LINE = "    ____________________________________________________________";
    public static final String INDENT = "     ";
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String LINE_SEPARATOR_AND_INDENT = LINE_SEPARATOR + INDENT;
    public static final String HELP_INSTRUCTIONS = "Learn how to use Duke:" +
            LINE_SEPARATOR_AND_INDENT + " To add a task: todo task_name" +
            LINE_SEPARATOR_AND_INDENT + " To add an event: event /on event_date" +
            LINE_SEPARATOR_AND_INDENT + " To add a deadline: deadline /by deadline_date" +
            LINE_SEPARATOR_AND_INDENT + " To view the list: list" +
            LINE_SEPARATOR_AND_INDENT + " To get help: help" +
            LINE_SEPARATOR_AND_INDENT + " To end the program: bye";

    public static String filePath = Paths.get(System.getProperty("user.dir"), "data/duke.txt").normalize().toString();


    public static void echo(String line) {
        System.out.println(LINE + LINE_SEPARATOR + INDENT + line + LINE_SEPARATOR + LINE);
    }

    public static void loadData() {
        try {
            DataSaver.checkFileExist("data/duke.txt");
            DataSaver.loadFileContents("data/duke.txt");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveData() {
        try {
            Path filePath = Paths.get("data/duke.txt");
            Files.createDirectories(filePath.getParent());
            DataSaver.writeFileContents("data/duke.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void executeRequest() {
        String line;
        Scanner in = new Scanner(System.in);
        int taskCount = 0;
        Task[] tasks = new Task[100];

        line = in.nextLine();
        String[] words = line.split(" ");

        while (!line.equals("bye")) {
            System.out.println(LINE);
            try {
                switch (words[0]) {
                case "list":
                    TaskHandler.printTaskList();
                    break;
                case "done":
                    TaskHandler.markTaskAsDone(words);
                    saveData();
                    break;
                case "deadline":
                    TaskHandler.addDeadline(line);
                    saveData();
                    break;
                case "event":
                    TaskHandler.addEvent(line);
                    saveData();
                    break;
                case "todo":
                    TaskHandler.addTodo(line);
                    saveData();
                    break;
                case "help":
                    TaskHandler.showHelp();
                    break;
                default:
                    TaskHandler.handleWrongCommand();
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(LINE);
            line = in.nextLine();
            words = line.split(" ");
        }
    }

    public static void greetUser() {
        echo("Hello! I'm Duke." + LINE_SEPARATOR_AND_INDENT + "What can I do for you?" +
                LINE_SEPARATOR + LINE_SEPARATOR_AND_INDENT + HELP_INSTRUCTIONS);
    }

    public static void farewellUser() {
        echo("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        loadData();
        greetUser();
        executeRequest();
        farewellUser();
    }
}
