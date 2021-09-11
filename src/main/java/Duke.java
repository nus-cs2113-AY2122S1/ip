import java.io.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import Exception.DukeException;
import Tasks.Task;
import Tasks.Event;
import Tasks.Todo;
import Tasks.Deadline;


public class Duke {
    private static boolean isLoading = true;

    private static void processInput (List<Task> tasks, boolean isLoading) throws IOException, DukeException {

        // Hard Drive loading
        if (isLoading) {
            File f = new File ("data.txt");
            if (f.createNewFile()) {
                System.out.println("data.txt created for you");
            }

            else {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line;
                while ( (line = br.readLine()) != null ) {
                    addTask(line, tasks);
                }
            }
            Duke.isLoading = false;
        }

        // After loading (ready for User Input)
        else {
            System.out.println("What can I do for you today?");

            while (true) {
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();

                if (input.equals("bye")) {
                    System.out.println("See you later alligator");
                    break;
                }

                if (!isQuery(input, tasks)) {
                    try {
                        Task addedTask = addTask(input, tasks);
                        System.out.println("I've added:");
                        addedTask.describe();
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
            Task.numberOfTasks -= 1;
            return true;
        }

        else if (input.equals("list")) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.print(i + 1 + ".");
                tasks.get(i).describe();
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

        // check to see if NOT loading from Hard Drive
        else if (!isLoading) {
            try {
                BufferedWriter output = new BufferedWriter(new FileWriter("data.txt", true));
                output.append(input);
                output.newLine();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        switch (taskType) {
        case "todo" :
            Task todoTask = new Todo(input.replaceAll(taskType, ""));
            tasks.add(todoTask);
            return todoTask;

        case "deadline" :
            Task deadlineTask = new Deadline (descriptionAndTime.split("/")[0], descriptionAndTime.split("/")[1]);
            tasks.add(deadlineTask);
            return deadlineTask;

        case "event" :
            Task eventTask = new Event(descriptionAndTime.split("/")[0], descriptionAndTime.split("/")[1]);
            tasks.add(eventTask);
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
        } catch (IOException e) {
            System.out.println("Failed to read from data.txt");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
