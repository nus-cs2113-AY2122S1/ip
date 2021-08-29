import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static boolean isNonAddTaskInput (String input, List<Task> tasks) {
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

    private static Task addTask (String input, List<Task> tasks) {
        String taskType = input.split(" ")[0];
        String descriptionAndTime = input.replaceAll(taskType, "");

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
            Task eventTask = new Event (descriptionAndTime.split("/")[0], descriptionAndTime.split("/")[1]);
            tasks.add(eventTask);
            return eventTask;

        default :
            System.out.println("Invalid Input");
        }
        return null;
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

            if (!isNonAddTaskInput(input, tasks)) {
                Task addedTask = addTask(input, tasks);
                System.out.println("Yeah boii I've added:");

                assert addedTask != null;
                addedTask.describe();
                System.out.println("You now have " + Task.numberOfTasks + " tasks in the list");
            }
        }
    }
}
