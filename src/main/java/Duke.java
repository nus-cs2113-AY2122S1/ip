import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import Tasks.Task;
import Tasks.Event;
import Tasks.Todo;
import Tasks.Deadline;
import Exception.DukeException;


public class Duke {

    public static boolean isLoading = true;

    private static void saveData (List<Task> tasks) {
        File dataFile = new File ("savedData.txt");

        try {
            if (dataFile.createNewFile()) {
                System.out.println("savedData.txt created for you");
            }

            BufferedWriter dataFileWriter = new BufferedWriter(new FileWriter(dataFile));
            for (int i = 0; i < tasks.size(); i++) {
                dataFileWriter.append(tasks.get(i).describeString());
                dataFileWriter.newLine();
            }
            dataFileWriter.close();

        } catch (IOException e) {
            System.out.println("savedData.txt already created");
        }
    }

    private static void saveCommand (String input) {
        try {
            BufferedWriter commandFileWriter = new BufferedWriter(new FileWriter("savedCommands.txt", true));
            commandFileWriter.append(input);
            commandFileWriter.newLine();
            commandFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processInput (List<Task> tasks, boolean isLoading) throws IOException, DukeException {

        // reading from Hard Drive
        if (isLoading) {
            File commandFile = new File ("savedCommands.txt");

            if (commandFile.createNewFile()) {
                System.out.println("savedCommands.txt created for you");
            }

            else {
                BufferedReader commandFileReader = new BufferedReader(new FileReader(commandFile));
                String line;
                while ( (line = commandFileReader.readLine()) != null ) {
                    if (!isQuery(line,tasks)) {
                        addTask(line,tasks);
                    }
                }
            }
            Duke.isLoading = false;
        }

        // ready for User Input
        else {
            System.out.println("What can I do for you today?");

            while (true) {
                Scanner systemScanner = new Scanner(System.in);
                String systemInput = systemScanner.nextLine();

                if (systemInput.equals("bye")) {
                    System.out.println("See you later alligator");
                    break;
                }

                if (!isQuery(systemInput, tasks)) {
                    try {
                        Task addedTask = addTask(systemInput, tasks);
                        System.out.println("I've added:");
                        addedTask.describePrint();
                        System.out.println("You now have " + Task.numberOfTasks + " tasks in the list");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    private static boolean isQuery (String input, List<Task> tasks) {

        if (input.contains("done")) {
            int taskIndex = Integer.parseInt(input.replaceAll("[^0-9]", ""));
            tasks.get(taskIndex - 1).markAsDone();

            if (!isLoading) {
                saveCommand(input);
                System.out.println("This task is done:");
                tasks.get(taskIndex-1).describePrint();
            }

            return true;
        }

        else if (input.equals("list")) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.print(i + 1 + ".");
                tasks.get(i).describePrint();
            }
            return true;
        }

        else if (input.contains("delete")) {
            int taskIndex = Integer.parseInt(input.replaceAll("[^0-9]", ""));

            if (!isLoading) {
                saveCommand(input);
                System.out.println("I have removed this task:");
                tasks.get(taskIndex - 1).describePrint();
                tasks.remove(taskIndex -1);
                Task.numberOfTasks -= 1;
                System.out.println("You now have " + Task.numberOfTasks + " tasks in the list");
            }

            else {
                tasks.remove(taskIndex -1);
                Task.numberOfTasks -= 1;
            }

            return true;
        }

        return false;
    }

    private static Task addTask (String input, List<Task> tasks) throws DukeException {
        String taskType = input.split(" ")[0];
        String descriptionAndTime = input.replaceAll(taskType, "");

        // check for Task's description
        if ( (input.contains("todo") || input.contains("deadline") || input.contains("event"))
                && input.split(" ").length == 1) {
            throw new DukeException("Description cannot be empty");
        }

        switch (taskType) {
        case "todo" :
            Task todoTask = new Todo(input.replaceAll(taskType, ""));
            tasks.add(todoTask);
            if (!isLoading) {
                saveCommand(input);
            }
            return todoTask;

        case "deadline" :
            Task deadlineTask = new Deadline (descriptionAndTime.split("/")[0], descriptionAndTime.split("/")[1]);
            tasks.add(deadlineTask);
            if (!isLoading) {
                saveCommand(input);
            }
            return deadlineTask;

        case "event" :
            Task eventTask = new Event(descriptionAndTime.split("/")[0], descriptionAndTime.split("/")[1]);
            tasks.add(eventTask);
            if (!isLoading) {
                saveCommand(input);
            }
            return eventTask;

        default :
            throw new DukeException("I don't understand, try again");
        }
    }

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();

        try {
            processInput(tasks, isLoading);           // Hard-drive loading
            processInput(tasks, isLoading);           // Normal functionality
            saveData(tasks);                          // provides readable User format of Tasks
        } catch (IOException e) {
            System.out.println("Failed to read from savedCommands.txt");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
