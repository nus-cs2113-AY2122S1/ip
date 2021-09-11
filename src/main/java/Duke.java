import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {
    private static void greet() {
        System.out.println("Welcome to Duke! What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    private static void loadData() {
        try {
            DataManager.readFileContents("data/duke.txt");
        } catch (FileNotFoundException e) {
            System.out.println("New user, no local data found");
        }
    }

    private static void saveData() {
        try {
            Path path = Paths.get("data/duke.txt");
            Files.createDirectories(path.getParent()); //make directory
            DataManager.writeFileContents("data/duke.txt");//write to file, create if haven't
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        loadData();
        greet();
        //initialise variables to scan user input
        String userInput;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                exit();
                break;
            } else if (userInput.startsWith("list")) {
                TaskManager.listTasks();
            } else {
                TaskManager.handleRequest(userInput);
            }
        }
        saveData();
    }




}

