import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Storage {
    private static final ArrayList<Task> inputTasks = new ArrayList<>();
    private static final String STORAGE_PATH = "duke.txt";
    private static final int OK_EXIT_CODE = 0;
    public static final char COMPLETE_CHARACTER = 'X';
    private static String acknowledgeMessage;

    public static void loadStoredTasks(File storedTasks) {
        try {
            Scanner scanner = new Scanner(storedTasks);

            scanner.nextLine(); //Moves past prefix \n of file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                char completionStatus = line.charAt(0);
                String taskDescription = line.substring(1);

                try {
                    Storage.storeTask(taskDescription, false);
                    if (completionStatus == COMPLETE_CHARACTER) {
                        markComplete(inputTasks.size(), false);
                    }
                } catch (DukeException e) {
                    System.out.println("duke.txt is corrupted. Please delete duke.txt and restart the program.");
                    System.exit(OK_EXIT_CODE);
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("An error in reading the storage file occurred.");
            System.exit(OK_EXIT_CODE);
        }


    }

    public static void setupStorage() {
        try {
            File storedTasks = new File(STORAGE_PATH);
            if (storedTasks.createNewFile()) {
                System.out.println("File created: " + storedTasks.getName());
            } else {
                System.out.println("Loading from storage file");
                loadStoredTasks(storedTasks);
            }
        } catch (IOException e) {
            System.out.println("An error in creating the storage file occurred.");
            System.exit(OK_EXIT_CODE);
        }

    }

    public static void storeTask(String input, boolean shouldWriteToFile) throws DukeException {
        Task newTask;
        if (input.startsWith("todo")) {
            newTask = new Todo(input.substring(4).trim());
        } else if (input.startsWith("deadline")) {
            String[] inputSlices = input.substring(8).trim().split("/");
            newTask = new Deadline(inputSlices[0].trim(), inputSlices[1].substring(2).trim());
        } else if (input.startsWith("event")) {
            String[] inputSlices = input.substring(5).trim().split("/");
            newTask = new Event(inputSlices[0].trim(), inputSlices[1].substring(2).trim());
        } else {
            throw new DukeException();
        }

        inputTasks.add(newTask);

        acknowledgeMessage = "Got it. I've added this task: \n  " + inputTasks.get(inputTasks.size() - 1) + "\n"
                + "Now you have: " + inputTasks.size() + " tasks in the list";

        Response.echo(acknowledgeMessage);

        if (shouldWriteToFile) {
            try {
                FileWriter fw = new FileWriter(STORAGE_PATH, true);
                fw.write('\n' + newTask.getEncodedFormat());
                fw.close();
            } catch (IOException e) {
                System.out.println("Storage file cannot be updated. Changes will be lost when the program is closed");
            }
        }
    }

    public static void deleteTask(int taskNumber) {
        int taskIndex = taskNumber - 1;

        acknowledgeMessage = "Noted. I've removed this task: \n" + inputTasks.get(taskIndex) + "\n"
                + "Now you have: " + (inputTasks.size() - 1) + " tasks in the list";

        inputTasks.remove(taskIndex);

        Response.echo(acknowledgeMessage);

        writeTasksToFile();

    }

    public static void list() {
        System.out.println(Response.getLine());

        for (int i = 0; i < inputTasks.size(); i++) {
            System.out.println((i + 1) + ". " + inputTasks.get(i));
        }

        System.out.println(Response.getLine());
    }

    public static void markComplete(int completedTask, boolean shouldWriteToFile) {
        inputTasks.get(completedTask - 1).markComplete();
        Response.echo("Nice! I've marked this task as done: \n" + inputTasks.get(completedTask - 1));

        if (shouldWriteToFile) {
            writeTasksToFile();
        }
    }

    public static void writeTasksToFile() {
        try {
            FileWriter fw = new FileWriter(STORAGE_PATH, false);
            for (Task task : inputTasks) {
                fw.write('\n' + task.getEncodedFormat());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Storage file cannot be overwritten. Changes will be lost when the program is closed");
        }
    }
}