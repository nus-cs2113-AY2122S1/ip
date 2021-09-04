import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static boolean isQuery (String input, List<Task> tasks) {

        if (input.contains("done")) {
            int taskIndex = Integer.parseInt(input.replaceAll("[^0-9]", ""));
            if (taskIndex > Task.numberOfTasks) {
                System.out.println("You do not have that many tasks");
                return false;
            }

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

        switch (taskType) {
        case "todo" :
            System.out.println(input.split(" ")[1]);
            Task todoTask = new Todo(input.replaceAll(taskType, ""));
            tasks.add(todoTask);
            return todoTask;

        case "deadline" :
            Task deadlineTask = new Deadline (descriptionAndTime.split("/")[0], descriptionAndTime.split("/")[1]);
            tasks.add(deadlineTask);
            return deadlineTask;

        case "event" :
            Task eventTask = new Event (descriptionAndTime.split("/")[0], descriptionAndTime.split("/")[1]);
            tasks.add(eventTask);
            return eventTask;

        default :
            throw new DukeException("I don't understand, try again");
        }
    }

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<Task>();
        System.out.println("What can I do for you today homie");

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
