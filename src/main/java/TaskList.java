import java.util.ArrayList;

/**
 * TaskList class contains and facilitate operations on the inputTasks.
 */
public class TaskList {
    private static ArrayList<Task> inputTasks = new ArrayList<>();
    private static String acknowledgeMessage;

    public static int getTaskListSize() {
        return inputTasks.size();
    }

    public static ArrayList<Task> getInputTasks() {
        return inputTasks;
    }

    /**
     * Parses a given input String and stores it as a new task corresponding to todo/event/deadline.
     *
     * @param input              Command entered by the user.
     * @param shouldAppendToFile Flag to indicate whether the parsed task should be appended to file.
     * @throws DukeException If neither todo, deadline or event correspond to the input String.
     */
    public static void storeTask(String input, boolean shouldAppendToFile) throws DukeException {
        Task newTask;
        if (input.startsWith("todo")) {
            newTask = new Todo(input.substring("todo".length()).trim());
        } else if (input.startsWith("deadline")) {
            String[] inputSlices = input.substring("deadline".length()).trim().split("/");
            newTask = new Deadline(inputSlices[0].trim(), inputSlices[1].substring("by".length()).trim());
        } else if (input.startsWith("event")) {
            String[] inputSlices = input.substring("event".length()).trim().split("/");
            newTask = new Event(inputSlices[0].trim(), inputSlices[1].substring("at".length()).trim());
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

    /**
     * Deletes a task from the list inputTasks based on its index.
     *
     * @param taskNumber Index of the task in inputTasks to be deleted.
     */
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

    /**
     * Prints all tasks in inputTasks that contain a given query String in their descriptions
     *
     * @param query Substring to search for within each task's description
     */
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

    /**
     * Prints all tasks in inputTasks
     */
    public static void list() {
        System.out.println(Ui.getLine());

        for (int i = 0; i < inputTasks.size(); i++) {
            System.out.println((i + 1) + ". " + inputTasks.get(i));
        }

        System.out.println(Ui.getLine());
    }

    /**
     * Marks a pre-existing task in the inputTasks list as completed.
     *
     * @param completedTask     Index of the task in inputTasks to be marked as complete.
     * @param shouldWriteToFile Flag indicating whether the updated inputTasks list should be written to file.
     */
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
