import java.util.ArrayList;

public class TaskList {

    public static void printTaskList(ArrayList<Task> tasks) {
        String message = " Here are the tasks in your list:\n";
        int taskIndex = 1;
        for (Task task : tasks) {
            message = message + " " + taskIndex + "." + task + "\n";
            taskIndex++;
        }
        Ui.printMessage(message);
    }

    public static void markAsDone(ArrayList<Task> tasks, int taskNumber) {
        try{
            tasks.get(taskNumber).setDone();
            String message = " Nice! I've marked this task as done:\n"
                    + "  " + tasks.get(taskNumber) + "\n";
            Ui.printMessage(message);
        } catch (IndexOutOfBoundsException e) {
            DukeException.printIndexError();
        }
    }

    public static void deleteTask(ArrayList<Task> tasks, int taskNumber) {
        try{
            Task deletedTask = tasks.get(taskNumber);
            tasks.remove(taskNumber);
            String message = " Noted. I've removed this task:\n"
                    + "  " + deletedTask + "\n"
                    + " Now you have " + tasks.size() +" tasks in the list.\n";
            Ui.printMessage(message);
        } catch (IndexOutOfBoundsException e) {
            DukeException.printIndexError();
        }
    }

    public static Task createTask(String[] parsedUserInput) {
        String category = parsedUserInput[0];
        String description = parsedUserInput[1];
        String details = parsedUserInput[2];
        Task newTask = new Task("initialize");
        switch (category) {
        case "todo":
            newTask = new ToDo(description);
            break;
        case "deadline":
            newTask = new Deadline(description, details);
            break;
        case "event":
            newTask = new Event(description, details);
            break;
        default:
            break;
        }
        return newTask;
    }

    public static void addTask(ArrayList<Task> taskList, Task newTask) {
        taskList.add(newTask);
        String message = " Got it. I've added this task:\n"
                + "  " + newTask + "\n"
                + " Now you have " + taskList.size() + " tasks in the list.\n";
        Ui.printMessage(message);
    }

}
