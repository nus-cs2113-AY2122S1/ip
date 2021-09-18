import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> inputTasks = new ArrayList<>();
    private static String acknowledgeMessage;

    public static int getTaskListSize() {
        return inputTasks.size();
    }

    public static ArrayList<Task> getInputTasks() {
        return inputTasks;
    }

    public static void storeTask(String input, boolean shouldAppendToFile) throws DukeException {
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

        Ui.echo(acknowledgeMessage);

        if (shouldAppendToFile) {
            Storage.appendTaskToFile(newTask);
        }
    }

    public static void deleteTask(int taskNumber) {
        int taskIndex = taskNumber - 1;
        try {
            acknowledgeMessage = "Noted. I've removed this task: \n" + inputTasks.get(taskIndex) + "\n"
                    + "Now you have: " + (inputTasks.size() - 1) + " tasks in the list";

            inputTasks.remove(taskIndex);

            Ui.echo(acknowledgeMessage);

            Storage.writeTasksToFile();
        } catch (IndexOutOfBoundsException e) {
            Ui.echo("Please enter a task number from the list");
        }
    }

    public static void findTask(String query) {
        System.out.println(Ui.getLine());
        System.out.println("Here are the matching tasks in your list:");
        int taskNumber = 1;
        for (Task task : inputTasks) {
            if (task.description.contains(query)) {
                System.out.println(taskNumber + ". " + task);
                taskNumber++;
            }
        }

        System.out.println(Ui.getLine());
    }

    public static void list() {
        System.out.println(Ui.getLine());

        for (int i = 0; i < inputTasks.size(); i++) {
            System.out.println((i + 1) + ". " + inputTasks.get(i));
        }

        System.out.println(Ui.getLine());
    }

    public static void markComplete(int completedTask, boolean shouldWriteToFile) {
        try {
            inputTasks.get(completedTask - 1).markComplete();
            Ui.echo("Nice! I've marked this task as done: \n" + inputTasks.get(completedTask - 1));

            if (shouldWriteToFile) {
                Storage.writeTasksToFile();
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.echo("Please enter a task number from the list");
        }
    }
}
